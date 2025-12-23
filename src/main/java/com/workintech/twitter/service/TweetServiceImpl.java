package com.workintech.twitter.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workintech.twitter.dto.request.TweetPatchRequestDto;
import com.workintech.twitter.dto.request.TweetRequestDto;
import com.workintech.twitter.dto.response.TweetResponseDto;
import com.workintech.twitter.entity.Tweet;
import com.workintech.twitter.exceptions.TweetNotFoundException;
import com.workintech.twitter.mapper.TweetMapper;
import com.workintech.twitter.repository.TweetRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TweetServiceImpl implements TweetService {

    @Autowired
    private final TweetRepository tweetRepository;

    @Autowired
    private final TweetMapper tweetMapper;

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
       tweet = tweetRepository.save(tweet);
       return tweetMapper.toTweetResponseDto(tweet); 
    }

    @Override
    public TweetResponseDto replaceOrCreate(Long id, TweetRequestDto tweetRequestDto) {
        Tweet tweet = tweetMapper.toEntity(tweetRequestDto);
        Optional<Tweet> optTweet = tweetRepository.findById(id);
        if(optTweet.isPresent()) {
            tweet.setId(id);
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
    tweetRepository.deleteById(id);
    }

}
