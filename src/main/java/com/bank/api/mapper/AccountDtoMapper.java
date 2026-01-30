package com.bank.api.mapper;

import com.bank.api.dto.AccountDto;
import com.bank.domain.Account;

public class AccountDtoMapper {

    private AccountDtoMapper() {}

    public static AccountDto toDto(Account account) {
        return new AccountDto(
                account.getId(),
                account.getName(),
                account.getBalance(),
                account.getUserId()
        );
    }
}
