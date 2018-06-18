package com.transport.transportation.email;

import com.transport.transportation.entity.Driver;
import com.transport.transportation.entity.Ecommerce;
import com.transport.transportation.entity.EcommerceTaxiRequest;
import com.transport.transportation.entity.SignUp;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;

@Service
public class EcommerceReqSendEmailToAdmin {

    public static void main(String[] args) {
        EcommerceReqSendEmailToAdmin mail = new EcommerceReqSendEmailToAdmin();
        SignUp user = new SignUp();
        user.setEmail("rkrgarlapati@gmail.com");
        user.setPassword("password");
        //mail.sendMail(user);
        //mail.sendMail();
    }

    public void sendEcommerceTaxiRequestEmails(List<String> allEmailIDs, EcommerceTaxiRequest transReq,
                                               Ecommerce ecommerce, String status) {

        try {
            Message message = new MimeMessage(getMailSession());
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(StringUtils.join(allEmailIDs, ',')));
            if (status.equals("A")) {
                message.setSubject("Ecommerce Order Admin Approved. Request # " + transReq.getRequestid());
            } else if (status.equals("P")) {
                message.setSubject("Ecommerce Order received. Request # " + transReq.getRequestid());
            }

            message.setText(" Hi,"
                    + "\n\n Ecommerce Order details : " +
                    "\n Product Name :" + ecommerce.getProductname() +
                    "\n Product Price:" + ecommerce.getPrice() +
                    "\n Email       :" + transReq.getUser().getEmail() +
                    "\n MobileNo    :" + transReq.getMobileNo() +
                    "\n DateTime    :" + transReq.getDateTime() +
                    "\n Status      :" + getString(transReq.getRequestStatus()) +
                    "\n UserType    :" + transReq.getUserType());

            Transport.send(message);

            System.out.println("Ecommerce Request Mail Sent");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private String getString(String status) {
        String reqStatus;

        if (status.equals("P")) {
            reqStatus = "PENDING";
        } else if (status.equals("R")) {
            reqStatus = "REJECTED";
        } else if (status.equals("A")) {
            reqStatus = "APPROVED";
        } else {
            reqStatus = "ACKNOWLEDGE";
        }

        return reqStatus;
    }

    public void sendEmailToAdminsAndCustomer(List<String> allAdminEmailIDs, EcommerceTaxiRequest taxiRequest, Driver driver, String otp) {

        new Thread(() -> {
            sendMail(StringUtils.join(allAdminEmailIDs, ','), taxiRequest.getRequestid(), driver, otp);
            sendMail(taxiRequest.getUser().getEmail(), taxiRequest.getRequestid(), driver, otp);
        }).start();
    }

    public void sendMail(String emailids, int requestid, Driver driver, String otp) {
        try {
            Message message = new MimeMessage(getMailSession());
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(emailids));
            message.setSubject("Driver Accepted. Request # " + requestid);
            message.setText(" Hi,"
                    + "\n\n OTP  : " + otp
                    + "\n\n Driver details : " +
                    "\n\n Name        :" + driver.getName() +
                    "\n Mobile        :" + driver.getMobile() +
                    "\n Vehicle Color :" + driver.getVehiclecolor() +
                    "\n Vechile Make  :" + driver.getVehiclemake() +
                    "\n Vehile Num    :" + driver.getVehiclenum());

            Transport.send(message);

            System.out.println("Ecommerce Product Request with OTP Mail Sent");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private Session getMailSession() {
        final String serverusername = "newecommercerequest@gmail.com";
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

        return session;
    }
}