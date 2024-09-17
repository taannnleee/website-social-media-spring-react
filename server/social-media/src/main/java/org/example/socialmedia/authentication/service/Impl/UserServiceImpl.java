package org.example.socialmedia.authentication.service.Impl;
import lombok.RequiredArgsConstructor;
import org.example.socialmedia.authentication.dto.request.LoginRequest;
import org.example.socialmedia.authentication.dto.request.RegistrationRequest;
import org.example.socialmedia.authentication.dto.response.ResponseData;
import org.example.socialmedia.authentication.dto.response.TokenRespone;
import org.example.socialmedia.authentication.exception.InvalidPasswordException;
import org.example.socialmedia.authentication.exception.UserNotFoundException;
import org.example.socialmedia.authentication.repositories.UserRepository;
import org.example.socialmedia.authentication.service.UserService;
import org.example.socialmedia.common.entities.User;
import org.example.socialmedia.common.mapper.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public UserDetailsService userDetailsService() {
        return username ->
                userRepository.findByUsername(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public ResponseData<?> registerUser(RegistrationRequest registrationRequest) {
        String encodedPassword = passwordEncoder.encode(registrationRequest.getPassword());
        User user = Mappers.convertToEntity(registrationRequest, User.class);
        user.setPassword(encodedPassword);
        user.setStatus(false);
        userRepository.save(user);

        return new ResponseData<>(HttpStatus.CREATED.value(), "Success", registrationRequest);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public long saveUser(User user) {
        userRepository.save(user);
        return user.getId();
    }

    @Override
    public Optional<User> findByPhone(String phoneNumber) {
        return userRepository.findByPhone(phoneNumber);
    }

    @Override
    public Optional<User> findByUserName(String username) {
        return userRepository.findByUsername(username);
    }


    @Override
    public User checkUser(RegistrationRequest registrationRequest) {
        if (!registrationRequest.getPassword().equals(registrationRequest.getConfirmpassword())) {
            throw new InvalidPasswordException("Passwords do not match");
        }

        Optional<User> optionalUserByUsername = findByUserName(registrationRequest.getUsername());

        Optional<User> optionalUserByPhone = findByPhone(registrationRequest.getPhone());

        Optional<User>optionalUserByEmail = findByEmail(registrationRequest.getEmail());

        if (optionalUserByUsername.isPresent()) {
            return optionalUserByUsername.get();
        }

        if (optionalUserByPhone.isPresent()) {
            return optionalUserByPhone.get();
        }

        if (optionalUserByEmail.isPresent()) {
            return optionalUserByEmail.get();
        }

        throw new UserNotFoundException("Username, phone or email number not found.");
    }


    @Override
    public User checkLogin(LoginRequest loginRequest) {
        User user = userRepository.findByPhone(loginRequest.getUsername()).orElse(null);
        if (user != null) {
            if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                return user;
            }
        }
        return null;
    }

    @Override
    public boolean changePassword(String email, String newPassword) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setPassword(newPassword);
            userRepository.save(user);
            return true;
        } else {
            return false;
        }
    }

}
