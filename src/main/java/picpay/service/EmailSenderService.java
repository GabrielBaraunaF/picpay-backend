package picpay.service;

import org.springframework.stereotype.Service;

@Service
public interface EmailSenderService {
    void sendEmail(String emailTo,String body, String subject);
}
