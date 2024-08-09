package com.rifqio.springsecurity.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Entity(name = "customers")
@Getter
@Setter
public class Customers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_customer")
    private Long id;

    private String name;

    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    private String role;

    @Column(name = "created_at")
    @JsonIgnore
    private Date createdAt;

    @OneToMany(mappedBy = "customers", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Authority> authorities;
}
