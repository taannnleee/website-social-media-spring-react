package org.example.socialmedia.authentication.dto;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.example.socialmedia.common.Enum.ERole;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class CustomerDTO implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Date dateOfBirth;
    private String phoneNumber;
    private List<String> permission;
    private String passHash;
    private String repeatPassword;
    private ERole role;
}
