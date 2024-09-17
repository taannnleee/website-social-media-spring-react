package org.example.socialmedia.common.entities;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "Parent_Child")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Parent_Child  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Parent parent;

    @ManyToOne
    @JoinColumn(name = "child_id")
    private Child child;
}
