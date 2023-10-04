package com.colatina.app.service.entrypoint.api;

import com.colatina.app.service.core.domain.AccountDomain;
import com.colatina.app.service.core.usecase.ChangeStatusUseCase;
import com.colatina.app.service.core.usecase.CreateAccountUseCase;
import com.colatina.app.service.core.usecase.GetAccountBalanceUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {

    private final CreateAccountUseCase createAccountUseCase;
    private final ChangeStatusUseCase changeStatusUseCase;
    private final GetAccountBalanceUseCase getAccountBalanceUseCase;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid AccountDomain data) {
        createAccountUseCase.execute(data);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/change/{id}")
    public ResponseEntity<Void> changeStatus(@RequestBody AccountDomain data, @PathVariable Integer id) {
        changeStatusUseCase.execute(data, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
