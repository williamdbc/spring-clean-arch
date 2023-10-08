package com.colatina.app.service.entrypoint.api;

import com.colatina.app.service.core.domain.TransactionDomain;
import com.colatina.app.service.core.domain.enumeration.TransactionStatus;
import com.colatina.app.service.core.usecase.GetAccountStatementUseCase;
import com.colatina.app.service.core.usecase.MakeTransactionUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/transaction")
@RequiredArgsConstructor
public class TransactionController {

    private final GetAccountStatementUseCase getAccountStatementUseCase;
    private final MakeTransactionUseCase makeTransactionUseCase;

    @GetMapping("/account-statement/{account_id}")
    public ResponseEntity<List<TransactionDomain>> getAccountStatement(@PathVariable("account_id") Integer accountId,
                                                                       @RequestHeader("start_date") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime startDate,
                                                                       @RequestHeader("end_date") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime endDate,
                                                                       @RequestParam(value = "min_value", required = false) BigDecimal minValue,
                                                                       @RequestParam(value = "page", defaultValue = "1") int page,
                                                                       @RequestParam(value = "page_size", defaultValue = "5") int pageSize) {

        final List<TransactionDomain> accountStatement = getAccountStatementUseCase.getAccountStatement(accountId, startDate, endDate, minValue, page, pageSize);
        return new ResponseEntity<>(accountStatement, accountStatement.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TransactionDomain> makeTransaction(@RequestBody TransactionDomain transactionData){
        TransactionDomain transaction = makeTransactionUseCase.execute(transactionData);
        return new ResponseEntity<>(transaction, transaction.getStatus().equals(TransactionStatus.REFUSED) ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
    }


}
