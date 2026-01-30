package com.bank.domain;

import com.bank.domain.exception.user.MissingUserEmailException;

import java.util.UUID;

public class User {

    private final UUID id;
    private final String email;
    private final String firstname;
    private final String lastname;

    public User(UUID id, String email, String firstname, String lastname) {
        if (email == null || email.isBlank()) {
            throw new MissingUserEmailException();
        }
        this.id = id;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public UUID getId() { return id;}
    public String getEmail() {return email;}
    public String getFirstname() { return firstname; }
    public String getLastname() { return lastname; }
}
