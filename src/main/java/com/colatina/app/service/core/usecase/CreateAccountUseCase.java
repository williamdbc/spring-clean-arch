package com.colatina.app.service.core.usecase;

import com.colatina.app.service.core.domain.AccountDomain;
import com.colatina.app.service.core.exception.BusinessException;
import com.colatina.app.service.core.gateway.AccountGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateAccountUseCase {

    private final AccountGateway accountGateway;

    public void execute(AccountDomain account) {

        if(account.isUnderAge()) {
            throw new BusinessException("Account cannot be created because age is under 18.");
        }

        accountGateway.create(account);

    }
}
