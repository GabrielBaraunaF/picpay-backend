package picpay.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import picpay.entity.Account;
import picpay.entity.Transaction;
import picpay.repository.TransactionRepository;

import java.time.LocalDateTime;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {
    @Mock
    private TransactionRepository repository;
    @InjectMocks
    private DefaultTransactionService transactionService;

    @Test
    void whenCreateTransactionSavedWithSucess(){
        Transaction transaction = getTransaction();

        Transaction transactionExpected=getTransaction();

        when(repository.save(transaction)).thenReturn(transactionExpected);

        transactionService.save(transaction);
        verify(repository).save(transaction);
    }
    @Test
    void shouldReturnTransactionsForGivenDate(){
        LocalDateTime localDateTime;

        when(repository.findByDate())
    }

    private Transaction getTransaction() {
        Transaction transaction= new Transaction();
        Account payer= new Account();
        Account payee= new Account();

        transaction.setDate(LocalDateTime.of(2024,03,29,00,00));
        transaction.setPayee(payee);
        transaction.setPayer(payer);
        transaction.setValue(100d);
        return transaction;
    }

}