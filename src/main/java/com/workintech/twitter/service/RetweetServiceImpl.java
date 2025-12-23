package com.workintech.twitter.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workintech.twitter.dto.request.RetweetRequestDto;
import com.workintech.twitter.dto.response.RetweetResponseDto;
import com.workintech.twitter.entity.Retweet;
import com.workintech.twitter.exceptions.RetweetNotFoundException;
import com.workintech.twitter.mapper.RetweetMapper;
import com.workintech.twitter.repository.RetweetRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RetweetServiceImpl implements RetweetService {

    @Autowired
    private final RetweetRepository retweetRepository;

    @Autowired
    private final RetweetMapper retweetMapper;

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
         Retweet retweet = retweetMapper.toEntity(retweetRequestDto);
       retweet = retweetRepository.save(retweet);
       return retweetMapper.toRetweetResponseDto(retweet); 
    }
    
    @Override
    public void deleteById(Long id) {
        retweetRepository.deleteById(id);
    }

    
}
