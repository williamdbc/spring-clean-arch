package com.colatina.app.service.core.usecase;

import com.colatina.app.service.core.domain.TransactionDomain;
import com.colatina.app.service.core.gateway.TransactionGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

    public class MakeTransactionUseCase {

    private final TransactionGateway transactionGateway;

    public TransactionDomain execute(TransactionDomain transaction){
        return transactionGateway.makeTransaction(transaction);
    }
}
