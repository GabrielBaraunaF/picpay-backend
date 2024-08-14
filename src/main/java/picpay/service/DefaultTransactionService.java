package picpay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import picpay.entity.Transaction;
import picpay.repository.TransactionRepository;

import java.time.LocalDate;
import java.util.List;
@Service
public class DefaultTransactionService implements  TransactionService {
    @Autowired
    TransactionRepository repository;

    @Override
    public void save(Transaction transaction) {
        repository.save(transaction);
    }

    @Override
    public List<Transaction> transactionByDate(LocalDate date, int accountNumber) {
       return repository.findByDate(date.getYear(),date.getMonthValue(),date.getDayOfMonth(),accountNumber);
    }

    @Override
    public List<Transaction> transactionHistory(int accountNumber) {
        return repository.findAllById(accountNumber);
    }
}
