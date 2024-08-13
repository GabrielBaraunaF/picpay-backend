package picpay.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import picpay.entity.Account;
import picpay.entity.Transaction;
import picpay.repository.TransactionRepository;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {
    @Mock
    private TransactionRepository repository;
    @InjectMocks
    private DefaultTransactionService transactionService;

    @Captor
    ArgumentCaptor<Integer> dayCaptor;
    @Captor
    ArgumentCaptor<Integer> monthCaptor;
    @Captor
    ArgumentCaptor<Integer> yearCaptor;
    @Captor
    ArgumentCaptor<Integer> numberCaptor;


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
        LocalDate localDate=LocalDate.now();
        List<Transaction> transactionlist=getTransactionList();
        int number=2255;

        when(repository.findByDate(anyInt(),anyInt(),anyInt(),anyInt())).thenReturn(transactionlist);

        List<Transaction>transactionExpected = transactionService.transactionByDate(localDate,number);

        verify(repository).findByDate(dayCaptor.capture(),monthCaptor.capture(),yearCaptor.capture(),numberCaptor.capture());

        assertEquals(transactionExpected,transactionlist);
        assertEquals(localDate.getYear(),yearCaptor.getValue());
        assertEquals(localDate.getMonthValue(),monthCaptor.getValue());
        assertEquals(localDate.getDayOfMonth(),dayCaptor.getValue());
        assertEquals(number,numberCaptor.getValue());

    }
    private List getTransactionList(){
        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(getTransaction());
        transactionList.add(getTransaction());
        transactionList.add(getTransaction());
        return transactionList;
    }

    private Transaction getTransaction() {
        Transaction transaction= new Transaction();
        Account payer= new Account();
        Account payee= new Account();

        transaction.setDate(LocalDateTime.of(2024,03,30,00,00));
        transaction.setPayee(payee);
        transaction.setPayer(payer);
        transaction.setValue(100d);
        return transaction;
    }

}