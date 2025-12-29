package ma.enset.eventsourcingcqrsspringaxonapp.query.handlers;

import ma.enset.eventsourcingcqrsspringaxonapp.query.dtos.AccountStatementResponseDTO;
import ma.enset.eventsourcingcqrsspringaxonapp.query.entities.Account;
import ma.enset.eventsourcingcqrsspringaxonapp.query.entities.AccountOperation;
import ma.enset.eventsourcingcqrsspringaxonapp.query.queries.GetAccountStatement;
import ma.enset.eventsourcingcqrsspringaxonapp.query.queries.GetAllAccountsQuery;
import ma.enset.eventsourcingcqrsspringaxonapp.query.queries.WatchEventQuery;
import ma.enset.eventsourcingcqrsspringaxonapp.query.repository.AccountRepository;
import ma.enset.eventsourcingcqrsspringaxonapp.query.repository.OperationRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountQueryHandler {
    private AccountRepository accountRepository;
    private OperationRepository operationRepository;

    public AccountQueryHandler(AccountRepository accountRepository, OperationRepository operationRepository) {
        this.accountRepository = accountRepository;
        this.operationRepository = operationRepository;
    }

    @QueryHandler
    public List<Account> on(GetAllAccountsQuery query) {
        return accountRepository.findAll();
    }

    @QueryHandler
    public AccountStatementResponseDTO on(GetAccountStatement query) {
        Account account = accountRepository.findById(query.getAccountId()).get();
        List<AccountOperation> operations = operationRepository.findByAccountId(query.getAccountId());
        return new AccountStatementResponseDTO(account, operations);
    }

    @QueryHandler
    public AccountOperation on(WatchEventQuery query) {
        return AccountOperation.builder().build();
    }
}
