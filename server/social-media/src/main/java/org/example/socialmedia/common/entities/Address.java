package org.example.socialmedia.common.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "Address")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Address extends AbstractEntity<Long> {
    private String name;
    private String street;
    private String city;
    private String state;
}
