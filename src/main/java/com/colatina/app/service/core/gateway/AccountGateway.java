package com.colatina.app.service.core.gateway;

import com.colatina.app.service.core.domain.AccountDomain;
import com.colatina.app.service.core.domain.enumeration.AccountStatus;

import java.math.BigDecimal;

public interface AccountGateway {

    AccountDomain create(AccountDomain account);
    AccountDomain changeStatus(AccountDomain account, Integer id);
    BigDecimal getAccountBalance(Integer id);
}
