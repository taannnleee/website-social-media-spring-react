package org.example.socialmedia.authentication.service.Impl;
import org.example.socialmedia.authentication.dto.request.LoginRequest;
import org.example.socialmedia.authentication.dto.request.RegistrationRequest;
import org.example.socialmedia.authentication.exception.UserNotFoundException;
import org.example.socialmedia.authentication.repositories.UserRepository;
import org.example.socialmedia.authentication.service.UserService;
import org.example.socialmedia.common.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

//    @Override
//    public UserDetailsService userDetailsService() {
//        return username -> userRepository.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("User not found"));
//        return null;
//    }

    @Override
    public RegistrationRequest registerUser(RegistrationRequest registrationRequest) {
        User user = new User();
        user.setEmail(registrationRequest.getUsername());
        user.setPassword(registrationRequest.getPassword());
        user.setPhone(registrationRequest.getPhoneNumber());
        userRepository.save(user);
        return registrationRequest;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
//        return userRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("User not found"));
    }

    @Override
    public User findByPhone(String phoneNumber) {
        return userRepository.findByPhone(phoneNumber)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
//        return userRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("User not found"));
    }

    @Override
    public User checkLogin(LoginRequest loginRequest) {
        User user = userRepository.findByPhone(loginRequest.getPhoneNumber()).orElse(null);
        if (user != null) {
            if (loginRequest.getPassword().equals(user.getPassword())) {
                return user;
            }
        }
        return null;
    }

}
