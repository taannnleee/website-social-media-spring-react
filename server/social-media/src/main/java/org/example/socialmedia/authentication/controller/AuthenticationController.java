package org.example.socialmedia.authentication.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.socialmedia.authentication.dto.request.LoginRequest;
import org.example.socialmedia.authentication.dto.request.RegistrationRequest;
import org.example.socialmedia.authentication.dto.response.ResponseData;
import org.example.socialmedia.authentication.dto.response.TokenRespone;
import org.example.socialmedia.authentication.exception.UserNotFoundException;
import org.example.socialmedia.authentication.repositories.UserRepository;
import org.example.socialmedia.authentication.service.Impl.AuthencationService;
import org.example.socialmedia.authentication.service.UserService;
import org.example.socialmedia.common.Enum.EMessage;
import org.example.socialmedia.sendEmail.service.EmailService;
import org.example.socialmedia.sendEmail.utils.OTPGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class AuthenticationController {
    private final UserService userService;
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final AuthencationService authencationService;

//    @GetMapping("/getAllUser")
//    public ResponseData getAllUser() {
//        log.info("getAllUser");
//        return new ResponseData<>(HttpStatus.CREATED.value(), "Success",userService.getAllUser());
//    }

    @GetMapping("/getAllUser")
    public ResponseData<?> getAllUser() {
        return new ResponseData<>(HttpStatus.OK.value(), "Get all user success", userService.getAllUser());
    }

    @PostMapping("/register")
    public ResponseData<?> registerUser(@RequestBody RegistrationRequest registrationRequest) {
        try {
            System.out.println(registrationRequest.getEmail());
            System.out.println(registrationRequest.getPassword());
            System.out.println(registrationRequest.getPhone());
            System.out.println(registrationRequest.getFullname());
            userService.checkUserExist(registrationRequest.getUsername(),registrationRequest.getPhone());
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), "Phone number and username already exists");
        } catch (UserNotFoundException e) {
            userService.registerUser(registrationRequest);
            return new ResponseData<>(HttpStatus.OK.value(), "User registered successfully");
        }
    }


    @PostMapping("/refresh")
    public ResponseData<TokenRespone> refreshToken(HttpServletRequest request) {
        return new ResponseData<>(HttpStatus.OK.value(), "Login Success", authencationService.refresh(request));
    }
    @PostMapping("/login")
    public ResponseData<TokenRespone> login(@RequestBody LoginRequest loginRequest) {
        return new ResponseData<>( HttpStatus.OK.value(),"Login Success",authencationService.authentication(loginRequest));
//
//        System.out.println("hihi");
//        System.out.println(loginRequest.getPhoneNumber());
//        System.out.println(loginRequest.getPassword());
//        User user = userService.checkLogin(loginRequest);
//        if (user != null) {
//            return new ResponseData<>(HttpStatus.OK.value(), "Login successful");
//        } else {
//            return new ResponseData<>(HttpStatus.UNAUTHORIZED.value(), "Invalid phone number or password");
//        }
    }

    @PostMapping("/logout")
    public ResponseData<?> logout(HttpServletRequest request) {
        return new ResponseData<>(HttpStatus.OK.value(), "Logout Success", authencationService.logout(request));
//

    }


    @PostMapping("/otp")
    public ResponseData<?> sendOTP(@RequestParam String email,
                                   HttpSession session,
                                   Model model) {
        try {
            String OTP = OTPGenerator.generateOTP();
            emailService.sendEmail(email, EMessage.TITLE_OTP.getValue(), EMessage.TEXT_EMAIL_OTP.getValue() + OTP);
            session.setAttribute("OTP", OTP);
            session.setAttribute("OTP_EMAIL", email);
            return new ResponseData<>(HttpStatus.OK.value(), "Success");
        } catch (UserNotFoundException e) {
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), EMessage.CUSTOMER_NOT_EXIST.getValue());
        }
    }


    @PostMapping("/verify-otp")
    public ResponseData<?> verifyOTP(@RequestParam String otp,
                                     HttpSession session) {
        try {
            // Lấy OTP từ session
            String sessionOtp = (String) session.getAttribute("OTP");
            String sessionEmail = (String) session.getAttribute("OTP_EMAIL");

            if (sessionOtp == null || sessionEmail == null) {
                return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), "OTP session has expired or is invalid");
            }

            if (sessionOtp.equals(otp)) {
                session.removeAttribute("OTP");
                session.removeAttribute("OTP_EMAIL");
                return new ResponseData<>(HttpStatus.OK.value(), "Success");
            } else {
                return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), "Invalid OTP");
            }

        } catch (Exception e) {
            return new ResponseData<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An error occurred while verifying OTP");
        }
    }

    @PostMapping("/changePassword")
    public ResponseData<?> changePassword(@RequestParam String email,
                                          @RequestParam String newPassword) {
        try {
            boolean isChanged = userService.changePassword(email, newPassword);

            if (isChanged) {
                return new ResponseData<>(HttpStatus.OK.value(), "Password changed successfully");
            } else {
                return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), "User not found");
            }
        } catch (Exception e) {
            return new ResponseData<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An error occurred while changing the password");
        }
    }

    @GetMapping("/getProfile")
    public ResponseData<?> getProfile(@PathVariable("id") String id){
        return new ResponseData<>(HttpStatus.OK.value(), "Password changed successfully",userRepository.findById(Long.valueOf(id)));
    }
}