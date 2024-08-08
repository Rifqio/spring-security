package com.rifqio.springsecurity.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity(name = "customers")
@Getter
@Setter
public class Customers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String password;


    @Column(name = "phone_number")
    private String phoneNumber;

    private String role;

    @Column(name = "created_at")
    private Date createdAt;
}
