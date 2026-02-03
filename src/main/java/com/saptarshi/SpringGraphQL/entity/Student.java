package com.saptarshi.SpringGraphQL.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private double marks;

    @ManyToOne(fetch = FetchType.EAGER) // it will create the N+1 problem
    @JoinColumn(name="department_id")
    @ToString.Exclude
    private Department department;

    private String email;

    private String password;
}
