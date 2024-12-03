package org.example.socialmedia.authentication.service;


import org.example.socialmedia.authentication.dto.request.LoginRequest;
import org.example.socialmedia.authentication.dto.request.RegistrationRequest;
import org.example.socialmedia.authentication.dto.request.UpdateProfileRequest;
import org.example.socialmedia.authentication.dto.response.ProfileResponse;
import org.example.socialmedia.authentication.dto.response.ResponseData;
import org.example.socialmedia.common.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUser();
    Optional<User>  findByUserName(String username);

    User checkUser(RegistrationRequest registrationRequest);
    RegistrationRequest registerUser(RegistrationRequest registrationRequest);
    Optional<User> findByPhone(String phoneNumber);
    User checkLogin(LoginRequest loginRequest);
    boolean changePassword(String email, String newPassword);
    UserDetailsService userDetailsService();
    Optional<User> findByEmail(String email);
    long saveUser(User user);

    ProfileResponse getProfile(String id);

    void updateProfile(UpdateProfileRequest updateProfileRequest, String id);
}
