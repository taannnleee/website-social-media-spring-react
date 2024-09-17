package org.example.socialmedia.common.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Parent")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Parent implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fatherFullName;

    private String motherFullName;

    private String fatherPhoneNumber;

    private String motherPhoneNumber;

    private String address;

    private String fatherEmail;

    private String motherEmail;

    @OneToMany(mappedBy = "parent")
    private List<Parent_Child> parentChildren;
}
