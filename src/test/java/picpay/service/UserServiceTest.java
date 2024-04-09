package picpay.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import picpay.entity.Account;
import picpay.entity.User;
import picpay.exception.ApplicationException;
import picpay.repository.UserRepository;

import java.security.MessageDigest;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private DefaultUserService userService;

    @Captor
    ArgumentCaptor<User> userCaptor;

    @Test
    void givenUserWhenDeleteByIdMarksUserInactive() {
        User userPO = getUser();

        Optional<User> userOptional = Optional.of(userPO);

        when(repository.findById(userPO.getId())).thenReturn(userOptional);

        userService.deleteById(userPO.getId());

        verify(repository).save(userCaptor.capture());

        assertEquals(false, userCaptor.getValue().getActive());
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
    @DisplayName("Saving a new user with an existing pix should throw an error")
    void givenNewUserWhenSavedWithAlreadyRegisteredPixlMustThrowException() {
        String pix = "gabriel@gmail.com";
        User userPO = getUser();

        when(repository.findByAccountPix(pix)).thenReturn(userPO);

        User user = getUser();

        ApplicationException exception = assertThrows(
                ApplicationException.class,
                () -> {
                    userService.save(user);
                }
        );

        assertEquals("PIX ja associado a outro usuário", exception.getMessage());
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

    @Test
    void shouldThrowExceptionWhenAuthenticateUserEmailNotFound() {
        User user = getUser();
        user.setPassword("123456");
        user.setEmail("teste@gmail.com");

        when(repository.findByEmail("teste@gmail.com")).thenReturn(null);

        ApplicationException exception = assertThrows(
                ApplicationException.class,
                () -> {
                    userService.authenticate(user);
                }
        );
        assertThrows(ApplicationException.class, () -> userService.authenticate(user));
        assertEquals("email ou senha invalidos", exception.getMessage());

    }

    @Test
    void shouldThrowExceptionWhenAuthenticateUserPasswordNotEquals() {
        User user = getUser();
        user.setPassword(generateHashPassword("123456"));
        user.setEmail("teste@gmail.com");

        when(repository.findByEmail(user.getEmail())).thenReturn(user);

        User userExpected = getUser();
        userExpected.setPassword("12345");
        userExpected.setEmail("teste@gmail.com");

        ApplicationException exception = assertThrows(
                ApplicationException.class,
                () -> {
                    userService.authenticate(userExpected);
                }
        );
        assertEquals("email ou senha invalidos", exception.getMessage());

    }

    @Test
    void givenUserAuthenticateWithSuccess() {
        User user = getUser();
        user.setEmail("teste@gmail.com");

        User userExpected = getUser();
        userExpected.setPassword(generateHashPassword("123456"));
        userExpected.setEmail("teste@gmail.com");

        when(repository.findByEmail("teste@gmail.com")).thenReturn(userExpected);

        User userCompare = getUser();
        userCompare.setPassword("123456");
        userCompare.setEmail("teste@gmail.com");
        User userActual = userService.authenticate(userCompare);

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