package com.bank.domain.exception.account;

public class MissingAccountNameException extends RuntimeException
{
    public MissingAccountNameException(){
        super("Missing Name Account for the creation !");
    }
}
