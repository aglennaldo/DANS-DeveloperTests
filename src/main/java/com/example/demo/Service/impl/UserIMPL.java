package com.example.demo.Service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Dto.LoginDTO;
import com.example.demo.Dto.UserDTO;
import com.example.demo.Entity.User;
import com.example.demo.Repo.UserRepository;
import com.example.demo.Service.UserService;
import com.example.demo.response.LoginResponse;

@Service
public class UserIMPL implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String addUser(UserDTO userDTO) {
        
        User user = new User(
            userDTO.getId(),
            userDTO.getEmail(),            
            userDTO.getName(),
            this.passwordEncoder.encode(userDTO.getPassword())
        );

        userRepository.save(user);
        return user.getName();
    }

    UserDTO userDTO;
    @Override
    public LoginResponse loginUser(LoginDTO loginDTO) {
     
        String msg = "";
        User user1 = userRepository.findByEmail(loginDTO.getEmail());
        if (user1 != null) {
            String password = loginDTO.getPassword();
            String encodedPassword = user1.getPassword();
            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
            if (isPwdRight) {
                Optional<User> user = userRepository.findOneByEmailAndPassword(loginDTO.getEmail(), encodedPassword);
                if (user.isPresent()) {
                    return new LoginResponse("Login Success", true);
                } else {
                    return new LoginResponse("Login Failed", false);
                }
            } else {
                return new LoginResponse("password Not Match", false);
            }
        }else {
            return new LoginResponse("Email not exits", false);
        }
    }
} 
