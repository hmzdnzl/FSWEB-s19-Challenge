package com.workintech.twitter.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.workintech.twitter.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.nickName like %:nickName%")
    List<User> searchByNickName(@Param("nickName") String nickName);

    @Query("select u from User u where u.email = :email")
    Optional<User> findByEmail(@Param("email") String email);

}
