package com.workintech.twitter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


import com.workintech.twitter.exceptions.UserNameNotFoundException;
import com.workintech.twitter.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UserNameNotFoundException {
    return userRepository
    .findByEmail(username)
    .orElseThrow(()-> new UserNameNotFoundException("Kullanıcı bulunamadı", HttpStatus.NOT_FOUND));
    }

}
