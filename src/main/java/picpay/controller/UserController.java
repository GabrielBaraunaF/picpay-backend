package picpay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import picpay.api.TransferAuthorizerAPI;

@RestController()
@RequestMapping("/user")
public class UserController {
    @Autowired
    private TransferAuthorizerAPI transferAuthorizerAPI;
    @GetMapping("/teste")

    public String teste(){
    return String.valueOf(transferAuthorizerAPI.isAuthorized());
    }
}
