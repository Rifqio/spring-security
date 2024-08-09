package com.rifqio.springsecurity.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity(name = "account_transactions")
@Getter
@Setter
public class AccountTransactions {
    @Id
    @Column(name = "transaction_id")
    private String transactionId;

    @Column(name = "account_number")
    private long accountNumber;

    @Column(name = "customer_id")
    private long customerId;

    @Column(name = "transaction_type")
    private String transactionType;

    @Column(name = "transaction_summary")
    private String transactionSummary;

    @Column(name = "amount")
    private double amount;

    @Column(name = "transaction_date")
    private Date transactionDate;

    @Column(name="closing_balance")
    private double closingBalance;

    @Column(name = "created_at")
    @JsonIgnore
    private Date createdAt;
}
