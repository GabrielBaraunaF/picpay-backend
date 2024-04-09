package picpay.service;

import org.springframework.beans.factory.annotation.Autowired;
import picpay.entity.User;
import picpay.exception.ApplicationException;
import picpay.repository.UserRepository;

import java.security.MessageDigest;
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
        user.setPassword(generateHashPassword(user.getPassword()));
        return repository.save(user);
    }

    @Override
    public User authenticate(User user) {
        user.setPassword(generateHashPassword(user.getPassword()));

        User userExists = repository.findByEmail(user.getEmail());

        if (userExists != null && userExists.getPassword().equals(user.getPassword())) {
            return userExists;
        }
        throw new ApplicationException("email ou senha invalidos");
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
        Optional<User> user = repository.findById(id);
        if (user.isPresent()) {
            inactiveUser(user.get());
        }
    }

    private void inactiveUser(User user) {
        user.setActive(false);
        repository.save(user);
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

    private String generateHashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (Exception ex) {
            throw new ApplicationException("falha ao gerar senha");
        }
    }

}