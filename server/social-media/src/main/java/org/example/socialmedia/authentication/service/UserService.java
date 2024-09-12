package org.example.socialmedia.authentication.service;


import org.example.socialmedia.authentication.dto.request.LoginRequest;
import org.example.socialmedia.authentication.dto.request.RegistrationRequest;
import org.example.socialmedia.authentication.dto.response.ResponseData;
import org.example.socialmedia.authentication.dto.response.TokenRespone;
import org.example.socialmedia.common.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    List<User> getAllUser();
    User findByUserName(String username);
    User checkUserExist(String username, String phoneNumber);
    User findByEmail(String email);
    ResponseData<?> registerUser(RegistrationRequest registrationRequest);
    User findByPhone(String phoneNumber);
    User checkLogin(LoginRequest loginRequest);
    boolean changePassword(String email, String newPassword);
    UserDetailsService userDetailsService();
}
