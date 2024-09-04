package org.example.socialmedia.authentication.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.socialmedia.authentication.dto.request.LoginRequest;
import org.example.socialmedia.authentication.dto.request.RegistrationRequest;
import org.example.socialmedia.authentication.dto.response.ResponseData;
import org.example.socialmedia.authentication.exception.UserNotFoundException;
import org.example.socialmedia.authentication.repositories.UserRepository;
import org.example.socialmedia.authentication.service.UserService;
import org.example.socialmedia.common.Enum.EMessage;
import org.example.socialmedia.common.entities.Address;
import org.example.socialmedia.common.entities.User;
import org.example.socialmedia.sendEmail.service.EmailService;
import org.example.socialmedia.sendEmail.utils.OTPGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class AuthenticationController {
    private final UserService userService;
    private final UserRepository userRepository;
    private final EmailService emailService;

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
            userService.findByPhone(registrationRequest.getPhone());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Phone number already exists");
        } catch (UserNotFoundException e) {
            userService.registerUser(registrationRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        User user = userService.checkLogin(loginRequest);
        if (user != null) {
            return ResponseEntity.status(HttpStatus.OK).body("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid phone number or password");
        }
    }


    @PostMapping("/otp")
    public ResponseData<?> sendOTP(@RequestParam String email,
                                   HttpSession session,
                                   Model model){
        try{
            User user = userService.findByEmail(email);
            String OTP = OTPGenerator.generateOTP();
            emailService.sendEmail(email,EMessage.TITLE_OTP.getValue(),EMessage.TEXT_EMAIL_OTP.getValue()+OTP);
            return new ResponseData<>(HttpStatus.CREATED.value(), "Success");
        }catch (UserNotFoundException e){
            model.addAttribute("result", EMessage.CUSTOMER_NOT_EXIST.getValue());
            return new ResponseData<>(HttpStatus.CREATED.value(), EMessage.CUSTOMER_NOT_EXIST.getValue());
        }
    }
}

