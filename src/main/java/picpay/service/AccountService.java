package picpay.service;

import org.springframework.stereotype.Service;
import picpay.entity.Account;
@Service
public interface AccountService {

    void debitar(Integer number,Double value);
    void creditar(Integer number,Double value);
}