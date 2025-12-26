package com.workintech.twitter.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.workintech.twitter.dto.request.UserPatchRequestDto;
import com.workintech.twitter.dto.request.UserRequestDto;
import com.workintech.twitter.dto.response.TweetResponseDto;
import com.workintech.twitter.dto.response.UserResponseDto;
import com.workintech.twitter.entity.Tweet;
import com.workintech.twitter.entity.User;
import com.workintech.twitter.exceptions.TweetNotFoundException;
import com.workintech.twitter.exceptions.UserNotFoundException;
import com.workintech.twitter.mapper.TweetMapper;
import com.workintech.twitter.mapper.UserMapper;
import com.workintech.twitter.repository.TweetRepository;
import com.workintech.twitter.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

@Autowired
private final UserRepository userRepository;

@Autowired TweetRepository tweetRepository;

@Autowired
private final UserMapper userMapper;

@Autowired 
private final TweetMapper tweetMapper;


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

    @Override
    public List<UserResponseDto> searchByNickName(String nickName) {
    return userRepository
    .searchByNickName(nickName)
    .stream()
    .map(userMapper::toUserResponseDto)
    .toList();
    }

    @Override
    public void removeTweet(Long userId, Long tweetId) {
     User user = userRepository
     .findById(userId)
     .orElseThrow(()-> new UserNotFoundException(userId + " id'li kullanıcı bulunamadı"));

     Tweet tweet = tweetRepository.findById(tweetId)
     .orElseThrow(()-> new TweetNotFoundException(tweetId + " id'li tweet bulunamadı"));

     user.removeTweet(tweet);
     tweet.removeUser(user);

     userRepository.save(user);
     
    }

    @Override
    public List<TweetResponseDto> getAllTweets(Long userId) {
         User user = userRepository
     .findById(userId)
     .orElseThrow(()-> new UserNotFoundException(userId + " id'li kullanıcı bulunamadı"));

         return user.getTweets()
        .stream()
        .map(tweetMapper::toTweetResponseDto)
        .toList();
}

    

}
