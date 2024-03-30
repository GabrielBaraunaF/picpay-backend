package picpay.service;

import org.springframework.beans.factory.annotation.Autowired;
import picpay.entity.Account;
import picpay.repository.AccountRepository;

import java.util.Optional;

public class DefaultAccountService implements AccountService {
    @Autowired
    private AccountRepository repository;

    @Override
    public void debitar(Integer number, Double value) {
        Account account = repository.findByNumber(number);
        if (account != null) {
            account.setBalance(account.getBalance() - value);
            repository.save(account);
        }
    }

    @Override
    public void creditar(Integer number, Double value) {
        Account account = repository.findByNumber(number);
        if (account != null) {
            account.setBalance(account.getBalance() + value);
            repository.save(account);
        }
    }

}