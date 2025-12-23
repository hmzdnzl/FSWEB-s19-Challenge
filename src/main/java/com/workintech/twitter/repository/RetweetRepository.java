package com.workintech.twitter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.workintech.twitter.entity.Retweet;

public interface RetweetRepository extends JpaRepository<Retweet, Long> {

}
