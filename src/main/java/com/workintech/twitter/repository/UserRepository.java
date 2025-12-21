package com.workintech.twitter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.workintech.twitter.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
