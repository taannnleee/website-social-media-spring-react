package org.example.socialmedia.common.entities;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.print.attribute.standard.Media;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Child")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter

public class Child implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    private LocalDate dateOfBirth;

    private String gender;

    private int classId;

    private String facePhoto;

    @OneToMany(mappedBy = "child")
    private List<Parent_Child> parentChildren;
}