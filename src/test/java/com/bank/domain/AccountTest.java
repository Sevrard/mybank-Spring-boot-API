package com.bank.domain;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.bank.domain.exception.transaction.InsufficientFundsException;
import com.bank.domain.exception.account.NonPositiveAmountException;

import java.math.BigDecimal;

class AccountTest {

    private static final BigDecimal HUNDRED = new BigDecimal("100.00");
    private static final BigDecimal FIFTY = new BigDecimal("50.00");

    /// SUCCESS ////
    @Test
    void should_create_account_with_initial_balance(){
        Account account = AccountTestFactory.createAccount(HUNDRED);
        assertEquals(0, account.getBalance().compareTo(HUNDRED));
    }
    @Test
    void should_debit_account(){
        Account account = AccountTestFactory.createAccount(HUNDRED);
        account.debit(HUNDRED);
        assertEquals( 0,  account.getBalance().compareTo(new BigDecimal("0.00")));
    }
    @Test
    void should_credit_account(){
        Account account = AccountTestFactory.createAccount(HUNDRED);
        account.credit(HUNDRED);
        assertEquals(0, account.getBalance().compareTo(new BigDecimal("200.00")));
    }

    /// FAIL ////
    @Test
    void should_throw_when_initial_balance_is_negative(){
        assertThrows(NonPositiveAmountException.class, () ->
                AccountTestFactory.createAccount(new BigDecimal("-100.00"))
        );
    }

    @Test
    void should_throw_when_debit_non_positive_amount(){
        Account account = AccountTestFactory.createAccount(FIFTY);
        assertThrows(NonPositiveAmountException.class, () -> account.debit(BigDecimal.ZERO));
    }
    @Test
    void should_throw_when_debit_more_than_balance(){
        Account account = AccountTestFactory.createAccount(FIFTY);
        assertThrows(InsufficientFundsException.class,() -> account.debit(HUNDRED));
    }
    @Test
    void should_throw_when_credit_non_positive_amount(){
        Account account = AccountTestFactory.createAccount(FIFTY);
        BigDecimal amountCredit = new BigDecimal("-100.00");
        assertThrows(NonPositiveAmountException.class, () -> account.credit(amountCredit));
    }
    @Test
    void should_throw_when_credit_zero_amount(){
        Account account = AccountTestFactory.createAccount(FIFTY);
        assertThrows(NonPositiveAmountException.class, () -> account.credit(BigDecimal.ZERO));
    }


}