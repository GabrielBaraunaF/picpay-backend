package picpay.service;

import org.springframework.stereotype.Service;
import picpay.entity.Account;

public interface AccountService {

    void debitar(Integer number,Double value);
    void creditar(String keypix,Double value);
}
