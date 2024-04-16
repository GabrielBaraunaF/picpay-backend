package picpay.facade;

import org.springframework.stereotype.Service;
import picpay.dto.TransactionDTO;
import picpay.dto.UserDTO;

import java.time.LocalDate;
import java.util.List;

public interface UserFacade {

    public UserDTO createAccount(UserDTO userDTO);

    //public void inactivateAccount(UserDTO userDTO);

    public List<TransactionDTO> transactionHistory(LocalDate date, int accountNumber);

    //public void transfer(Integer accountNumberPayer, Integer accountNumberPayee, double valor );

}
