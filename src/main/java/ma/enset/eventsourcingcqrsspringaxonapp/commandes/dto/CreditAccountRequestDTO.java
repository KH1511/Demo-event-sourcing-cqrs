package ma.enset.eventsourcingcqrsspringaxonapp.commandes.dto;

public record CreditAccountRequestDTO(String accountId, double amount, String currency) {
}
