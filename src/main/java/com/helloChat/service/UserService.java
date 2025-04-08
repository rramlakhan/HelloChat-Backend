package com.helloChat.service;

import com.helloChat.entity.User;
import com.helloChat.exception.UserAlreadyExistsException;
import com.helloChat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User saveUser(User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new UserAlreadyExistsException("Email already exists.");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public boolean resetPassword(User user) {
        User savedUser = userRepository.findByEmail(user.getEmail());
        if (savedUser != null) {
            savedUser.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(savedUser);
            return true;
        }
        return false;
    }
}
