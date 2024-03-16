package picpay.repository;

import org.springframework.stereotype.Repository;
import picpay.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {
}
