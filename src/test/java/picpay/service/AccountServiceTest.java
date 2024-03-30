package picpay.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import picpay.entity.Account;
import picpay.repository.AccountRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {
    @Mock
    private AccountRepository repository;
    @InjectMocks
    private DefaultAccountService accountService;

    @Captor
    ArgumentCaptor<Account> accountCaptor;

    @Test
    void givenAccountShouldDebit() {
        Account account = getAccount();

        when(repository.findByNumber(account.getNumber())).thenReturn(account);

        accountService.debitar(account.getNumber(), 50d);

        verify(repository).save(accountCaptor.capture());
        assertEquals(50d, accountCaptor.getValue().getBalance());
    }

    @Test
    void givenAccountShouldCredit() {
        Account account = getAccount();

        when(repository.findByNumber(account.getNumber())).thenReturn(account);

        accountService.creditar(account.getNumber(), 50d);

        verify(repository).save(accountCaptor.capture());
        assertEquals(150d, accountCaptor.getValue().getBalance());
    }

    private Account getAccount() {
        Account account = new Account();
        account.setNumber(2205);
        account.setBalance(100d);
        return account;
    }


}