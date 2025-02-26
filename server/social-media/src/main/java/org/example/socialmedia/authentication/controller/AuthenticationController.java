package org.example.socialmedia.authentication.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.socialmedia.authentication.dto.request.LoginRequest;
import org.example.socialmedia.authentication.dto.request.RegistrationRequest;
import org.example.socialmedia.authentication.dto.request.ResetPasswordDTO;
import org.example.socialmedia.authentication.dto.response.ResponseData;
import org.example.socialmedia.authentication.dto.response.TokenRespone;
import org.example.socialmedia.authentication.exception.*;
import org.example.socialmedia.authentication.repositories.UserRepository;
import org.example.socialmedia.authentication.service.Impl.AuthencationService;
import org.example.socialmedia.authentication.service.UserService;
import org.example.socialmedia.common.Enum.EMessage;
import org.example.socialmedia.sendEmail.service.EmailService;
import org.example.socialmedia.sendEmail.utils.OTPGenerator;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
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
        System.out.println("hihi");
        System.out.println("hihi");
        System.out.println("hihi");
         System.out.println("hihi");
        
        return new ResponseData<>(HttpStatus.OK.value(), "Get all user success", userService.getAllUser());
    }

    @PostMapping("/register")
    public ResponseData<?> registerUser(@RequestBody RegistrationRequest registrationRequest) {
        try {
            userService.checkUser(registrationRequest);
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), "Phone number,username or email already exists");
        }
        catch (InvalidPasswordException e) {
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), "Passwords do not match");
        }
        catch (UserNotFoundException e) {
            RegistrationRequest rp = userService.registerUser(registrationRequest);
            return new ResponseData<>(HttpStatus.OK.value(), "User registered successfully",rp);
        }
    }

    @PostMapping("verifyOTP_register")
    public ResponseData<TokenRespone> verifyOTPRegister(String OTP,  String email) {
        try {
            authencationService.verifyOTP_register(OTP, email);
            return new ResponseData<>(HttpStatus.OK.value(), "OTP is valid. Proceed with register.");
        } catch (UserNotFoundException | TokenNotFoundException | InvalidOtpException e) {
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @PostMapping("/refresh")
    public ResponseData<TokenRespone> refreshToken(HttpServletRequest request) {
        return new ResponseData<>(HttpStatus.OK.value(), "Login Success", authencationService.refresh(request));
    }
    @PostMapping("/login")
    public ResponseData<TokenRespone> login(@RequestBody LoginRequest loginRequest) {
        try {
            TokenRespone tokenRespone = authencationService.authentication(loginRequest);
            return new ResponseData<>( HttpStatus.OK.value(),"Login Success",tokenRespone);
        }catch (BadCredentialsException e){
            System.out.println("hihi");
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(),"Bad credentials");
        }
        catch (AccoutIsNotActive e){
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(),"Accout Is Not Active");
        }
    }

    @PostMapping("/logout")
    public ResponseData<?> logout(HttpServletRequest request) {
        return new ResponseData<>(HttpStatus.OK.value(), "Logout Success", authencationService.logout(request));
    }

    @PostMapping("/forgot-password")
    public ResponseData<?> forgotPassword(@RequestBody String email) {
        try {
            String result = authencationService.forgotPassword(email);
            // Trả về phản hồi thành công
            return new ResponseData<>(HttpStatus.OK.value(), "Success"+result);
        } catch (UserNotFoundException e) {
            // Nếu không tìm thấy người dùng
            return new ResponseData<>(HttpStatus.NOT_FOUND.value(), "NOT_FOUND");
        } catch (InvalidDataAccessApiUsageException e) {
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), "BAD_REQUEST");
        } catch (Exception e) {
            // Xử lý các lỗi khác
            return new ResponseData<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "INTERNAL_SERVER_ERROR");
        }
    }

    @PostMapping("/reset-password")
    public ResponseData<?> resetPassword(@RequestBody String OTP,String email) {
        try {
            authencationService.resetPassword(OTP, email);
            return new ResponseData<>(HttpStatus.OK.value(), "OTP is valid. Proceed with password reset.");
        } catch (UserNotFoundException | TokenNotFoundException | InvalidOtpException e) {
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @PostMapping("/change-password")
    public ResponseData<?> changePassword(@RequestBody ResetPasswordDTO request) {
        return new ResponseData<>(HttpStatus.OK.value(), "Success", authencationService.changePassword(request));
    }
    @GetMapping("/test")
    public ResponseData<?> test() {


        System.out.println("hihi");
        System.out.println("hihi");
        System.out.println("tan");
        System.out.println("hihi");
        System.out.println("hihi");
        System.out.println("hihi");
        System.out.println("hihi");
        System.out.println("hihi");
        System.out.println("hihi");
        System.out.println("hihi");
        return new ResponseData<>(HttpStatus.OK.value(), "Success", "ok");

    }


    @GetMapping("/getProfile")
    public ResponseData<?> getProfile(@PathVariable("id") String id){
        return new ResponseData<>(HttpStatus.OK.value(), "Password changed successfully",userRepository.findById(Long.valueOf(id)));
    }

}
