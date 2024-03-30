package picpay.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import picpay.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Integer> {

    @Query("SELECT * FROM transaction WHERE YEAR(date) = :year AND MONTH(date) =:month  AND DAY(date) =:day")
    List<Transaction> findByDate(@Param("year") int year,@Param("Month") int month,@Param("day") int day);

}
