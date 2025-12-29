package ma.enset.eventsourcingcqrsspringaxonapp.query.repository;

import ma.enset.eventsourcingcqrsspringaxonapp.query.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {
}
