package com.reqhu.testtask.util;

import com.reqhu.testtask.domain.Booking;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.util.List;

@UtilityClass
public class BookingsUtil {

    public boolean checkForAvailability(LocalDateTime startDateTime, LocalDateTime endDateTime, List<Booking> bookings) {
        for (Booking booking : bookings) {
            if (!startDateTime.isAfter(booking.getEndDateTime()) && !booking.getStartDateTime().isAfter(endDateTime)) {
                return false;
            }
        }
        return true;
    }

}
