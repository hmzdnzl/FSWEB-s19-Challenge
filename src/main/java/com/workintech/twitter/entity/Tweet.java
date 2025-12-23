package com.workintech.twitter.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
@Table(name="tweet", schema = "twitter")
public class Tweet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

@Column(name="created_time")
private LocalDateTime createdDate;

@NotBlank
@NotNull
@Column(name="tweet_text")
private String tweetText;

@OneToMany(mappedBy = "tweet", cascade = CascadeType.ALL)
private List <Like> likes = new ArrayList<>();

@ToString.Exclude
@ManyToOne
@JoinColumn(name="user_id")
private User user;

@OneToMany(mappedBy = "tweet", cascade = CascadeType.ALL)
private List<Retweet> reteweets = new ArrayList<>();


@ToString.Exclude
@OneToMany(mappedBy = "tweet", cascade = CascadeType.ALL)
private List<Comment> comments = new ArrayList<>();

public void addComment(Comment comment) {
    if(comment != null && !comments.contains(comment)) {
        comment.setTweet(this);
        comments.add(comment);
    }
}

public void removeComment(Comment comment) {
    if (comment != null && comments.remove(comment)) {
        comment.setTweet(null);
    }
}

public void addLike(Like like) {
  if (like !=null && !likes.contains(like)) {
    like.setTweet(this);
    likes.add(like);
  }  
}

public void removeLike(Like like) {
    if (like != null && likes.remove(like)) {
        like.setTweet(null);
    }
}

public void addReteweet(Retweet reteweet) {
    if(reteweet != null && !reteweets.contains(reteweet)) {
        reteweet.setTweet(this);
        reteweets.add(reteweet);
    }
}

public void removeReteweet(Retweet reteweet) {
    if(reteweet != null && reteweets.remove(reteweet)) {
        reteweet.setTweet(null);       
    }
}



}


