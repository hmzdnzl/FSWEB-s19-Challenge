package com.workintech.twitter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.workintech.twitter.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
