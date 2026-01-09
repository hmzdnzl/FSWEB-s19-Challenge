package com.workintech.twitter.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.workintech.twitter.dto.request.RetweetRequestDto;
import com.workintech.twitter.dto.response.RetweetResponseDto;
import com.workintech.twitter.entity.Retweet;
import com.workintech.twitter.exceptions.RetweetNotFoundException;
import com.workintech.twitter.exceptions.UnauthorizedDeleteException;
import com.workintech.twitter.mapper.RetweetMapper;
import com.workintech.twitter.repository.RetweetRepository;
import com.workintech.twitter.repository.TweetRepository;
import com.workintech.twitter.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RetweetServiceImpl implements RetweetService {

    @Autowired
    private final RetweetRepository retweetRepository;

    @Autowired
    private final RetweetMapper retweetMapper;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final TweetRepository tweetRepository;

    @Override
    public List<RetweetResponseDto> getAll() {
         return retweetRepository
       .findAll()
       .stream()
       .map(retweetMapper::toRetweetResponseDto)
       .toList();
    }

    @Override
    public RetweetResponseDto findById(Long id) {
      Optional<Retweet> optRetweet = retweetRepository.findById(id);
        if(optRetweet.isPresent()) {
              Retweet retweet = optRetweet.get();
        return retweetMapper.toRetweetResponseDto(retweet);
        }
        throw new RetweetNotFoundException(id+" id'li tweet bulunamadı.");
    }

    @Override
    public RetweetResponseDto create(RetweetRequestDto retweetRequestDto) {
        org.springframework.security.core.Authentication authentication = org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        com.workintech.twitter.entity.User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new com.workintech.twitter.exceptions.UserNotFoundException("Kullanıcı bulunamadı"));

        com.workintech.twitter.entity.Tweet tweet = tweetRepository.findById(retweetRequestDto.tweetId())
            .orElseThrow(() -> new com.workintech.twitter.exceptions.TweetNotFoundException("Tweet bulunamadı"));

        java.util.Optional<Retweet> existingRetweet = retweetRepository.findByUserIdAndTweetId(user.getId(), tweet.getId());
        if (existingRetweet.isPresent()) {
            retweetRepository.delete(existingRetweet.get());
            return null; // veya özel bir response dönebilirsiniz
        } else {
            Retweet retweet = retweetMapper.toEntity(retweetRequestDto);
            retweet.setUser(user);
            retweet.setTweet(tweet);
            retweet = retweetRepository.save(retweet);
            return retweetMapper.toRetweetResponseDto(retweet);
        }
    }
    
    @Override
    public void deleteById(Long id) {
        org.springframework.security.core.Authentication authentication = org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        com.workintech.twitter.entity.User user = userRepository.findByEmail(email)
          .orElseThrow(() -> new com.workintech.twitter.exceptions.UserNotFoundException("Kullanıcı bulunamadı"));

        Retweet retweet = retweetRepository.findById(id)
          .orElseThrow(() -> new com.workintech.twitter.exceptions.RetweetNotFoundException(id + " id'li retweet bulunamadı."));

        if (retweet.getUser() == null || !java.util.Objects.equals(user.getId(), retweet.getUser().getId())) {
          throw new UnauthorizedDeleteException("Bu retweet'i silmeye yetkiniz yok.", HttpStatus.FORBIDDEN);
        }

        retweetRepository.deleteById(id);
    }

    
}
