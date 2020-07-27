package com.reqhu.testtask.repository;

import com.reqhu.testtask.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Integer> {

    @Modifying
    @Query(value = "INSERT INTO users (name) VALUES (:name)", nativeQuery = true)
    void nativeSqlSave(@Param("name") String name);

}