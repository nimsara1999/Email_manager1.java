package Emailclient;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

class TLSMailSender {
    public static boolean sendmail1(String recipientmail,String subject, String content) {
        final String username = "nimsarathisalgcc@gmail.com";
        final String password = "scpkmquhggruakqj";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientmail));
            message.setSubject(subject);
            message.setText(content);
            Transport.send(message);
            System.out.println("mail successfully sent");

        } catch(AddressException e){
            System.out.println("Email Address error");
        }
        catch (MessagingException e) { //if any error while mail send
            System.out.println("Error while mail sending.");
            return false;
        }
        return true;
    }
}

