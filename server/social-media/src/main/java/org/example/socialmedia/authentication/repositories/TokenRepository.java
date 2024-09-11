package org.example.socialmedia.authentication.repositories;

import org.example.socialmedia.common.entities.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long>  {
    Optional<Token> findByUsername(String username);
}
