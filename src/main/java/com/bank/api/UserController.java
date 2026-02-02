package com.bank.api;

import com.bank.api.dto.CreateUserRequest;
import com.bank.api.dto.UserDto;
import com.bank.api.mapper.UserDtoMapper;
import com.bank.application.RecordUserUseCase;
import com.bank.application.QueryUserUseCase;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final RecordUserUseCase createUserUseCase;
    private final QueryUserUseCase userQueryUseCase;

    public UserController(
            RecordUserUseCase createUserUseCase,
            QueryUserUseCase userQueryUseCase
    ) {
        this.createUserUseCase = createUserUseCase;
        this.userQueryUseCase = userQueryUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void signup(@RequestBody @Valid CreateUserRequest request) {
        //log.info(request);
        createUserUseCase.execute(
                request.firstname(),
                request.lastname(),
                request.email(),
                request.password()
        );
    }

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable UUID id) {
        return UserDtoMapper.toDto(
                userQueryUseCase.getById(id)
        );
    }
}
