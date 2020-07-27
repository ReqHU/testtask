package com.reqhu.testtask.service;

import com.reqhu.testtask.domain.Booking;
import com.reqhu.testtask.domain.Room;
import com.reqhu.testtask.domain.User;
import com.reqhu.testtask.repository.BookingRepository;
import com.reqhu.testtask.repository.RoomRepository;
import com.reqhu.testtask.repository.UserRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static com.reqhu.testtask.util.BookingsUtil.checkForAvailability;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    private final UserRepository userRepository;

    private final RoomRepository roomRepository;

    public BookingService(BookingRepository bookingRepository, UserRepository userRepository, RoomRepository roomRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
    }

    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }

    @Transactional
    public void deleteById(int id) {
        bookingRepository.deleteById(id);
    }

    @Transactional
    public void save(
            String name, String description, LocalDateTime startDateTime,
            LocalDateTime endDateTime, Integer userId, Integer roomId
    ) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("Название манипуляции не должно быть пустым.");
        }
        if (startDateTime.isAfter(endDateTime)) {
            throw new IllegalArgumentException("Дата/время начала манипуляции должно быть перед датой/временем конца манипуляции.");
        }
        User user = userRepository.findById(userId).orElseThrow();
        Room room = roomRepository.findById(roomId).orElseThrow();
        if (checkForAvailability(startDateTime, endDateTime, user.getBookings())) {
            if (checkForAvailability(startDateTime, endDateTime, room.getBookings())) {
                bookingRepository.nativeSqlSave(name, description, startDateTime, endDateTime, userId, roomId);
            } else {
                throw new RuntimeException("Помещение занято в это время.");
            }
        } else {
            throw new RuntimeException("Участник занят в это время.");
        }
    }

    @Scheduled(cron = "0 * * * * *")
    @Transactional
    public void deleteBookingsAutomatically() {
        List<Booking> bookings = bookingRepository.findAll();
        for (Booking booking : bookings) {
            if (booking.getEndDateTime().isBefore(LocalDateTime.now())) {
                bookingRepository.deleteById(booking.getId());
            }
        }
    }

}
