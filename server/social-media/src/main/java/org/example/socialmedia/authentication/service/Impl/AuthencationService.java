package org.example.socialmedia.authentication.service.Impl;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.example.socialmedia.authentication.dto.request.LoginRequest;
import org.example.socialmedia.authentication.dto.request.ResetPasswordDTO;
import org.example.socialmedia.authentication.dto.response.ResponseData;
import org.example.socialmedia.authentication.dto.response.TokenRespone;
import org.example.socialmedia.authentication.exception.UserNotFoundException;
import org.example.socialmedia.authentication.repositories.UserRepository;
import org.example.socialmedia.authentication.service.JwtService;
import org.example.socialmedia.authentication.service.TokenService;
import org.example.socialmedia.authentication.service.UserService;
import org.example.socialmedia.common.Enum.EMessage;
import org.example.socialmedia.common.Enum.ETokenType;
import org.example.socialmedia.common.entities.Token;
import org.example.socialmedia.common.entities.User;
import org.example.socialmedia.sendEmail.service.EmailService;
import org.example.socialmedia.sendEmail.utils.OTPGenerator;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.sound.midi.InvalidMidiDataException;
import java.io.StringReader;
import java.util.Optional;

import static org.example.socialmedia.common.Enum.ETokenType.*;

@Service
@RequiredArgsConstructor
public class AuthencationService {
    private final AuthenticationManager authenticationManager;
    private  final UserRepository userRepository;
    private  final JwtService jwtService;
    private final TokenService tokenService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    public TokenRespone authentication(LoginRequest loginRequest){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        }
        catch (BadCredentialsException e) {
            throw new RuntimeException("Username or Password is incorrect", e);
        }

        var user = userRepository.findByUsername(loginRequest.getUsername()).orElseThrow(() -> new UsernameNotFoundException("Username or Password is incorrect"));

        String accessToken =  jwtService.generateToken(user);
        String refresh_token =  jwtService.generateRefreshToken(user);

        //save token vào db
        tokenService.save(Token.builder()
                        .username(user.getUsername())
                        .accessToken(accessToken)
                        .refreshToken(refresh_token)
                        .build());

        return TokenRespone.builder()
                .accesstoken(accessToken)
                .refreshtoken(refresh_token)
                .userid(user.getId())
                .build();
    }

    public TokenRespone refresh(HttpServletRequest loginRequest) {
        //validate xem token cos rỗng không
        String refresh_token = loginRequest.getHeader("x-token");
        if(StringUtils.isBlank(refresh_token)){
            throw new InvalidDataAccessApiUsageException("Token is empty");
        }
        //extract user from token
        final String userName = jwtService.extractUsername(refresh_token, REFRESHTOKEN);
        System.out.println("userName" +userName);

        //check it into db
        Optional<User> user =  userRepository.findByUsername(userName);
        System.out.println("userID: "+ user.get().getId());


        //validate xem token có hợp lệ không
        if(!jwtService.isValid(refresh_token, REFRESHTOKEN,user.get())){
            throw new InvalidDataAccessApiUsageException("Token is invalid");
        }

        String accessToken =  jwtService.generateToken(user.get());

        return TokenRespone.builder()
                .accesstoken(accessToken)
                .refreshtoken(refresh_token)
                .userid(user.get().getId())
                .build();
    }

    public String logout(HttpServletRequest request) {
        //validate xem token cos rỗng không
        String refresh_token = request.getHeader("x-token");
        if(StringUtils.isBlank(refresh_token)){
            throw new InvalidDataAccessApiUsageException("Token is empty");
        }

        //extract user from token
        final String userName = jwtService.extractUsername(refresh_token, ACCESSTOKEN);

        //check token in db
        Token tokenCurrent = tokenService.getTokenByUsername(userName);

        //delete token
        tokenService.delete(tokenCurrent);
        return "delete!";
    }

    public String forgotPassword(String email) {
        //check email exist or not
        Optional<User> user = userService.findByEmail(email);

        //user is active or inactivated
        if(user.isPresent()){
            if(!user.get().isEnabled()){
                throw new InvalidDataAccessApiUsageException("User is active");
            }
        }


        //generate reset token
//        String resetToken = jwtService.generateResetToken(user.get());

        //send email confirm
        String OTP = OTPGenerator.generateOTP();
        emailService.sendEmail(email, EMessage.TITLE_OTP.getValue(), EMessage.TEXT_EMAIL_OTP.getValue() + OTP);

        // save into database OTP
        Token token = tokenService.getTokenByUsername(user.get().getUsername());
        token.setOTP(OTP);
        tokenService.save(token);

        String confirmLink="";
        System.out.println("sent");
        return "Sent";

    }

    public ResponseData<?> resetPassword(String OTP, String email) {
        Optional<User> user = userService.findByEmail(email);
        if (user.isPresent()) {
            Token token = tokenService.getTokenByUsername(user.get().getUsername());
            if (token == null) {
                return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), "Token not found");
            }
            if (OTP.equals(token.getOTP())) {
                return new ResponseData<>(HttpStatus.OK.value(), "OTP is valid. Proceed with password reset.");
            } else {
                return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), "Invalid OTP");
            }
        }
        return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), "User not found");
    }

    public ResponseData<?> changePassword(ResetPasswordDTO request) {

        Optional<User> user = userService.findByEmail(request.getEmail());
        if (user.isEmpty()) {
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), "User not found");
        }

        if (!request.getPassword().equals(request.getConfirmPassword())) {
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), "Passwords do not match");
        }

        String encryptedPassword = passwordEncoder.encode(request.getPassword());
        User currentUser = user.get();
        currentUser.setPassword(encryptedPassword);
        userService.saveUser(currentUser);  // Giả sử bạn đã có phương thức lưu user

        return new ResponseData<>(HttpStatus.OK.value(), "Password successfully changed");
    }

//    public String resetPassword(String secretKey) {
//        final String userName = jwtService.extractUsername(secretKey, RESETTOKEN);
//        User user = userService.findByUserName(userName).orElse(null);
//        if(!jwtService.isValid(secretKey, RESETTOKEN, user)){
//            throw new InvalidDataAccessApiUsageException("Token is invalid");
//        }
//        return "reset";
//    }

//    public String changePassword(ResetPasswordDTO request) {
//        User user = isValidUserByToken(request.getSecretKey());
//        if(!request.getPassword().equals(request.getConfirmPassword())){
//            throw new InvalidDataAccessApiUsageException("Password not match");
//        }
//        user.setPassword(passwordEncoder.encode(request.getPassword()));
//        userService.saveUser(user);
//        return "Changed";
//    }
//    private User isValidUserByToken(String secretKey) {
//        final String userName = jwtService.extractUsername(secretKey, RESETTOKEN);
//        User user = userService.findByUserName(userName).orElse(null);
//
//        if(!user.isEnabled()){
//            throw new InvalidDataAccessApiUsageException("User is active");
//        }
//
//        if(!jwtService.isValid(secretKey, RESETTOKEN, user)){
//            throw new InvalidDataAccessApiUsageException("Token is invalid");
//        }
//        return user;
//    }
}