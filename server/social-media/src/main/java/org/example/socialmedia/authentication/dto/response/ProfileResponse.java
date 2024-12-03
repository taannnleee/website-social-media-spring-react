package org.example.socialmedia.authentication.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.socialmedia.common.entities.Address;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class ProfileResponse implements Serializable {
    private String fullname;
    private String username;
    private String email;
    private String phone;

    private String street;
    private String city;
    private String state;
}
