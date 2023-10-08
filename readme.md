Pequeno projeto para treinar as práticas de clean-code e clean-arch propostas em um mini-curso ministrado por [Chrysthian Moizes]([https://www.jetbrains.com/idea/](https://github.com/chrysthianmoizes) e [João Pedro Calixto](https://github.com/jpcalixto)

# Sistema gerenciador de Transações

O SGT tem a função de gerenciar as transações de usuários cadastrados no sistema.

# WIP - O que precisa ser feito?

- **CreateAccountUseCase**
  - [x] Só permitir usuários maiores de idade (18 anos) a se cadastrarem no sistema;
  - [x] O usuário não pode ter o CPF negativado para se cadastrar no sistema;
  - [x] Sempre que um usuário for cadastrado, uma carteira deve ser criada e associada a ele.

-  **ChangeStatusUseCase**
   - [x] Usuário pode bloquear e desbloquear sua conta.

- **GetAccountStatementUseCase**
  - [x] Melhorar os filtros para obter melhores resultados no extrato do usuário.

-  **GetAccountBalanceUseCase**
   - [x] Corrigir arquitetura de pastas do caso de uso.

- **MakeTransactionUseCase**
  - [x] Usuário que está transferindo e que está recebendo a transação devem estar ativos no sistema;
  - [x] Deve haver saldo suficiente na carteira do usuário que está transferindo;
  - [x] Sistema deve debitar e creditar corretamente das contas (ambas as operações devem ocorrer com sucesso).
