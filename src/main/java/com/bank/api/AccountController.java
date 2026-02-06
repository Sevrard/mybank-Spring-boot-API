package com.bank.api;

import com.bank.api.dto.AccountDto;
import com.bank.api.dto.CreateAccountRequest;
import com.bank.api.mapper.AccountDtoMapper;
import com.bank.application.QueryAccountUseCase;
import com.bank.application.RecordAccountUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/accounts")
@CrossOrigin(origins = "http://localhost:4200") // Sécurité supplémentaire
public class AccountController {

    private static final Logger log = LoggerFactory.getLogger(AccountController.class);

    private final RecordAccountUseCase createAccountUseCase;
    private final QueryAccountUseCase accountQueryUseCase;

    public AccountController(
            RecordAccountUseCase createAccountUseCase,
            QueryAccountUseCase accountQueryUseCase
    ) {
        this.createAccountUseCase = createAccountUseCase;
        this.accountQueryUseCase = accountQueryUseCase;
    }


    @PatchMapping("/deactivate/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        log.info(" 🚨 Deactivation de Account ! id ={}", id);
        accountQueryUseCase.delete(id);
        log.info("✅ Deactivation success", id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody CreateAccountRequest dto) {
        log.info(" Creation de Account !!!!!");
        createAccountUseCase.execute(
                UUID.randomUUID(),
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


    @GetMapping("/user/{userId}")
    public List<AccountDto> getByUserId(@PathVariable UUID userId) {
        return accountQueryUseCase.findByUserId(userId)
                .stream()
                .map(AccountDtoMapper::toDto)
                .toList();
    }

}
