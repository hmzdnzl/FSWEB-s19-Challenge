package com.workintech.twitter.service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.workintech.twitter.dto.request.LoginRequestDto;
import com.workintech.twitter.dto.request.RegisterRequestDto;
import com.workintech.twitter.dto.response.LoginResponseDto;
import com.workintech.twitter.dto.response.RegisterResponseDto;
import com.workintech.twitter.entity.Role;
import com.workintech.twitter.entity.User;
import com.workintech.twitter.exceptions.IncorrectPasswordException;
import com.workintech.twitter.exceptions.UserAlreadyRegisteredException;
import com.workintech.twitter.exceptions.UserNotFoundException;
import com.workintech.twitter.repository.RoleRepository;
import com.workintech.twitter.repository.UserRepository;
import com.workintech.twitter.security.JwtUtil;

@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;



    @Override
    public RegisterResponseDto register(RegisterRequestDto registerRequestDto) {
        Optional<User> optUser = userRepository.findByEmail(registerRequestDto.email()); 
            if(optUser.isPresent()) {
                throw new UserAlreadyRegisteredException("Email zaten mevcut");
            }
        
            String encodedPassword = passwordEncoder.encode(registerRequestDto.password());
            
           Role userRole = roleRepository.getRoleByAuthority("user");

           User user = new User();
           user.setPassword(encodedPassword);
           user.setEmail(registerRequestDto.email());
           user.setNickName(registerRequestDto.nickName());
           user.setRoles(Set.of(userRole));
           user.setSignUpDate(LocalDate.now());

           userRepository.save(user);
            
           return new RegisterResponseDto(registerRequestDto.email(), "Kullanıcı başarıyla kaydedildi");
    }

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
     User loggedUser = userRepository.findByEmail(loginRequestDto.email())
                        .orElseThrow(()-> new UserNotFoundException("Bu emaile sahip kullanıcı bulunamadı."));

        if(!passwordEncoder.matches(loginRequestDto.password(), loggedUser.getPassword())) {
            throw new IncorrectPasswordException("Parola hatalı",HttpStatus.UNAUTHORIZED);
        }
  
String token = jwtUtil.generateToken(loggedUser);

        return new LoginResponseDto(
            loggedUser.getEmail(),
            loggedUser.getNickName(),
            "Oturum açıldı",
            token      
        );
    }

}
