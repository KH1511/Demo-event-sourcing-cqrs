package ma.enset.eventsourcingcqrsspringaxonapp.commandes.dto;

import ma.enset.eventsourcingcqrsspringaxonapp.enums.AccountStatus;

public record UpdateAccountStatusRequestDTO(String accountId, AccountStatus status) {
}
