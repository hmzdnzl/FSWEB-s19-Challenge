package com.workintech.twitter.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.workintech.twitter.entity.Like;
import com.workintech.twitter.entity.Tweet;
import com.workintech.twitter.entity.User;

public interface LikeRepository extends JpaRepository<Like, Long> {
@Query("select l from Like l where l.user = :user and l.tweet = :tweet")
Optional<Like> findByUserAndTweet(@Param("user") User user, @Param("tweet") Tweet tweet);
}
