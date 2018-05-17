package com.transport.transportation.email;

import com.transport.transportation.entity.SignUp;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class SignUpSendEmail {

    public static void main(String[] args) {
        SignUpSendEmail mail = new SignUpSendEmail();
        SignUp user = new SignUp();
        user.setEmail("rkrgarlapati@gmail.com");
        user.setPassword("password");
        mail.sendMail(user);
        //mail.sendMail();
    }

    public void sendMail(SignUp user) {

        final String serverusername = "registermycredentials@gmail.com";
        final String serverpassword = "Password111!";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(serverusername, serverpassword);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(user.getEmail()));
            message.setSubject("Registration Successful!");
            message.setText(" Hi,"
                    + "\n\n Your login credentials : "
                    + "\n\n  LoginID    : " + user.getEmail()
                    + "\n  Password : " + user.getPassword()
                    + "\n\n Regards.");

            Transport.send(message);

            System.out.println("Registration Mail Sent");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}