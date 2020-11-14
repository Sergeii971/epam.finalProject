package com.verbovskiy.finalproject.util.mail;

import com.verbovskiy.finalproject.exception.SendMailException;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * The type Mail sender.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
public class MailSender {
    private MimeMessage message;
    private final String sendToEmail;
    private final String mailSubject;
    private final String mailText;
    private final Properties properties;
    private static final String PROPERTY_NAME = "config\\mail.properties";

    public MailSender(String sendToEmail, String mailSubject, String mailText) throws SendMailException {
        try {
            this.sendToEmail = sendToEmail;
            this.mailSubject = mailSubject;
            this.mailText = mailText;
            properties = new Properties();
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            InputStream inputStream = loader.getResourceAsStream(PROPERTY_NAME);
            properties.load(inputStream);
        } catch (IOException e) {
            throw new SendMailException(e);
        }
    }

    public void send() throws SendMailException {
        try {
            initMessage();
            Transport.send(message);
        } catch (MessagingException e) {
            throw new SendMailException(e);
        }
    }

    private void initMessage() throws MessagingException {
        Session mailSession = SessionFactory.createSession(properties);
        mailSession.setDebug(true);
        message = new MimeMessage(mailSession);
        message.setSubject(mailSubject);
        message.setContent(mailText, "text/html");
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(sendToEmail));
    }
}

