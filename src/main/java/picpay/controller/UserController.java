package picpay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import picpay.dto.AccountDTO;
import picpay.dto.TransactionDTO;
import picpay.dto.UserDTO;
import picpay.entity.User;
import picpay.facade.UserFacade;
import picpay.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;


@RestController()
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserFacade userFacade;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/transaction")
    public List<TransactionDTO> transactionHistory(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate date){
        Integer accountNumber = 2;
        return userFacade.transactionByDate(date,accountNumber);
    }
    @PostMapping("/")
    public UserDTO createAccount(@RequestBody UserDTO userDTO){
        return userFacade.createAccount(userDTO);
    }
    @DeleteMapping("/")
    public void inactiveAccount(){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1);
        userFacade.inactivateAccount(userDTO);
    }
    @PostMapping("/transaction")
    public void createTransaction(@RequestBody AccountDTO accountDTO){
        userFacade.transfer(accountDTO);
    }
    @GetMapping("/user")
    public UserDTO getuser(){
        User user =userRepository.findByEmail("gbferreira08@gmail.com");
        UserDTO userDTO=new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setCpf(user.getCpf());
        userDTO.setEmail(user.getEmail());
        userDTO.setType(user.getType());
        userDTO.setAccountNumber(user.getAccount().getNumber());
        userDTO.setKeyPix(user.getAccount().getPix());
        userDTO.setActive(user.getActive());
        return userDTO;
    }
    @GetMapping("/transactions")
    public List<TransactionDTO> allTransaction(){
        Integer accountNumber = 1;
        return userFacade.transactionHistory(accountNumber);
    }



}
