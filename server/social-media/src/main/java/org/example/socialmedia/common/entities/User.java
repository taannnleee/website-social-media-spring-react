package org.example.socialmedia.common.entities;


import jakarta.persistence.*;
import lombok.*;

import javax.print.attribute.standard.Media;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "User")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter

// implements UserDetails
public class User extends AbstractEntity<Long> {
    private String fullname;
    private String phone;
    private String email;
    private String password;
    private Date dateOfBirth;
    private String gender;
    @OneToMany(mappedBy = "user")
    private List<GroupHasUser> groupHasUsers;

    @OneToMany(mappedBy = "user")
    private List<UserHasRole> userHasRoles;

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return List.of();
//    }
//
//    @Override
//    public String getUsername() {
//        return "";
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
}
