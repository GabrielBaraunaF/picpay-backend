package picpay.service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class DefaultEmailSender implements EmailSenderService {


    public static final String MAIL = "luizsergio.barauna@gmail.com";

    @Override
    public void sendEmail(String emailTo, String bodyEmail, String subjectEmail)  {
        String to = emailTo; // Substitua pelo endereço de email do destinatário
        String from = MAIL; // Substitua pelo seu endereço de email
        String subject = subjectEmail;
        String body = bodyEmail;

        try {
            // Cria uma mensagem de email
            Message message = new MimeMessage(getSession());
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(body);

            // Envia o email
            Transport.send(message);

            System.out.println("Email enviado com sucesso!");

        } catch (MessagingException e) {
            System.out.println("Falha ao enviar email. Erro: " + e.getMessage());
        }

    }

    private Session getSession() {
        String host = "smtp.gmail.com"; // Substitua pelo servidor de email SMTP que você está usando
        String username = MAIL; // Substitua pelo seu endereço de email
        String password = "dosg ixtb suao ofut"; // Substitua pela sua senha de email

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
