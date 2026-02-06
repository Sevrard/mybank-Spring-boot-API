package com.bank.api;

import com.bank.api.dto.TransferRequest;
import com.bank.application.RecordTransferWithAuditUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transfers")
public class TransferController {

    private final RecordTransferWithAuditUseCase transferUseCase;

    public TransferController(RecordTransferWithAuditUseCase transferUseCase) {
        this.transferUseCase = transferUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void transfer(@Valid @RequestBody TransferRequest dto){
        transferUseCase.execute(
                dto.fromAccountId(),
                dto.toAccountId(),
                dto.amount(),
                dto.label()
        );
    }
}
