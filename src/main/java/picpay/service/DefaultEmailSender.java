package picpay.service;

import org.springframework.stereotype.Service;
import picpay.exception.ApplicationException;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
@Service
public class DefaultEmailSender implements EmailSenderService {

    private final String EMAIL_FROM = "luizsergio.barauna@gmail.com";

    @Override
    public void sendEmail(String emailTo, String bodyEmail, String subjectEmail)  {
        try {
            Message message = new MimeMessage(getSession());
            message.setFrom(new InternetAddress(EMAIL_FROM));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTo));
            message.setSubject(subjectEmail);
            message.setText(bodyEmail);

            Transport.send(message);

        } catch (MessagingException e) {
            throw new ApplicationException("erro ao enviar o email");
        }

    }

    private Session getSession() {
        String host = "smtp.gmail.com";
        String username = EMAIL_FROM;
        String password = "dosg ixtb suao ofut";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(username, password);
            }
        });
        return session;
    }


}
