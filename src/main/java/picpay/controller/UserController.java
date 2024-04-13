package picpay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import picpay.dto.TransactionDTO;
import picpay.facade.UserFacade;

import java.time.LocalDate;
import java.util.List;


@RestController()
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserFacade userFacade;
    @GetMapping("{}")
    public List<TransactionDTO> transactionHistory(@PathVariable LocalDate date, @PathVariable Integer id){
        return userFacade.transactionHistory(date,id);
    }



}
