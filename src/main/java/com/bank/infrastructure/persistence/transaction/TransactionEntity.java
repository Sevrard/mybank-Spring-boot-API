package com.bank.infrastructure.persistence.transaction;

import com.bank.domain.Transaction;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="transactions")
public class TransactionEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable=false, precision = 19, scale = 2)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private Transaction.TransactionType type;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @PrePersist
    void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @Column(name="account_id", nullable=false)
    private UUID accountId;

    @Column(name="label", nullable = false)
    private String label;

    protected TransactionEntity() {}

    public TransactionEntity(UUID id, UUID accountId, BigDecimal amount, Transaction.TransactionType type, String label){
        this.id = id;
        this.amount = amount;
        this.type = type;
        this.accountId = accountId;
        this.createdAt = LocalDateTime.now();
        this.label = label;
    }

    public UUID getId(){ return id; }
    public BigDecimal getAmount(){ return amount; }
    public Transaction.TransactionType getType(){ return type; }
    public LocalDateTime getCreatedAt(){ return createdAt; };
    public UUID getAccountId(){ return accountId; }
    public String getLabel(){ return label; }
}
