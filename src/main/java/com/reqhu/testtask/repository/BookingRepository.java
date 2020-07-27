package com.reqhu.testtask.repository;

import com.reqhu.testtask.domain.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Repository
@Transactional
public interface BookingRepository extends JpaRepository<Booking, Integer> {

    @Modifying
    @Query(value =
            "INSERT INTO bookings (name, description, start_date_time, end_date_time, user_id, room_id) " +
            "VALUES (:name, :description, :start_date_time, :end_date_time, :user_id, :room_id)",
            nativeQuery = true)
    void nativeSqlSave(
            @Param("name") String name, @Param("description") String description,
            @Param("start_date_time") LocalDateTime startDateTime, @Param("end_date_time") LocalDateTime endDateTime,
            @Param("user_id") Integer userId, @Param("room_id") Integer roomId);

}
