package picpay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import picpay.dto.TransactionDTO;
import picpay.dto.UserDTO;
import picpay.facade.UserFacade;

import java.time.LocalDate;
import java.util.List;


@RestController()
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserFacade userFacade;

    @GetMapping("/transaction")
    public List<TransactionDTO> transactionHistory(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate date){
        Integer accountNumber = 2;
        return userFacade.transactionHistory(date,accountNumber);
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



}
