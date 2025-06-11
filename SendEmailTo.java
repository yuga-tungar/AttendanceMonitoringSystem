package Final;
import java.io.FileInputStream;
import java.io.IOException;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class SendEmailTo {
final String senderEmail;
final String senderPassword;
// Load credentials from config.properties
public SendEmailTo(String receiverEmail, String subject, String body) {
    Properties config = new Properties();
    try (FileInputStream fis = new FileInputStream("config.properties")) {
        config.load(fis);
        this.senderEmail = config.getProperty("smtp.email");
        this.senderPassword = config.getProperty("smtp.password");
    } catch (IOException e) {
        throw new RuntimeException("Failed to load config.properties: " + e.getMessage());
    }
    final String emailSMTPServer = "smtp.gmail.com";
    final String emailServerPort = "465";
    String receiverEmail;
    String emailSubject;
    String emailBody;

    public SendEmailTo(String receiverEmail, String subject, String body) {
        this.receiverEmail = receiverEmail;
        this.emailSubject = subject;
        this.emailBody = body;

        Properties props = new Properties();
        props.put("mail.smtp.user", senderEmail);
        props.put("mail.smtp.host", emailSMTPServer);
        props.put("mail.smtp.port", emailServerPort);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.port", emailServerPort);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");

        try {
            Authenticator auth = new SMTPAuthenticator();
            Session session = Session.getInstance(props, auth);
            MimeMessage msg = new MimeMessage(session);
            msg.setText(emailBody);
            msg.setSubject(emailSubject);
            msg.setFrom(new InternetAddress(senderEmail));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(receiverEmail));
            Transport.send(msg);
            System.out.println("Message sent successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class SMTPAuthenticator extends Authenticator {
        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(senderEmail, senderPassword);
        }
    }
}
