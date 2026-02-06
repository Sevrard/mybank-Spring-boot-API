package com.bank.domain.exception.account;

import java.util.UUID;

public class NotActiveAccountException extends RuntimeException {
    public NotActiveAccountException(UUID id){
        super ("This account is not active ! id:" + id);
    }
}
