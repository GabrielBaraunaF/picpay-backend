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
        //Aplicar um algoritimo de hash na senha antes de salvar o objetp
        //Pesquise no ChatGPT assim: "Aplicar um hash senha java" teste a soluçao e depois import a mesma para classe EncryptionUtil
        return repository.save(user);
    }

    @Override
    public User authenticate(User user) {

        // recuperar o user gravado no banco pelo email setado no usuario passado como parametro
        // aplicar o mesmo algoritimo de hash no password vindo no objeto user passado como parametro
        // se o user recuperado do banco não for nulo e o password do mesmo for igual ao password do user passado como parametro, retorno o user do banco
        // se a condicão acima falha retornar uma ApplicationException informando que Email ou senha invélidos
        return null;
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
       if (user.isPresent()){
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

}