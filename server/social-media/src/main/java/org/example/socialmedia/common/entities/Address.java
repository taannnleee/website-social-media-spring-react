package org.example.socialmedia.common.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "Address")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Address implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long addressId;

    @Column(name = "city")
    private String city;

    @Column(name = "district")
    private String district;

    @Column(name = "stress")
    private String stress;

    @OneToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private Customer customer;

    public Address(String city, String district, String stress) {
        this.city = city;
        this.district = district;
        this.stress = stress;
    }
}
