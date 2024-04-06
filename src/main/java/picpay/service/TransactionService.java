package picpay.service;

import org.springframework.stereotype.Service;
import picpay.entity.Transaction;

import java.time.LocalDate;
import java.util.List;

@Service
public interface TransactionService {
    void save(Transaction transaction);
    List<Transaction> transactionHistory(LocalDate date, int number);
}
