package com.colatina.app.service.dataprovider.adapter;

import com.colatina.app.service.configuration.mapper.AccountMapper;
import com.colatina.app.service.configuration.mapper.TransactionMapper;
import com.colatina.app.service.core.domain.AccountDomain;
import com.colatina.app.service.core.domain.TransactionDomain;
import com.colatina.app.service.core.domain.enumeration.AccountStatus;
import com.colatina.app.service.core.domain.enumeration.TransactionStatus;
import com.colatina.app.service.core.exception.BusinessException;
import com.colatina.app.service.core.gateway.TransactionGateway;
import com.colatina.app.service.dataprovider.entity.AccountEntity;
import com.colatina.app.service.dataprovider.entity.TransactionEntity;
import com.colatina.app.service.dataprovider.repository.AccountRepository;
import com.colatina.app.service.dataprovider.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TransactionAdapter implements TransactionGateway {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    @Override
    public List<TransactionDomain> getAccountStatement(Integer accountId, LocalDateTime startDate, LocalDateTime endDate,
                                                       BigDecimal minValue, int page, int pageSize) {

        List<TransactionEntity> filteredTransactions = transactionRepository.findByAccountOriginIdAndCreatedAtBetweenAndValueGreaterThanEqual(accountId, startDate, endDate, minValue);

        int startIndex =  (page - 1) * pageSize;

        if (startIndex >= filteredTransactions.size()) {
            return Collections.emptyList();
        }

        int endIndex = Math.min(startIndex + pageSize, filteredTransactions.size());

        List<TransactionEntity> pageTransactions = filteredTransactions.subList(startIndex, endIndex);

        return transactionMapper.toDto(pageTransactions);
    }

    public TransactionDomain makeTransaction(TransactionDomain transaction){

        Integer accountOriginId = transaction.getAccountOrigin().getId();
        AccountEntity accountOrigin = accountRepository.findById(accountOriginId).orElseThrow(() -> new BusinessException("Origin account not found."));

        Integer accountDestinationId = transaction.getAccountDestination().getId();
        AccountEntity accountDestination = accountRepository.findById(accountDestinationId).orElseThrow(() -> new BusinessException("Destination account not found."));

        if(!(accountOrigin.isActive() && accountDestination.isActive())){
            transaction.setStatus(TransactionStatus.REFUSED);
            //throw new BusinessException("One (or both) transaction accounts are not active.");
        } else {
            transaction.setStatus(TransactionStatus.WAITING_PROCESSING);
            BigDecimal transactionValue = transaction.getValue();

            if(!accountOrigin.hasEnoughBalance(transactionValue)){
                transaction.setStatus(TransactionStatus.REFUSED);
                //throw new BusinessException("Origin account doesnt have enough balance to make the transaction.");
            } else {
                accountOrigin.debit(transactionValue);
                accountDestination.credit(transactionValue);
                transaction.setStatus(TransactionStatus.PROCESSED);
            }
        }

        TransactionEntity transactionEntity = transactionMapper.toEntity(transaction);
        transactionRepository.save(transactionEntity);
        return transactionMapper.toDto(transactionEntity);
    }

}
