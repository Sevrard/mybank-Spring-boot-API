package com.bank.domain.exception.user;

public class MissingUserEmailException extends RuntimeException{
    public MissingUserEmailException(){
        super("No user email set !");
    }
}
