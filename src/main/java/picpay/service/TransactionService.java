package picpay.service;

import org.springframework.stereotype.Service;
import picpay.entity.Account;
import picpay.entity.Transaction;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface TransactionService {
    void save(Transaction transaction);
    List<Transaction> transactionHistory(LocalDateTime date);
}
