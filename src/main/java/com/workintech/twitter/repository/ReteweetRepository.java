package com.workintech.twitter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.workintech.twitter.entity.Reteweet;

public interface ReteweetRepository extends JpaRepository<Reteweet, Long> {

}
