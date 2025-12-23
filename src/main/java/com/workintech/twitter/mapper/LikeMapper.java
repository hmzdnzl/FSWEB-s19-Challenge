package com.workintech.twitter.mapper;

import org.springframework.stereotype.Component;

import com.workintech.twitter.dto.request.LikeRequestDto;
import com.workintech.twitter.dto.response.LikeResponseDto;
import com.workintech.twitter.entity.Like;

@Component
public class LikeMapper {
    public Like toEntity(LikeRequestDto likeRequestDto) {
    Like like = new Like();
        return like;
    }

  public LikeResponseDto toLikeResponseDto(Like like) {
    int likeCount = like.getTweet() != null && like.getTweet().getLikes() != null
        ? like.getTweet().getLikes().size(): 0;
        
    String user = like.getUser() != null ? like.getUser().getNickName() : null;
    return new LikeResponseDto(likeCount, user);
}
    }

