package com.colatina.app.service.core.usecase;

import com.colatina.app.service.core.gateway.AccountGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class GetAccountBalanceUseCase {

    //private final WalletRepository walletRepository;
    private final AccountGateway accountGateway;

    public String execute(final Integer accountId) {
        return NumberFormat.getInstance(new Locale("pt", "BR"))
                .format(accountGateway.getAccountBalance(accountId));
    }

}
