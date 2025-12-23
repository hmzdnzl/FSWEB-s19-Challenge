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
import com.workintech.twitter.mapper.UserMapper;
import com.workintech.twitter.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

@Autowired
private final UserRepository userRepository;

@Autowired
private final UserMapper userMapper;


    @Override
    public List<UserResponseDto> getAll() {
     return userRepository
     .findAll()
     .stream()
     .map(userMapper::toUserResponseDto)
     .toList();
    }

    @Override
    public UserResponseDto findById(Long id) {
      Optional<User> optUser = userRepository.findById(id);
      if(optUser.isPresent()) {
        User user = optUser.get();
        return userMapper.toUserResponseDto(user);
      }
      throw new UserNotFoundException(id + " id'li kullanıcı bulunamadı.");
    }

    @Override
    public UserResponseDto create(UserRequestDto userRequestDto) {
  User user = userMapper.toEntity(userRequestDto);
   user = userRepository.save(user);
   return userMapper.toUserResponseDto(user);

    }

    @Override
    public UserResponseDto replaceOrCreate(Long id, UserRequestDto userRequestDto) {
    User user = userMapper.toEntity(userRequestDto);
     Optional<User> optUser = userRepository.findById(id);
     if(optUser.isPresent()) {
        user.setId(id);
        userRepository.save(user);
        return userMapper.toUserResponseDto(user);
     }
      userRepository.save(user);
 return new UserResponseDto(user.getEmail(),user.getNickName());
    }

    @Override
    public UserResponseDto update(Long id, UserPatchRequestDto userPatchRequestDto) {
     User userToUpdate = userRepository
     .findById(id)
     .orElseThrow(()-> new UserNotFoundException(id + " id'li kullanıcı bulunamadı."));

      userMapper.updateEntity(userToUpdate, userPatchRequestDto);

        userRepository.save(userToUpdate);

        return userMapper.toUserResponseDto(userToUpdate);
    }

    @Override
    public void deleteById(Long id) {
      userRepository.deleteById(id);
    }

}
