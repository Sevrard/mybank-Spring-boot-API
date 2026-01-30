package com.bank.application;

import com.bank.domain.User;
import com.bank.domain.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class CreateUserUseCase {

    private final UserRepository userRepository;
    private static final Logger log = LoggerFactory.getLogger(CreateAccountUseCase.class);

    public CreateUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void execute(UUID id, String email, String firstname, String lastname) {
        log.info("Creadte USSSSSSSER !!!!!!!!!! id={}, email={}, firstname={}, lastname={}", id, email, firstname,lastname);
        User user = new User(id, email, firstname, lastname);
        userRepository.save(user);
    }
}
