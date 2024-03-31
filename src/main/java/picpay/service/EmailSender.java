package picpay.service;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {

    public static void main(String[] args) {
        // Configurações do servidor de email
        String host = "smtp.gmail.com"; // Substitua pelo servidor de email SMTP que você está usando
        String username = "luizsergio.barauna@gmail.com"; // Substitua pelo seu endereço de email
        String password = "dosg ixtb suao ofut"; // Substitua pela sua senha de email

        // Configurações do email
        String to = "gbferreira08@gmail.com"; // Substitua pelo endereço de email do destinatário
        String from = "luizsergio.barauna@gmail.com"; // Substitua pelo seu endereço de email
        String subject = "Assunto do Email";
        String body = "Corpo do Email";

        // Configurações adicionais, se necessário
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        // Cria uma sessão de email
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(username, password);
            }
        });

        try {
            // Cria uma mensagem de email
            Message message = new MimeMessage(session);
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
}

