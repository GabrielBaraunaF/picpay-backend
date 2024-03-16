package picpay.service;

import org.springframework.beans.factory.annotation.Autowired;
import picpay.entity.User;
import picpay.exception.ApplicationException;
import picpay.repository.UserRepository;

import java.util.Optional;

public class DefaultUserService implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public User save(User user) {
        if (user.getId() == null) {
            validateinsert(user);
        } else {
            validateUpdate(user);
        }
        return repository.save(user);
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

        userPO = repository.findByEmail(user.getEmail());

        if (userPO != null) {
            throw new ApplicationException("email já cadastrado na base");
        }

        userPO = repository.findByAccountPix(user.getAccount().getPix());

        if (userPO != null) {
            throw new ApplicationException("PIX ja associado a outro usuário");
        }
    }

    private void validateUpdate(User user) {
        User userPO = repository.findByAccountPix(user.getAccount().getPix());

        if (userPO != null && !userPO.getId().equals(user.getId())) {
            throw new ApplicationException("PIX ja associado a outro usuário");
        }
    }

}