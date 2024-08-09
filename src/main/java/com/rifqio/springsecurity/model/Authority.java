package com.rifqio.springsecurity.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "authorities")
@Getter
@Setter
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_authorities")
    private Long id;

    private String authorities;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customers customers;
}
