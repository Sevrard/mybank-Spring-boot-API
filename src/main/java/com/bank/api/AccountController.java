package com.bank.api;

import com.bank.api.dto.AccountDto;
import com.bank.api.dto.CreateAccountRequest;
import com.bank.api.mapper.AccountDtoMapper;
import com.bank.application.QueryAccountUseCase;
import com.bank.application.RecordAccountUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final RecordAccountUseCase createAccountUseCase;
    private final QueryAccountUseCase accountQueryUseCase;

    public AccountController(
            RecordAccountUseCase createAccountUseCase,
            QueryAccountUseCase accountQueryUseCase
    ) {
        this.createAccountUseCase = createAccountUseCase;
        this.accountQueryUseCase = accountQueryUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody CreateAccountRequest dto) {
        createAccountUseCase.execute(
                dto.id(),
                dto.name(),
                dto.initialBalance(),
                dto.userId()
        );
    }

    @GetMapping("/{id}")
    public AccountDto get(@PathVariable UUID id) {
        return AccountDtoMapper.toDto(
                accountQueryUseCase.findById(id)
        );
    }


}
