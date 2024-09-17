package org.example.socialmedia.authentication.service;

import lombok.RequiredArgsConstructor;
import org.example.socialmedia.authentication.repositories.TokenRepository;
import org.example.socialmedia.common.entities.Token;

public interface TokenService {

    Long save(Token token);
    String delete(Token token);
    Token getTokenByUsername(String username);
}
