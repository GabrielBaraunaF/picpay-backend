package picpay.service;

import org.springframework.stereotype.Service;
import picpay.entity.Transaction;

import java.time.LocalDate;
import java.util.List;


public interface TransactionService {
    void save(Transaction transaction);
    List<Transaction> transactionByDate(LocalDate date, int accountNumber);
    List<Transaction> transactionHistory(int accountNumber);
}
