package picpay.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import picpay.exception.ApplicationException;

import javax.mail.Session;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class EmailSenderTest {
    @InjectMocks
    private DefaultEmailSender emailSenderService;

    @Test
    void shouldthrowExceptionWhenSendEmailFailed(){
        ApplicationException exception = assertThrows(
                ApplicationException.class,
                () -> { emailSenderService.sendEmail("testegmail.com","corpo","corpo"); }
        );
        assertEquals("erro ao enviar o email",exception.getMessage());
    }
    void givenEmailSendWithSucess(){

    }
}