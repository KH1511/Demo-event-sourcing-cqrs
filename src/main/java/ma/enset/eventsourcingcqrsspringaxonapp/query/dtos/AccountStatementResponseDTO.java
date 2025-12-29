package ma.enset.eventsourcingcqrsspringaxonapp.query.dtos;

import ma.enset.eventsourcingcqrsspringaxonapp.query.entities.Account;
import ma.enset.eventsourcingcqrsspringaxonapp.query.entities.AccountOperation;

import java.util.List;

public record AccountStatementResponseDTO(
        Account account,
        List<AccountOperation> operations
) {}
