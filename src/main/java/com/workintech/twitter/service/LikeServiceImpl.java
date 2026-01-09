package com.workintech.twitter.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.workintech.twitter.dto.request.LikeRequestDto;
import com.workintech.twitter.dto.response.LikeResponseDto;
import com.workintech.twitter.entity.Like;
import com.workintech.twitter.entity.Tweet;
import com.workintech.twitter.entity.User;
import com.workintech.twitter.exceptions.LikeNotFoundException;
import com.workintech.twitter.exceptions.TweetNotFoundException;
import com.workintech.twitter.exceptions.UserNotFoundException;
import com.workintech.twitter.mapper.LikeMapper;
import com.workintech.twitter.repository.LikeRepository;
import com.workintech.twitter.repository.TweetRepository;
import com.workintech.twitter.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    @Autowired
    private final LikeRepository likeRepository;

    @Autowired
    private final TweetRepository tweetRepository;

    @Autowired
    private final UserRepository userRepository;
    
    @Autowired
    private final LikeMapper likeMapper;

    @Override
    public List<LikeResponseDto> getAll() {
     return likeRepository.findAll()
            .stream()            
            .map(likeMapper::toLikeResponseDto)
            .toList();     
    }

    @Override
    public LikeResponseDto findById(Long id) {
           Optional<Like> optLike = likeRepository.findById(id);
        if(optLike.isPresent()) {
              Like like = optLike.get();
        return likeMapper.toLikeResponseDto(like);
        }
        throw new LikeNotFoundException(id+" id'li tweet bulunamadı.");
    }   

    @Override
    public LikeResponseDto create(LikeRequestDto likeRequestDto) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String email = authentication.getName();
    User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new UserNotFoundException("Kullanıcı bulunamadı"));

    Tweet tweet = tweetRepository.findById(likeRequestDto.tweetId())
        .orElseThrow(() -> new TweetNotFoundException("Tweet bulunamadı"));

    Optional<Like> existingLike = likeRepository.findByUserAndTweet(user, tweet);

    if (existingLike.isPresent()) {
        likeRepository.delete(existingLike.get());  
        return null; 
    } else {
        Like like = likeMapper.toEntity(likeRequestDto);
        like.setUser(user);
        like.setTweet(tweet);
        like = likeRepository.save(like);
        return likeMapper.toLikeResponseDto(like);
    }
}
    

    @Override
    public void deleteById(Long id) {
     likeRepository.deleteById(id);
}
}

