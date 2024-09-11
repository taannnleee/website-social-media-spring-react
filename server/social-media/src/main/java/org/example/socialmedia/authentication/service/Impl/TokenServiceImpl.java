package org.example.socialmedia.authentication.service.Impl;

import lombok.RequiredArgsConstructor;
import org.example.socialmedia.authentication.repositories.TokenRepository;
import org.example.socialmedia.authentication.service.TokenService;
import org.example.socialmedia.common.entities.Token;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {
    private final TokenRepository tokenRepository;

    @Override
    public Long save(Token token) {
        Optional<Token> optional =  tokenRepository.findByUsername(token.getUsername());
        if(optional.isEmpty()) {
            tokenRepository.save(token);
            return token.getId();
        }else{
            Token currentToken = optional.get();
            currentToken.setAccessToken(token.getAccessToken());
            currentToken.setRefreshToken(token.getRefreshToken());
            tokenRepository.save(currentToken);
            return currentToken.getId();
        }

    }

    @Override
    public String delete(Token token) {
        tokenRepository.delete(token);
        return  "delete!!!";
    }

    @Override
    public Token getTokenByUsername(String username) {
        return tokenRepository.findByUsername(username).orElseThrow(()-> new RuntimeException("Token not exists"));
    }
}
