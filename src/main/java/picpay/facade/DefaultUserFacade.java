package picpay.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import picpay.dto.TransactionDTO;
import picpay.dto.UserDTO;
import picpay.entity.Account;
import picpay.entity.Transaction;
import picpay.entity.User;
import picpay.service.TransactionService;
import picpay.service.UserService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DefaultUserFacade implements UserFacade {
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private UserService userService;

    @Override
    public UserDTO createAccount(UserDTO userDTO) {
        User user = userService.save(converterUser(userDTO));
        userDTO.setId(user.getId());
        userDTO.setAccountNumber(user.getAccount().getNumber());
        return userDTO;
    }

    @Override
    public void inactivateAccount(UserDTO userDTO) {
        userService.deleteById(userDTO.getId());
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

    private User converterUser(UserDTO userDTO) {
        User user = new User();
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setCpf(userDTO.getCpf());
        user.setName(userDTO.getName());

        Account account = new Account();
        user.setAccount(account);
        account.setPix(userDTO.getKeyPix());
        return user;
    }
}
