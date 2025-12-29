package ma.enset.eventsourcingcqrsspringaxonapp.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ma.enset.eventsourcingcqrsspringaxonapp.enums.AccountStatus;

@Getter
@AllArgsConstructor
public class AccountCreatedEvent {
    private String accountId;
    private double initialBalance;
    private AccountStatus status;
    private String currency;

}
