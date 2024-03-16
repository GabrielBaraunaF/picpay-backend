package picpay.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import picpay.dto.UsersDTO;
import picpay.entity.User;
import picpay.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DefaultUserServiceTest {
    @InjectMocks
    private UserRepository repository;
    @Mock
    private DefaultUserService service;

    private User user;
    private UsersDTO usersDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save() {
    }

    @Test
    void findById() {
    }

    @Test
    void deleteById() {
    }

    private void stateruser(){
        
    }

}