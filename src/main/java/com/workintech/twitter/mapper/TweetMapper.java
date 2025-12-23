package com.workintech.twitter.mapper;

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
        return tweet;
    }

    public TweetResponseDto toTweetResponseDto(Tweet tweet) {        
           return new TweetResponseDto(
        tweet.getTweetText(),
        tweet.getCreatedDate(),
        tweet.getUser() != null ? tweet.getUser().getId() : null,
        tweet.getLikes() != null ? tweet.getLikes().size() : 0,
        tweet.getReteweets() != null ? tweet.getReteweets().size() : 0,
        tweet.getComments() != null ? tweet.getComments().size() : 0
    );
    }

    public void updateEntity(Tweet tweetToUpdate, TweetPatchRequestDto tweetPatchRequestDto ) {
          if(tweetPatchRequestDto.tweetText() != null) {
            tweetToUpdate.setTweetText(tweetPatchRequestDto.tweetText());
        }

    }

}
