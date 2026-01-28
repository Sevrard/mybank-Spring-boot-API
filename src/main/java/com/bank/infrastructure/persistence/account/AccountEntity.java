package com.bank.infrastructure.persistence.account;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "accounts")
public class AccountEntity {

    @Id
    @Column(nullable = false, updatable = false)
    UUID id;

    @Column(nullable = false)
    private String owner;
    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal balance;

    protected AccountEntity(){}
    public AccountEntity(UUID id, String owner, BigDecimal balance){
        this.id = id;
        this.owner = owner;
        this.balance = balance;
    }

    public UUID getId() { return id; }
    public String getOwner() { return owner; }
    public BigDecimal getBalance() {return balance; }
}