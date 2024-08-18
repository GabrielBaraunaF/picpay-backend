package picpay.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import picpay.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Integer> {

    @Query(value = "SELECT *\n" +
            "        FROM transaction\n" +
            "        WHERE YEAR(date) = :year AND MONTH(date) = :month AND DAY(date) = :day\n" +
            "        AND (\n" +
            "        ID_ACCOUNT_PAYER IN (:accountNumber)\n" +
            "        OR ID_ACCOUNT_PAYEE IN (:accountNumber)\n" +
            "        );", nativeQuery = true)
    List<Transaction> findByDate(@Param("year") int year, @Param("month") int month, @Param("day") int day, @Param("accountNumber") int accountNumber);


    @Query(value = "SELECT *" +
            " FROM TRANSACTION" +
            " WHERE ID_ACCOUNT_PAYER IN (:accountNumber)" +
            " OR" +
            " ID_ACCOUNT_PAYEE IN (:accountNumber)", nativeQuery = true)
    List<Transaction> findByUser(@Param("accountNumber") int accountNumber);
}