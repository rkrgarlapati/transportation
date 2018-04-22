package com.transport.transportation.utility;

import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class ForgotPasswordSendEmail {

    public static void main(String[] args) {
        ForgotPasswordSendEmail mail = new ForgotPasswordSendEmail();
        mail.sendMail("password", "Ravi", "rkrgarlapati@gmail.com");
    }

    public void sendMail(String password, String name, String toEmail) {

        final String serverusername = "forgotmycredentials@gmail.com";
        final String serverpassword = "Password111!";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(serverusername, serverpassword);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(toEmail));
            message.setSubject("Forgot Password");
            message.setText(" Dear " + name + ","
                    + "\n\n Your login password : " + password
                    + "\n\n Regards.");

            Transport.send(message);

            System.out.println("Forgot Password Mail Sent");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}