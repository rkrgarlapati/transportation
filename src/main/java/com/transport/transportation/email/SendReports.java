package com.transport.transportation.email;

import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

@Service
public class SendReports {
    public static void main(String[] args) {
        SendReports mail = new SendReports();
        mail.sendMail("","SendTransportDocument.pdf", "rkrgarlapati@gmail.com");
    }

    public void sendMail(String fullpath, String filename, String toEmail) {

        System.out.println("sending file :"+filename);

        final String serverusername = "generatetransportreport@gmail.com";
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
            message.setSubject("Transport Request Report");

            Multipart multipart = new MimeMultipart();
            BodyPart messageBodyPart = new MimeBodyPart();
            //String filename = "abc.txt";
            DataSource source = new FileDataSource(fullpath);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);
            /*messageBodyPart.setText(" Hi,"
                    + "\n\n Please find the attached report."
                    + "\n\n Regards.");*/
            multipart.addBodyPart(messageBodyPart);

            // Send the complete message parts
            message.setContent(multipart);
            System.out.println("file attached :"+filename);
            Transport.send(message);

            System.out.println("Report mail sent..");

        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
