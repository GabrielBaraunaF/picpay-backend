package picpay.service;

import org.springframework.stereotype.Service;
import picpay.entity.User;

@Service
public interface UserService {

     User findById(Integer id);
     void deleteById(Integer id);
     User save(User user);


}
