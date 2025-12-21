package com.workintech.twitter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.workintech.twitter.entity.Like;

public interface LikeRepository extends JpaRepository<Like, Long> {

}
