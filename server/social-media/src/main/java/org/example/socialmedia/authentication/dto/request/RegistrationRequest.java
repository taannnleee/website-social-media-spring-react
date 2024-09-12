package org.example.socialmedia.authentication.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class RegistrationRequest {
    private String username;
    private String fullname;
    private String email;
    private String phone;
    private String password;
    private Date dateOfBirth;
    private String gender;
}
