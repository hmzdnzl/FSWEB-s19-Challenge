package com.workintech.twitter.entity;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="comment", schema = "twitter")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
private long id;

@Column(name = "comment_piece")
private int commentPiece;

@Column(name="created_date")
private LocalDateTime createdDate;

@ToString.Exclude
@ManyToOne
@JoinColumn(name="user_id")
private User user;


@ToString.Exclude
@ManyToOne
@JoinColumn(name="tweet_id")
private Tweet tweet;
}
