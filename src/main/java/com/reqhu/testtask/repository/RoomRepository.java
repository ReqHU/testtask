package com.reqhu.testtask.repository;

import com.reqhu.testtask.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface RoomRepository extends JpaRepository<Room, Integer> {

    @Modifying
    @Query(value = "INSERT INTO rooms (type) VALUES (:type)", nativeQuery = true)
    void nativeSqlSave(@Param("type") String type);

}
