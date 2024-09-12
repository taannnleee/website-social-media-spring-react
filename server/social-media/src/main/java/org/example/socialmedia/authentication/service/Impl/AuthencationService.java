package org.example.socialmedia.authentication.service.Impl;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.example.socialmedia.authentication.dto.request.LoginRequest;
import org.example.socialmedia.authentication.dto.response.TokenRespone;
import org.example.socialmedia.authentication.repositories.UserRepository;
import org.example.socialmedia.authentication.service.JwtService;
import org.example.socialmedia.authentication.service.TokenService;
import org.example.socialmedia.common.Enum.ETokenType;
import org.example.socialmedia.common.entities.Token;
import org.example.socialmedia.common.entities.User;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.sound.midi.InvalidMidiDataException;
import java.util.Optional;

import static org.example.socialmedia.common.Enum.ETokenType.ACCESSTOKEN;
import static org.example.socialmedia.common.Enum.ETokenType.REFRESHTOKEN;

@Service
@RequiredArgsConstructor
public class AuthencationService {
    private final AuthenticationManager authenticationManager;
    private  final UserRepository userRepository;
    private  final JwtService jwtService;
    private final TokenService tokenService;

    public TokenRespone authentication(LoginRequest loginRequest){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        }
        catch (BadCredentialsException e) {
//            throw new RuntimeException("Username or Password is incorrect", e);
        } catch (UsernameNotFoundException e) {
            throw new AuthenticationCredentialsNotFoundException("Username or Password is incorrect", e);
        } catch (Exception e) {
            throw new AuthenticationCredentialsNotFoundException("Authentication failed: " + e.getMessage(), e);
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
}