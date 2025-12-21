package com.workintech.twitter.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workintech.twitter.dto.request.UserPatchRequestDto;
import com.workintech.twitter.dto.request.UserRequestDto;
import com.workintech.twitter.dto.response.UserResponseDto;
import com.workintech.twitter.entity.User;
import com.workintech.twitter.exceptions.UserNotFoundException;
import com.workintech.twitter.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

@Autowired
private final UserRepository userRepository;


    @Override
    public List<UserResponseDto> getAll() {
     return userRepository
     .findAll()
     .stream()
     .map(user -> new UserResponseDto(user.getEmail(), user.getNickName()))
     .toList();
    }

    @Override
    public UserResponseDto findById(Long id) {
      Optional<User> optUser = userRepository.findById(id);
      if(optUser.isPresent()) {
        User user = optUser.get();
        return new UserResponseDto(user.getEmail(),user.getNickName() );
      }
      throw new UserNotFoundException(id + " id'li kullanıcı bulunamadı.");
    }

    @Override
    public UserResponseDto create(UserRequestDto userRequestDto) {
   User user = new User();
   user.setEmail(userRequestDto.email());
   user.setNickName(userRequestDto.nickName());
   user = userRepository.save(user);
   return new UserResponseDto(user.getEmail(),user.getNickName());

    }

    @Override
    public UserResponseDto replaceOrCreate(Long id, UserRequestDto userRequestDto) {
     User user = new User();
     user.setEmail(userRequestDto.email());
     user.setNickName(userRequestDto.nickName());
     Optional<User> optUser = userRepository.findById(id);
     if(optUser.isPresent()) {
        user.setId(id);
        userRepository.save(user);
        return new UserResponseDto(user.getEmail(),user.getNickName());
     }
      userRepository.save(user);
 return new UserResponseDto(user.getEmail(),user.getNickName());
    }

    @Override
    public UserResponseDto update(Long id, UserPatchRequestDto userPatchRequestDto) {
     User userUpdated = userRepository
     .findById(id)
     .orElseThrow(()-> new UserNotFoundException(id + " id'li kullanıcı bulunamadı."));

        if(userPatchRequestDto.email() != null) {
            userUpdated.setEmail(userPatchRequestDto.email());
        }

        if(userPatchRequestDto.nickName() != null) { 
            userUpdated.setNickName(userPatchRequestDto.nickName());
        }

        userRepository.save(userUpdated);

        return new UserResponseDto(userUpdated.getEmail(),userUpdated.getNickName());
    }

    @Override
    public void deleteById(Long id) {
      userRepository.deleteById(id);
    }

}
