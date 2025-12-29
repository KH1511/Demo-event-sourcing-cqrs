package ma.enset.eventsourcingcqrsspringaxonapp.commandes.aggregates;

import lombok.extern.slf4j.Slf4j;
import ma.enset.eventsourcingcqrsspringaxonapp.commandes.commands.AddAccountCommand;
import ma.enset.eventsourcingcqrsspringaxonapp.commandes.commands.CreditAccountCommand;
import ma.enset.eventsourcingcqrsspringaxonapp.commandes.commands.DebitAccountCommand;
import ma.enset.eventsourcingcqrsspringaxonapp.commandes.commands.UpdateAccountStatusCommand;
import ma.enset.eventsourcingcqrsspringaxonapp.enums.AccountStatus;
import ma.enset.eventsourcingcqrsspringaxonapp.events.*;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@Slf4j
public class AccountAggregate {
    private String accountId;
    private double balance;
    private AccountStatus status;

    public AccountAggregate(){}

    //ADD ACCOUNT
    @CommandHandler
    public AccountAggregate(AddAccountCommand command){
        log.info("########## AddAccountCommand Received ###########");
        if (command.getInitialBalance()<=0) throw new IllegalArgumentException("Initial balance must be positive");
        AggregateLifecycle.apply(new AccountCreatedEvent(
                command.getId(),
                command.getInitialBalance(),
                AccountStatus.CREATED,
                command.getCurrency()
        ));
        if (command.getInitialBalance()<=0) throw new IllegalArgumentException("Initial balance must be positive");
        AggregateLifecycle.apply(new AccountActivatedEvent(
                command.getId(),
                AccountStatus.ACTIVATED
        ));
    }

    @EventSourcingHandler
    public void on(AccountCreatedEvent event){
        log.info("########## AccountCreatedEvent occurred ###########");

        this.accountId = event.getAccountId();
        this.balance = event.getInitialBalance();
        this.status = event.getStatus();
    }

    @EventSourcingHandler
    public void on(AccountActivatedEvent event){
        log.info("########## AccountActivatedEvent occurred ###########");

        this.accountId = event.getAccountId();
        this.status = event.getStatus();
    }

    //CREDIT ACCOUNT
    @CommandHandler
    public void handle(CreditAccountCommand command){
        log.info("########## CreditAccountCommand Received ###########");
        if (!status.equals(AccountStatus.ACTIVATED)) throw new RuntimeException("The account "+command.getId()+"is not activated");
        if (command.getAmount()<=0) throw new IllegalArgumentException("Amount must be positive");
        AggregateLifecycle.apply(new AccountCreditEvent(
                command.getId(),
                command.getAmount(),
                command.getCurrency()
        ));
    }

    @EventSourcingHandler
    public void on(AccountCreditEvent event){
        log.info("########## AccountCreditEvent occurred ###########");

        this.accountId = event.getAccountId();
        this.balance = this.balance + event.getAmount();
    }

    //DEBIT ACCOUNT
    @CommandHandler
    public void handle(DebitAccountCommand command){
        log.info("########## DebitAccountCommand Received ###########");
        if (!status.equals(AccountStatus.ACTIVATED)) throw new RuntimeException("The account "+command.getId()+"is not activated");
        if (balance< command.getAmount()) throw new RuntimeException("Balance not sufficient");
        if (command.getAmount()<=0) throw new IllegalArgumentException("Amount must be positive");
        AggregateLifecycle.apply(new AccountDebitEvent(
                command.getId(),
                command.getAmount(),
                command.getCurrency()
        ));
    }

    @EventSourcingHandler
    public void on(AccountDebitEvent event){
        log.info("########## AccountDebitEvent occurred ###########");

        this.accountId = event.getAccountId();
        this.balance = this.balance - event.getAmount();
    }

    //UPDATE ACCOUNT
    @CommandHandler
    public void handle(UpdateAccountStatusCommand command){
        log.info("########## UpdateAccountStatusCommand Received ###########");
        AggregateLifecycle.apply(new AccountStatusUpdatedEvent(
                command.getId(),
                command.getStatus()
        ));
    }

    @EventSourcingHandler
    public void on(AccountStatusUpdatedEvent event){
        log.info("########## AccountStatusUpdatedEvent occurred ###########");

        this.accountId = event.getAccountId();
        this.status =  event.getStatus();
    }
}