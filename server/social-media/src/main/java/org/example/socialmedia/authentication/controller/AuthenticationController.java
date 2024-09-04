package org.example.socialmedia.authentication.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.socialmedia.authentication.dto.request.LoginRequest;
import org.example.socialmedia.authentication.dto.request.RegistrationRequest;
import org.example.socialmedia.authentication.exception.UserNotFoundException;
import org.example.socialmedia.authentication.service.UserService;
import org.example.socialmedia.common.entities.Address;
import org.example.socialmedia.common.entities.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class AuthenticationController {
    private final UserService userService;

//    @GetMapping("/getAllUser")
//    public ResponseData getAllUser() {
//        log.info("getAllUser");
//        return new ResponseData<>(HttpStatus.CREATED.value(), "Success",userService.getAllUser());
//    }

    @GetMapping("/getAllUser")
    public ResponseEntity<?> getAllUser() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Update Success");
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegistrationRequest registrationRequest) {
        try {
            userService.findByPhone(registrationRequest.getPhoneNumber());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Phone number already exists");
        } catch (UserNotFoundException e) {
            userService.registerUser(registrationRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
        }
    }

//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
//        User user = userService.checkLogin(loginRequest);
//        if (user != null) {
//            return ResponseEntity.status(HttpStatus.OK).body("Login successful");
//        } else {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid phone number or password");
//        }
//    }
}

