package ma.enset.eventsourcingcqrsspringaxonapp.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ma.enset.eventsourcingcqrsspringaxonapp.enums.AccountStatus;

@Getter
@AllArgsConstructor
public class AccountStatusUpdatedEvent {
    private String accountId;
    private AccountStatus status;
}
