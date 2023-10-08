package com.colatina.app.service.core.gateway;

import com.colatina.app.service.core.domain.TransactionDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface TransactionGateway {

    List<TransactionDomain> getAccountStatement(Integer accountId, LocalDateTime startDate, LocalDateTime endDate, BigDecimal minValue, int page, int pageSize);
    TransactionDomain makeTransaction(TransactionDomain transactionDomain);

}
