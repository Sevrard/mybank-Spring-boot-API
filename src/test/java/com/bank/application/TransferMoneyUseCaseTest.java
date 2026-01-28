package com.bank.application ;

import com.bank.domain.Account;
import com.bank.domain.AccountTestFactory;
import com.bank.domain.exception.AccountNotFoundException;
import com.bank.domain.exception.InsufficientFundsException;
import com.bank.domain.exception.SameAccountTransferException;
import com.bank.infrastructure.repository.FakeAccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


public class TransferMoneyUseCaseTest {
    private static final BigDecimal HUNDRED = new BigDecimal("100.00");
    private static final BigDecimal FIFTY = new BigDecimal("50.00");
    private FakeAccountRepository fakeAccountRepository;
    private TransferMoneyUseCase transferMoneyUseCase;

    @BeforeEach
    void setup() {
        fakeAccountRepository = new FakeAccountRepository();
        transferMoneyUseCase = new TransferMoneyUseCase(fakeAccountRepository);
    }

    /// HAPPY ///
    @Test
    void should_transfer_money_between_two_account(){
        Account from = AccountTestFactory.createAccount(HUNDRED);
        Account to = AccountTestFactory.createAccount(FIFTY);
        fakeAccountRepository.add(from);
        fakeAccountRepository.add(to);
        transferMoneyUseCase.execute(
                from.getId(),
                to.getId(),
                FIFTY
        );
        assertEquals(0, from.getBalance().compareTo(FIFTY));
        assertEquals(0, to.getBalance().compareTo(HUNDRED));
    }

    ///  ERROR ///
    @Test
    void should_throw_when_from_account_not_found(){
        Account from = AccountTestFactory.createAccount(HUNDRED);
        fakeAccountRepository.add(from);
        assertThrows(AccountNotFoundException.class, () -> transferMoneyUseCase.execute(
                from.getId(),
                UUID.randomUUID(),
                HUNDRED
        ));
    }
    @Test
    void should_throw_when_to_account_not_found() {
        Account to = AccountTestFactory.createAccount(HUNDRED);
        fakeAccountRepository.add(to);
        assertThrows(AccountNotFoundException.class, () -> transferMoneyUseCase.execute(
                        UUID.randomUUID(), // from absent
                        to.getId(),
                        HUNDRED
                )
        );
    }
    @Test
    void should_throw_when_transfer_same_accounts(){
        Account from = AccountTestFactory.createAccount(HUNDRED);
        fakeAccountRepository.add(from);
        assertThrows(SameAccountTransferException.class, ()-> transferMoneyUseCase.execute(
                from.getId(),
                from.getId(),
                FIFTY
        ));
    }
    @Test
    void should_throw_when_transfer_with_insufficient_funds(){
        Account from = AccountTestFactory.createAccount(FIFTY);
        Account to = AccountTestFactory.createAccount(FIFTY);
        fakeAccountRepository.add(from);
        fakeAccountRepository.add(to);
        assertThrows(InsufficientFundsException.class, ()-> transferMoneyUseCase.execute(
                from.getId(),
                to.getId(),
                HUNDRED
        ));
    }
}
