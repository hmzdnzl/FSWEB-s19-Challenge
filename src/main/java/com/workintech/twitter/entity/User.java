package com.workintech.twitter.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.annotation.Nullable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of="id")
@Entity
@Table(name="user", schema = "twitter")
public class User implements UserDetails {
    @Id
    @NotNull
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 16)    
@Column(name="nick_name")
private String nickName;

@NotEmpty
@NotBlank
@NotNull
@Email
@Size(max = 50)
@Column(name = "email")
private String email;

@NotNull
@NotEmpty
@NotBlank
//@Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9]).{8,20}$", message = "Şifre en az 8 en fazla 20 karakter, bir büyük harf, bir küçük harf ve bir rakam içermelidir.")
@Column(name = "password")
private String password;

@ManyToMany(fetch = FetchType.EAGER)
@JoinTable(
    name="user_role",
    schema = "twitter",
    joinColumns = @JoinColumn(name="user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id")
)
private Set<Role> roles = new HashSet<>();


@Column(name="signup_date")
private LocalDate signUpDate;

@ToString.Exclude
@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
private List<Tweet> tweets = new ArrayList<>();


@ToString.Exclude
@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
private List<Retweet> reteweets = new ArrayList<>();


@ToString.Exclude
@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
private List<Like> likes = new ArrayList<>();


@ToString.Exclude
@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
private List<Comment> comments = new ArrayList<>();


public void addTweet(Tweet tweet) {
    
    if(tweet != null && !tweets.contains(tweet)) {
        tweet.setUser(this);
    tweets.add(tweet);
}
}

public void removeTweet(Tweet tweet) {
     if (tweet != null && tweets.remove(tweet)) {
        tweet.setUser(null);
    }
}

public void addComment(Comment comment) {
    if(comment != null && !comments.contains(comment)) {
        comment.setUser(this);
        comments.add(comment);
    }
}

public void removeComment(Comment comment) {
    if (comment != null && comments.remove(comment)) {
        comment.setUser(null);
    }
}

public void addLike(Like like) {
  if (like !=null && !likes.contains(like)) {
    like.setUser(this);
    likes.add(like);
  }  
}

public void removeLike(Like like) {
    if (like != null && likes.remove(like)) {
        like.setUser(null);
    }
}

public void addReteweet(Retweet reteweet) {
    if(reteweet != null && !reteweets.contains(reteweet)) {
        reteweet.setUser(this);
        reteweets.add(reteweet);
    }
}

public void removeReteweet(Retweet reteweet) {
    if(reteweet != null && reteweets.remove(reteweet)) {
        reteweet.setUser(null);       
    }
}

@Override
public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.roles;
}

@Override
public String getUsername() {
return email;
}

@Override
public @Nullable String getPassword() {
    return this.password;
}

}
