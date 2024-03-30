package picpay.service;

import org.springframework.beans.factory.annotation.Autowired;
import picpay.entity.Account;
import picpay.entity.Transaction;
import picpay.repository.TransactionRepository;

import java.time.LocalDateTime;
import java.util.List;

public class DefaultTransactionService implements  TransactionService {
    @Autowired
    TransactionRepository repository;

    @Override
    public void save(Transaction transaction) {
        repository.save(transaction);
    }

    @Override
    public List<Transaction> transactionHistory(LocalDateTime date) {
       return repository.findByDate(date.getDayOfMonth(),date.getMonthValue(), date.getYear());
    }
}
