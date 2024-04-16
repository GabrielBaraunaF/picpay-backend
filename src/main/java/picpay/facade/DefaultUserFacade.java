package picpay.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import picpay.dto.TransactionDTO;
import picpay.dto.UserDTO;
import picpay.entity.Transaction;
import picpay.service.TransactionService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
@Component
public class DefaultUserFacade implements UserFacade {
    @Autowired
    private TransactionService transactionService;

    @Override
    public UserDTO createAccount(UserDTO userDTO) {
        return null;
    }

    @Override
    public List<TransactionDTO> transactionHistory(LocalDate date, int accountNumber) {
        List<TransactionDTO> transactionDTOS = converter(transactionService.transactionHistory(date, accountNumber));
        return transactionDTOS;
    }

    private List<TransactionDTO> converter(List<Transaction> transactions) {
        return transactions
                .stream()
                .map(dto -> {
                    TransactionDTO transactionDTO = new TransactionDTO();
                    transactionDTO.setPayer(dto.getPayer().getNumber());
                    transactionDTO.setValue(dto.getValue());
                    transactionDTO.setPayee(dto.getPayee().getNumber());
                    transactionDTO.setDate(dto.getDate());
                    return transactionDTO;
                }).collect(Collectors.toList());
    }
}
