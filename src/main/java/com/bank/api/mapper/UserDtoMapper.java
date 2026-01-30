package com.bank.api.mapper;

import com.bank.api.dto.UserDto;
import com.bank.domain.User;

public class UserDtoMapper {

    private UserDtoMapper() {}

    public static UserDto toDto(User user) {
        return new UserDto(
                user.getId(),
                user.getFirstname(),
                user.getLastname(),
                user.getEmail()
        );
    }
}
