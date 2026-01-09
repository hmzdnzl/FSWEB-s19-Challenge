package com.workintech.twitter.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.workintech.twitter.entity.Retweet;

public interface RetweetRepository extends JpaRepository<Retweet, Long> {

	@Query("SELECT r FROM Retweet r WHERE r.user.id = :userId AND r.tweet.id = :tweetId")
Optional<Retweet> findByUserIdAndTweetId(@Param("userId") Long userId, @Param("tweetId") Long tweetId);

}
