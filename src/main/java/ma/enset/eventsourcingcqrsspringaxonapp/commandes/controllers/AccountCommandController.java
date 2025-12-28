package ma.enset.eventsourcingcqrsspringaxonapp.commandes.controllers;

import ma.enset.eventsourcingcqrsspringaxonapp.commandes.commands.AddAccountCommand;
import ma.enset.eventsourcingcqrsspringaxonapp.commandes.commands.CreditAccountCommand;
import ma.enset.eventsourcingcqrsspringaxonapp.commandes.commands.DebitAccountCommand;
import ma.enset.eventsourcingcqrsspringaxonapp.commandes.dto.AddNewAccountRequestDTO;
import ma.enset.eventsourcingcqrsspringaxonapp.commandes.dto.CreditAccountRequestDTO;
import ma.enset.eventsourcingcqrsspringaxonapp.commandes.dto.DebitAccountRequestDTO;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@RestController
@RequestMapping("/commands/accounts")
public class AccountCommandController {
    private CommandGateway commandGateway;
    private EventStore eventStore;

    public AccountCommandController(CommandGateway commandGateway, EventStore eventStore) {
        this.commandGateway = commandGateway;
        this.eventStore = eventStore;
    }

    @PostMapping("/add")
    public CompletableFuture<String> addNewAccount(@RequestBody AddNewAccountRequestDTO requestDTO){
        CompletableFuture<String> response = commandGateway.send(new AddAccountCommand(
                UUID.randomUUID().toString(),
                requestDTO.initialBalance(),
                requestDTO.currency()
        ));
        return response;
    }

    @PostMapping("/credit")
    public CompletableFuture<String> creditAccount(@RequestBody CreditAccountRequestDTO requestDTO){
        CompletableFuture<String> response = commandGateway.send(new CreditAccountCommand(
                requestDTO.accountId(),
                requestDTO.amount(),
                requestDTO.currency()
        ));
        return response;
    }

    @PostMapping("/debit")
    public CompletableFuture<String> debitAccount(@RequestBody DebitAccountRequestDTO requestDTO){
        CompletableFuture<String> response = commandGateway.send(new DebitAccountCommand(
                requestDTO.accountId(),
                requestDTO.amount(),
                requestDTO.currency()
        ));
        return response;
    }

    @PutMapping("/updateStatus")
    public CompletableFuture<String> updateStatus(@RequestBody UpdateAccountStatusRequestDTO requestDTO){
        CompletableFuture<String> response = commandGateway.send(new UpdateAccountStatusCommand(
                requestDTO.accountId(),
                requestDTO.status()
        ));
        return response;
    }

    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception exception){
        return exception.getMessage();
    }

    @GetMapping("/events/{accounfId}")
    public Stream eventStore(@PathVariable String accountId){
        return eventStore.readEvents(accountId).asStream();
    }

}

