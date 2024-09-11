package org.example.socialmedia.authentication.service;

import org.example.socialmedia.common.Enum.ETokenType;
import org.example.socialmedia.common.entities.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String generateRefreshToken(User user);
    String generateToken(User user);
    String extractUsername(String token, ETokenType tokenType);
    Boolean isValid(String token,ETokenType tokenType, UserDetails userDetails);
}
