package ma.enset.eventsourcingcqrsspringaxonapp.commandes.dto;

public record DebitAccountRequestDTO(String accountId, double amount, String currency) {
}
