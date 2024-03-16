package picpay.repository;

import org.springframework.stereotype.Repository;
import picpay.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
}
