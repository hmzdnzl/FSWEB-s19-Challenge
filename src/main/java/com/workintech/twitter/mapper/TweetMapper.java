package com.workintech.twitter.mapper;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;
import com.workintech.twitter.dto.request.TweetPatchRequestDto;
import com.workintech.twitter.dto.request.TweetRequestDto;
import com.workintech.twitter.dto.response.TweetResponseDto;
import com.workintech.twitter.entity.Tweet;


@Component
public class TweetMapper {

      public Tweet toEntity(TweetRequestDto tweetRequestDto) {
    Tweet tweet = new Tweet();
   tweet.setTweetText(tweetRequestDto.tweetText());
   tweet.setCreatedDate(LocalDateTime.now());      
        return tweet;
    }

    public TweetResponseDto toTweetResponseDto(Tweet tweet) {        
        return new TweetResponseDto(
            tweet.getTweetText(),
            tweet.getUser() != null ? tweet.getUser().getNickName() : null,
            tweet.getCreatedDate(),
            tweet.getLikes() != null ? tweet.getLikes().size() : 0,
            tweet.getRetweets() != null ? tweet.getRetweets().size() : 0,
            tweet.getComments() != null ? tweet.getComments().size() : 0
        );
    }

    public void updateEntity(Tweet tweetToUpdate, TweetPatchRequestDto tweetPatchRequestDto ) {
          if(tweetPatchRequestDto.tweetText() != null) {
            tweetToUpdate.setTweetText(tweetPatchRequestDto.tweetText());
        }

    }

}
