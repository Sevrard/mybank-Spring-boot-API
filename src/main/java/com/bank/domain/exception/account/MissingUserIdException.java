package com.bank.domain.exception.account;

public class MissingUserIdException extends RuntimeException{
    public MissingUserIdException(){
        super("Missing USER ID for the account !");
    }
}
