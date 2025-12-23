package com.workintech.twitter.mapper;

import org.springframework.stereotype.Component;

import com.workintech.twitter.dto.request.RetweetRequestDto;
import com.workintech.twitter.dto.response.RetweetResponseDto;
import com.workintech.twitter.entity.Retweet;

@Component
public class RetweetMapper {

        public Retweet toEntity(RetweetRequestDto retweetRequestDto) {
    Retweet reteweet = new Retweet();
   reteweet.setTweetText(retweetRequestDto.tweetText());      
        return reteweet;
    }

    public RetweetResponseDto toRetweetResponseDto(Retweet retweet) {        
    return new RetweetResponseDto(
    retweet.getId(),
    retweet.getTweetText(),
    retweet.getUser() != null ? retweet.getUser().getNickName() : null,
    retweet.getCreatedDate()
);
    }
    
}
