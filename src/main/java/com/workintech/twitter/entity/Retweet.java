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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="reteweet", schema = "twitter")
public class Retweet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
private long id;

@ManyToOne
@JoinColumn(name="tweet_id")
private Tweet tweet;

@Column(name = "tweet_text")
private String tweetText;

@Column(name = "created_date")
private LocalDateTime createdDate;

@ToString.Exclude
@ManyToOne
@JoinColumn(name="user_id")
private User user;
}
