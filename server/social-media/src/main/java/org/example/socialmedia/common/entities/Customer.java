package org.example.socialmedia.common.entities;

import jakarta.persistence.*;
import lombok.*;
import org.example.socialmedia.common.Enum.ERole;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Customers")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name="password_hash")
    private String passHash;

    @Column(name="date_of_birth")
    private Date dateOfBirth;

    @Enumerated(EnumType.STRING)
    private ERole role;

    @OneToOne( mappedBy = "customer",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Address addresss;

}
