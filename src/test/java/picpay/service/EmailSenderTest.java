package picpay.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import picpay.exception.ApplicationException;

import javax.mail.Session;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class EmailSenderTest {
    @InjectMocks
    private DefaultEmailSender emailSenderService;
    @Mock
    private Session session;

    @Test
    void shouldthroeExceptionWhenSendEmailFailed(){
        ApplicationException exception = assertThrows(
                ApplicationException.class,
                () -> { emailSenderService.sendEmail("testegmail.com","corpo","corpo"); }
        );
        assertEquals("erro ao enviar o email",exception.getMessage());
    }
}