package com.bank.domain;

import java.math.BigDecimal;
import java.util.UUID;

public class AccountTestFactory{
    public static Account createAccount(BigDecimal balance) {
        return new Account(
                UUID.randomUUID(),
                "TEST_FFX",
                balance
        );
    }
}