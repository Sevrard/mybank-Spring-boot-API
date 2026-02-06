package com.bank.infrastructure.persistence.account;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "accounts")
public class AccountEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal balance;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column
    private String iban;
    @Column
    private String bic;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @Column(name = "isActive")
    private boolean isActive = true;

    protected AccountEntity() {}

    public AccountEntity(UUID id, String name, BigDecimal balance, UUID userId, String iban, String bic, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.userId = userId;
        this.iban = iban;
        this.bic = bic;
        this.createdAt = LocalDateTime.now();
        this.isActive = isActive;
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public BigDecimal getBalance() { return balance; }
    public UUID getUserId() { return userId; }
    public LocalDateTime getCreatedAt(){ return createdAt; };
    public String getIban(){ return iban; }
    public String getBic(){ return bic; }
    public Boolean getIsActive(){ return isActive; }

    public void setNotActive() {
        this.isActive = false;
    }
}
