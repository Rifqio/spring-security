package com.rifqio.springsecurity.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "cards")
@Getter
@Setter
public class Cards {
    @Id
    @Column(name = "id_card")
    private long id;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "customer_id")
    private long customerId;

    @Column(name = "card_type")
    private String cardType;

    @Column(name = "total_limit")
    private double totalLimit;

    @Column(name = "amount_used")
    private double amountUsed;

    @Column(name = "created_at")
    @JsonIgnore
    private String createdAt;
}
