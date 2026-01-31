package com.bank.api;

import com.bank.api.dto.TransactionDto;
import com.bank.application.QueryTransactionUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/transactions")
public class TransactionQueryController {

    private final QueryTransactionUseCase queryUseCase;

    public TransactionQueryController(QueryTransactionUseCase queryUseCase) {
        this.queryUseCase = queryUseCase;
    }

    @GetMapping("/{accountId}")
    public List<TransactionDto> findByAccount(@PathVariable UUID accountId) {
        return queryUseCase.findByAccount(accountId);
    }
}