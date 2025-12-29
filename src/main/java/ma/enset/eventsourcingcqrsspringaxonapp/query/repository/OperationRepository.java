package ma.enset.eventsourcingcqrsspringaxonapp.query.repository;

import ma.enset.eventsourcingcqrsspringaxonapp.query.entities.AccountOperation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OperationRepository extends JpaRepository<AccountOperation, Long> {
    List<AccountOperation> findByAccountId(String id);
}
