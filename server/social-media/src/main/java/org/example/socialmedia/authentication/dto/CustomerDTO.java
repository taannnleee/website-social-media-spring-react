package org.example.socialmedia.authentication.dto;



import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.*;
import lombok.*;
import org.example.socialmedia.common.Enum.ERole;
import org.example.socialmedia.common.entities.Address;
import org.example.socialmedia.common.util.PhoneNumber;
import org.modelmapper.internal.bytebuddy.implementation.bind.annotation.Empty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CustomerDTO implements Serializable {

//    private List<Address> address;

    @NotBlank(message = "firstName not null or not empty")
    private String firstName;

    @NotNull(message = "lastName not null")
    private String lastName;

    @Email(message = "email invalid format")
    private String email;

    @NotNull(message = "dateOfBirth not null")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "MM/dd/yyyy")
    private Date dateOfBirth;

//    @Pattern(regexp = "^\\d{10}$", message = "phone invalid format")
    @PhoneNumber
    private String phoneNumber;

    @NotEmpty
    private List<String> permission;

    private String passHash;

    private String repeatPassword;

    private ERole role;



}
