package picpay.service;

import org.springframework.beans.factory.annotation.Autowired;
import picpay.entity.User;
import picpay.exception.ApplicationException;
import picpay.repository.UserRepository;

import java.util.Optional;

public class DefaultUserService implements UserService {

    @Autowired
    private UserRepository repository;
    private User userPO;

    @Override
    public User save(User user) {
        if (user.getId() == null) {
            validateinsert(user);
        } else {
            validateUpdate(user);
        }
        return user;
    }

    @Override
    public User findById(Integer id) {
        Optional<User> user = repository.findById(id);
        if (user.isPresent()) {
            return user.get();
        }
        return null;
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }


    private void validateinsert(User user) {
        User userPO = repository.findByCpf(user.getCpf());
        if (userPO != null) {
            throw new ApplicationException("cpf ja cadastrado na base");
        }
        User userPO2 = repository.findByEmail(user.getEmail());
        if (userPO2 != null) {
            throw new ApplicationException("email ja cadastrado na base");
        }
        repository.save(user);
    }

    private void validateUpdate(User user) {
        User userPO = repository.findByAccountPIX(user.getAccount().getPix());
        if (userPO == null) {
            if (userPO.getAccount().getNumber().equals(user.getAccount().getNumber())) {
            } else {
                repository.save(user);
            }
        } else {
            throw new ApplicationException("PIX ja cadastrado na base");
        }
    }

}