package com.bank.infrastructure.persistence.user;
import com.bank.domain.User;

public class UserMapper {

    private UserMapper() {}

    public static User toDomain(UserEntity entity) {
        return new User(
                entity.getId(),
                entity.getEmail(),
                entity.getFirstname(),
                entity.getLastname()
        );
    }

    public static UserEntity toEntity(User user) {
        return new UserEntity(
                user.getId(),
                user.getEmail(),
                user.getFirstname(),
                user.getLastname()
        );
    }
}
