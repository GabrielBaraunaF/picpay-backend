package picpay.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import picpay.entity.Account;
import picpay.entity.User;
import picpay.exception.ApplicationException;
import picpay.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private DefaultUserService userService;
@Test
    void givenUserWhenDeleteByIdMarksUserInactive() {


    }


    @Test
    @DisplayName("saving a new user with an existing CPF should throw an error")
    void givenNewUserWhenSavedWithAlreadyRegisteredCpfMustThrowException() {

        String cpf = "999.999.999-99";
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
    @DisplayName("Saving a new user with an existing email should throw an error")
    void givenNewUserWhenSavedWithAlreadyRegisteredEmailMustThrowException() {
        String email = "gabriel@gmail.com";
        User userPO = new User();
        userPO.setEmail(email);
        userPO.setName("gabriel");

        when(repository.findByEmail(email)).thenReturn(userPO);

        User user = new User();
        user.setName("Gabriel");
        user.setEmail(email);

        assertThrows(ApplicationException.class, () -> userService.save(user));

    }

    @Test
    @DisplayName("Saving a new user with an existing email should throw an error")
    void givenNewUserWhenSavedWithAlreadyRegisteredPixlMustThrowException() {
        String pix = "gabriel@gmail.com";
        User userPO = getUser();

        when(repository.findByAccountPix(pix)).thenReturn(userPO);

        User user = getUser();
        user.setEmail(null);
        user.setCpf(null);

        assertThrows(ApplicationException.class, () -> userService.save(user));

    }


    @Test
    @DisplayName("Should save a new user successfully")
    void givenNewUserWhenSaveReturnUserCreated() {

        User user = getUser();

        User userExpected = getUser();
        userExpected.setId(1);

        when(repository.save(user)).thenReturn(userExpected);

        User userActual = userService.save(user);

        assertNotNull(userActual.getId());
        assertEquals(userExpected, userActual);
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