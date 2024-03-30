package picpay.service;

import org.springframework.beans.factory.annotation.Autowired;
import picpay.entity.Transaction;
import picpay.repository.TransactionRepository;

import java.time.LocalDate;
import java.util.List;

public class DefaultTransactionService implements  TransactionService {
    @Autowired
    TransactionRepository repository;

    @Override
    public void save(Transaction transaction) {
        repository.save(transaction);
    }

    @Override
    public List<Transaction> transactionHistory(LocalDate date) {
       return repository.findByDate(date.getDayOfMonth(),date.getMonthValue(), date.getYear());
    }
}
