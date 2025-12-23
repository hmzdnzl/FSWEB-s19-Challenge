package com.workintech.twitter.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.workintech.twitter.dto.request.LikeRequestDto;
import com.workintech.twitter.dto.response.LikeResponseDto;
import com.workintech.twitter.entity.Like;
import com.workintech.twitter.exceptions.LikeNotFoundException;
import com.workintech.twitter.mapper.LikeMapper;
import com.workintech.twitter.repository.LikeRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    @Autowired
    private final LikeRepository likeRepository;
    
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
           Like like = likeMapper.toEntity(likeRequestDto);
       like = likeRepository.save(like);
       return likeMapper.toLikeResponseDto(like); 
    }
    

    @Override
    public void deleteById(Long id) {
     likeRepository.deleteById(id);
}
}

