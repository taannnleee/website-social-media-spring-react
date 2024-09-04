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
    public ResponseData<?> registerUser(@RequestBody RegistrationRequest registrationRequest) {
        try {
            System.out.println("hahaa");
            System.out.println(registrationRequest.getEmail());
            System.out.println(registrationRequest.getPassword());
            System.out.println(registrationRequest.getPhone());
            System.out.println(registrationRequest.getFullname());
            userService.findByPhone(registrationRequest.getPhone());
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), "Phone number already exists");
        } catch (UserNotFoundException e) {
            userService.registerUser(registrationRequest);
            return new ResponseData<>(HttpStatus.OK.value(), "User registered successfully");
        }
    }

    @PostMapping("/login")
    public ResponseData<?> login(@RequestBody LoginRequest loginRequest) {
        System.out.println("hihi");
        System.out.println(loginRequest.getPhoneNumber());
        System.out.println(loginRequest.getPassword());
        User user = userService.checkLogin(loginRequest);
        if (user != null) {
            return new ResponseData<>(HttpStatus.OK.value(), "Login successful");
        } else {
            return new ResponseData<>(HttpStatus.UNAUTHORIZED.value(), "Invalid phone number or password");
        }
    }

    @GetMapping("/test1")
    public ResponseData<?> test() {
        return new ResponseData<>(HttpStatus.OK.value(), "OKELA");
    }



    @PostMapping("/otp")
    public ResponseData<?> sendOTP(@RequestParam String email,
                                   HttpSession session,
                                   Model model){
        try{
            String OTP = OTPGenerator.generateOTP();
            emailService.sendEmail(email,EMessage.TITLE_OTP.getValue(),EMessage.TEXT_EMAIL_OTP.getValue()+OTP);
            session.setAttribute("OTP", OTP);
            session.setAttribute("OTP_EMAIL", email);
            return new ResponseData<>(HttpStatus.OK.value(), "Success");
        }catch (UserNotFoundException e){
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), EMessage.CUSTOMER_NOT_EXIST.getValue());
        }
    }

    @PostMapping("/otp")
    public ResponseData<?> verifyOTP(@RequestParam String otp,
                                   HttpSession session,
                                   Model model){
        try{
            // Lấy OTP từ session
            String sessionOtp = (String) session.getAttribute("OTP");
            String sessionEmail = (String) session.getAttribute("OTP_EMAIL");

            if(sessionOtp.equals(otp)){
                return new ResponseData<>(HttpStatus.OK.value(), "Success");
            }else {
                return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), EMessage.CUSTOMER_NOT_EXIST.getValue());
            }

        }catch (UserNotFoundException e){
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), EMessage.CUSTOMER_NOT_EXIST.getValue());
        }
    }
}

