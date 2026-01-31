package com.bank.application;

import com.bank.domain.User;
import com.bank.domain.exception.user.UserNotFoundException;
import com.bank.domain.repository.UserRepository;

import java.util.UUID;

public class QueryUserUseCase {

    private final UserRepository userRepository;

    public QueryUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getById(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }
}
