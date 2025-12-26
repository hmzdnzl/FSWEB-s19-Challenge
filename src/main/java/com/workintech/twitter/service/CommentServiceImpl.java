package com.workintech.twitter.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.workintech.twitter.dto.request.CommentPatchRequestDto;
import com.workintech.twitter.dto.request.CommentRequestDto;
import com.workintech.twitter.dto.response.CommentResponseDto;
import com.workintech.twitter.entity.Comment;
import com.workintech.twitter.entity.Tweet;
import com.workintech.twitter.entity.User;
import com.workintech.twitter.exceptions.CommentNotFoundException;
import com.workintech.twitter.exceptions.TweetNotFoundException;
import com.workintech.twitter.exceptions.UserNotFoundException;
import com.workintech.twitter.mapper.CommentMapper;
import com.workintech.twitter.repository.CommentRepository;
import com.workintech.twitter.repository.TweetRepository;
import com.workintech.twitter.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    
    @Autowired
    private final CommentRepository commentRepository;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final TweetRepository tweetRepository;

    @Autowired
    private final CommentMapper commentMapper;
    
    @Override
    public List<CommentResponseDto> getAll() {
       return commentRepository
       .findAll()
       .stream()
       .map(commentMapper::toCommentResponseDto)
       .toList();
    }

    @Override
    public CommentResponseDto findById(Long id) {
        Optional<Comment> optComment = commentRepository.findById(id);
        if(optComment.isPresent()) {
            Comment comment = optComment.get();
            return commentMapper.toCommentResponseDto(comment);
        }
          throw new CommentNotFoundException(id+" id'li comment bulunamadı.");  
    }

    @Override
    public CommentResponseDto create(CommentRequestDto commentRequestDto) {
      Comment comment = commentMapper.toEntity(commentRequestDto);

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String email = authentication.getName();
    User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new UserNotFoundException("Kullanıcı bulunamadı"));

    Tweet tweet = tweetRepository.findById(commentRequestDto.tweetId())
        .orElseThrow(() -> new TweetNotFoundException("Tweet bulunamadı"));

    comment.setUser(user);
    comment.setTweet(tweet);

    comment = commentRepository.save(comment);
    return commentMapper.toCommentResponseDto(comment);      
    }

    @Override
    public CommentResponseDto replaceOrCreate(Long id, CommentRequestDto commentRequestDto) {
          Comment comment = commentMapper.toEntity(commentRequestDto);
        Optional<Comment> optComment = commentRepository.findById(id);
        if(optComment.isPresent()) {
            comment.setId(id);
            comment.setUser(optComment.get().getUser());
            comment.setTweet(optComment.get().getTweet());
            commentRepository.save(comment);
            return commentMapper.toCommentResponseDto(comment);
        }
        commentRepository.save(comment);
        return commentMapper.toCommentResponseDto(comment);
    }

    @Override
    public CommentResponseDto update(Long id, CommentPatchRequestDto commentPatchRequestDto) {
    
         Comment comentToUpdate = commentRepository
        .findById(id)
        .orElseThrow(()-> new CommentNotFoundException(id+" id'li comment bulunamadı."));      

        commentMapper.updateEntity(comentToUpdate, commentPatchRequestDto);
        commentRepository.save(comentToUpdate);
        return commentMapper.toCommentResponseDto(comentToUpdate);
    }
    

    @Override
    public void deleteById(Long id) {
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String email = authentication.getName();
    User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new UserNotFoundException("Kullanıcı bulunamadı"));

    Comment comment = commentRepository.findById(id)
        .orElseThrow(() -> new CommentNotFoundException(id + " id'li comment bulunamadı."));
if (comment.getUser() == null || user.getId() != comment.getUser().getId()) {
    throw new RuntimeException("Bu yorumu silmeye yetkiniz yok.");
}

    commentRepository.deleteById(id);
    }

}
