package ma.enset.eventsourcingcqrsspringaxonapp.commandes.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ma.enset.eventsourcingcqrsspringaxonapp.enums.AccountStatus;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@AllArgsConstructor
public class UpdateAccountStatusCommand {
    @TargetAggregateIdentifier
    private String id;
    private AccountStatus status;
}