package org.example.socialmedia.authentication.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationRequest {
    private String username;
    private String password;
    private String phoneNumber;
}
