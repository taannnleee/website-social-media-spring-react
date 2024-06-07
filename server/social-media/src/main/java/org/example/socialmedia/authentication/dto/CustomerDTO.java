package org.example.socialmedia.authentication.dto;



import lombok.*;
import org.example.socialmedia.common.Enum.ERole;
import org.example.socialmedia.authentication.entities.Address;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
public class CustomerDTO {
    private List<Address> addresss;

    private Long customerId;

    private String firstName;

    private String lastName;

    private String email;
    private Date dateOfBirth;
    private String phoneNumber;

    private int accountBalance;

    private String passHash;
    private String repeatPassword;


    private ERole role;

    public CustomerDTO(String firstName, String lastName, String email, Date dateOfBirth, String phoneNumber, String passHash, String repeatPassword, ERole role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.passHash = passHash;
        this.repeatPassword = repeatPassword;
        this.role = role;
    }

    public CustomerDTO(String firstName, String lastName, String email, String phoneNumber, String passHash, String repeatPassword) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.passHash = passHash;
        this.repeatPassword =repeatPassword;
    }
    public CustomerDTO(String firstName, String lastName, String email, String phoneNumber, String passHash) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.passHash = passHash;
    }

    public CustomerDTO(Long customerId, String firstName, String lastName, String email, String phoneNumber, String passHash) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.passHash = passHash;
    }


    public CustomerDTO(String firstName, String lastName, String email, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public CustomerDTO(String firstName, String lastName, String email, String phoneNumber, int accountBalance, String passHash) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.accountBalance = accountBalance;
        this.passHash = passHash;
    }

}
