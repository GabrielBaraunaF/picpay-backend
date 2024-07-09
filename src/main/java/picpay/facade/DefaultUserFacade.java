package picpay.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import picpay.api.DefaultTransferAuthorizerAPI;
import picpay.dto.AccountDTO;
import picpay.dto.TransactionDTO;
import picpay.dto.UserDTO;
import picpay.entity.Account;
import picpay.entity.Transaction;
import picpay.entity.User;
import picpay.service.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class   DefaultUserFacade implements UserFacade {
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private UserService userService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private EmailSenderService emailSenderService;
    @Autowired
    private DefaultTransferAuthorizerAPI transferAuthorizerAPI;


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

    @Override
    public void transfer(AccountDTO accountDTO) {

        //preciso verificar se é um usuario do tipo certo, no caso o usuario logado.
        if (transferAuthorizerAPI.isAuthorized()){
            accountService.debitar(accountDTO.getAccountIdLogged(), accountDTO.getValue());
            accountService.creditar(accountDTO.getKeypix(), accountDTO.getValue());
            emailSenderService.sendEmail("gbferreira08@gmail.com","Foi transferido para sua conta um valor de R$"+accountDTO.getValue(),"valor transferido para sua conta");

        }


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
