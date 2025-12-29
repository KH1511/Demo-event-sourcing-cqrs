package ma.enset.eventsourcingcqrsspringaxonapp.events;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AccountCreditEvent {
    private String accountId;
    private double amount;
    private String currency;
}
