package com.rifqio.springsecurity.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "loans")
@Getter
@Setter
public class Loans {
    @Id
    @Column(name = "loan_number")
    private long loanNumber;

    @Column(name = "customer_id")
    private long customerId;

    @Column(name = "start_date")
    private String startDate;

    @Column(name = "loan_type")
    private String loanType;

    @Column(name = "amount_paid")
    private double amountPaid;

    @Column(name = "outstanding_amount")
    private double outstandingAmount;

    @Column(name = "created_at")
    private String createdAt;
}
