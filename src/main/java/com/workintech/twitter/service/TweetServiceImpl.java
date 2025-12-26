package com.workintech.twitter.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.workintech.twitter.dto.request.TweetPatchRequestDto;
import com.workintech.twitter.dto.request.TweetRequestDto;
import com.workintech.twitter.dto.response.TweetResponseDto;
import com.workintech.twitter.dto.response.UserResponseDto;
import com.workintech.twitter.entity.Tweet;
import com.workintech.twitter.entity.User;
import com.workintech.twitter.exceptions.TweetNotFoundException;
import com.workintech.twitter.exceptions.UnauthorizedDeleteException;
import com.workintech.twitter.exceptions.UserNotFoundException;
import com.workintech.twitter.mapper.TweetMapper;
import com.workintech.twitter.mapper.UserMapper;
import com.workintech.twitter.repository.TweetRepository;
import com.workintech.twitter.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TweetServiceImpl implements TweetService {

    @Autowired
    private final TweetRepository tweetRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private final TweetMapper tweetMapper;

    @Autowired
    private final UserMapper userMapper;

    @Override
    public List<TweetResponseDto> getAll() {
       return tweetRepository
       .findAll()
       .stream()
       .map(tweetMapper::toTweetResponseDto)
       .toList();
    }

    @Override
    public TweetResponseDto findById(Long id) {
    Optional<Tweet> optTweet = tweetRepository.findById(id);
        if(optTweet.isPresent()) {
              Tweet tweet = optTweet.get();
        return tweetMapper.toTweetResponseDto(tweet);
        }
        throw new TweetNotFoundException(id+" id'li tweet bulunamadı.");
    }

    @Override
    public TweetResponseDto create(TweetRequestDto tweetRequestDto) {
       Tweet tweet = tweetMapper.toEntity(tweetRequestDto);

          Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String email = authentication.getName();

 User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("Kullanıcı bulunamadı"));

 tweet.setUser(user);
 
       tweet = tweetRepository.save(tweet);
       return tweetMapper.toTweetResponseDto(tweet); 
    }

    @Override
    public TweetResponseDto replaceOrCreate(Long id, TweetRequestDto tweetRequestDto) {
          Tweet tweet = tweetMapper.toEntity(tweetRequestDto);
    Optional<Tweet> optTweet = tweetRepository.findById(id);
    if(optTweet.isPresent()) {
        tweet.setId(id);
        tweet.setUser(optTweet.get().getUser());
        tweetRepository.save(tweet);
        return tweetMapper.toTweetResponseDto(tweet);
    }
    tweetRepository.save(tweet);
    return tweetMapper.toTweetResponseDto(tweet);
    }

    @Override
    public TweetResponseDto update(Long id, TweetPatchRequestDto tweetPatchRequestDto) {
        Tweet tweetToUpdate = tweetRepository
        .findById(id)
        .orElseThrow(()-> new TweetNotFoundException(id + " id'li kullanıcı bulunamadı."));

        tweetMapper.updateEntity(tweetToUpdate, tweetPatchRequestDto);
        tweetRepository.save(tweetToUpdate);
        return tweetMapper.toTweetResponseDto(tweetToUpdate);
    }

    @Override
    public void deleteById(Long id) {

         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String email = authentication.getName();

User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("Kullanıcı bulunamadı"));

  Tweet tweet = tweetRepository.findById(id)
        .orElseThrow(() -> new TweetNotFoundException(id + " id'li tweet bulunamadı."));

    if (tweet.getUser() == null || user.getId() != tweet.getUser().getId()) {
        throw new UnauthorizedDeleteException("Bu tweet'i silmeye yetkiniz yok.",HttpStatus.FORBIDDEN );
    }

    tweetRepository.deleteById(id);
    
    }
    
    @Override
    public List<UserResponseDto> getLikedUsers(Long id) {
       Optional<Tweet> optTweet = tweetRepository.findById(id);
       if(optTweet.isPresent()) {
        return optTweet
        .get()
        .getLikes()        
        .stream()
        .map(like -> userMapper.toUserResponseDto(like.getUser()))
        .toList();
       }
       throw new TweetNotFoundException(id + " id'li tweet bulunamadı.");
    }

}
