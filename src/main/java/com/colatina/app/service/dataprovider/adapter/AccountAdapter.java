package com.colatina.app.service.dataprovider.adapter;

import com.colatina.app.service.configuration.mapper.AccountMapper;
import com.colatina.app.service.core.domain.AccountDomain;
import com.colatina.app.service.core.domain.enumeration.AccountStatus;
import com.colatina.app.service.core.exception.BusinessException;
import com.colatina.app.service.core.gateway.AccountGateway;
import com.colatina.app.service.dataprovider.entity.WalletEntity;
import com.colatina.app.service.dataprovider.repository.AccountRepository;
import com.colatina.app.service.dataprovider.entity.AccountEntity;
import com.colatina.app.service.dataprovider.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AccountAdapter implements AccountGateway {

    private final AccountMapper mapper;
    private final AccountRepository accountRepository;
    private final WalletRepository walletRepository;

    @Override
    public AccountDomain create(AccountDomain account) {

        account.setStatus(AccountStatus.ACTIVE);

        AccountEntity accountEntity = mapper.toEntity(account);
        accountRepository.save(accountEntity);

        WalletEntity accountWallet = new WalletEntity(accountEntity);
        walletRepository.save(accountWallet);

        accountEntity.setWallet(accountWallet);

        return mapper.toDto(accountEntity);
    }

    @Override
    public AccountDomain changeStatus(AccountDomain account, Integer id){

        AccountEntity entity = accountRepository.findById(id).orElseThrow(() -> new BusinessException("Account not found"));

        entity.setStatus(account.getStatus());

        accountRepository.save(entity);

        return mapper.toDto(entity);
    }

    public BigDecimal getAccountBalance(@PathVariable Integer id){

        AccountEntity entity = accountRepository.findById(id).orElseThrow(() -> new BusinessException("Account not found"));

        return entity.getWallet().getBalance();
    }

}
