package com.bank.api;

import com.bank.api.dto.TransferRequest;
import com.bank.application.TransferWithAuditUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transfers")
public class TransferController {

    private final TransferWithAuditUseCase transferUseCase;
    public TransferController(TransferWithAuditUseCase transferUseCase) {
        this.transferUseCase = transferUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void transfer(@Valid @RequestBody TransferRequest dto){
        transferUseCase.execute(
                dto.fromAccountId(),
                dto.toAccountId(),
                dto.amount()
        );
    }
}
