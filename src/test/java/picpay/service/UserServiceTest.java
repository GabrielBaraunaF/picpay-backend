package picpay.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import picpay.entity.Account;
import picpay.entity.User;
import picpay.exception.ApplicationException;
import picpay.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private DefaultUserService userService;

    @Test
    void givenNewUserWhenSavedWithAlreadyRegisteredCpfMustThrowException() {

        String cpf  = "999.999.999-99";
        User userPO = new User();
        userPO.setCpf(cpf);
        userPO.setName("Sergio");

        when(repository.findByCpf(cpf)).thenReturn(userPO);

        User user = new User();
        user.setName("Gabriel");
        user.setCpf(cpf);

        assertThrows(ApplicationException.class, () -> userService.save(user));

    }

    @Test
    void givenNewUserWhenSaveReturnUserCreated() {

        User user = getUser();

        User userExpected = getUser();
        userExpected.setId(1);

        when(repository.save(user)).thenReturn(userExpected);

        User userActual =  userService.save(user);

        assertNotNull(userActual.getId());
        assertEquals(userExpected,userActual);
    }

    private static User getUser() {
        User user = new User();
        user.setName("Gabriel");
        user.setCpf("999.999.999-99");

        Account account = new Account();
        account.setBalance(10d);
        account.setNumber(1);
        account.setPix("gabriel@gmail.com");
        user.setAccount(account);
        return user;
    }

}