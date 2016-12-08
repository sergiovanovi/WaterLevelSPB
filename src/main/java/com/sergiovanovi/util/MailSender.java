package com.sergiovanovi.util;

import com.sergiovanovi.model.User;
import com.sergiovanovi.service.MeterService;
import com.sergiovanovi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;

public class MailSender {

    private final UserService userService;
    private final MeterService meterService;

    @Autowired
    public MailSender(UserService userService, MeterService meterService) {
        this.userService = userService;
        this.meterService = meterService;
    }

    @Scheduled(fixedDelay = 1800000)//30minute
    public void checkMeter() {
        double meter = meterService.getLastMeter();
        List<User> listUsers = userService.listAllUsers();

        for (User user : listUsers) {
            double util = user.getUtil();
            double min = user.getMin();
            double max = user.getMax();

            if (meter > max && util != 3) {
                try {
                    sendEmail(user.getMail(), "Уровень воды в порту Санкт-Петербурга выше " + user.getMax(), meter);
                    user.setUtil(3);
                    userService.updateUser(user);
                } catch (MessagingException e) {
                    System.out.println("Sending email fail");
                    e.printStackTrace();
                }
            } else if (meter < min && util != 1) {
                try {
                    sendEmail(user.getMail(), "Уровень воды в порту Санкт-Петербурга ниже " + user.getMin(), meter);
                    user.setUtil(1);
                    userService.updateUser(user);
                } catch (MessagingException e) {
                    System.out.println("Sending email fail");
                    e.printStackTrace();
                }
            } else if (meter <= max && meter >= min && util != 2) {
                try {
                    sendEmail(user.getMail(), "Уровень воды в порту Санкт-Петербурга в пределах от " + user.getMin() + " до " + user.getMax(), meter);
                    user.setUtil(2);
                    userService.updateUser(user);
                } catch (MessagingException e) {
                    System.out.println("Sending email fail");
                    e.printStackTrace();
                }
            }
        }
    }

    public void sendEmail(String email, String message, double meter) throws MessagingException {
        String from = "waterlevelinfospb@mail.ru";
        String to = email;
        String username = "waterlevelinfospb@mail.ru";
        String password = "Assword11";

        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", "smtp.mail.ru");
        properties.setProperty("mail.smtp.port", "465");
        properties.setProperty("mail.smtp.socketFactory.port", "465");
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        MimeMessage mimeMessage = new MimeMessage(session);
        mimeMessage.setFrom(new InternetAddress(from));
        mimeMessage.addRecipients(Message.RecipientType.TO, to);
        mimeMessage.setSubject(message);
        mimeMessage.setText(message + ".\n" + "Текущий уровень " + meter + " см." + "\n" + "Подробнее тут http://www.pasp.ru/op-info-weather?mode=current");
        Transport.send(mimeMessage);
        System.out.println("Sending email done");

    }
}
