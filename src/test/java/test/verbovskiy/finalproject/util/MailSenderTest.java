package test.verbovskiy.finalproject.util;

import com.verbovskiy.finalproject.exception.SendMailException;
import com.verbovskiy.finalproject.util.mail.MailSender;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class MailSenderTest {
    MailSender sender;

    @BeforeClass
    public void setUp() {

    }

    @Test
    public void sendMessagePositiveTest() throws SendMailException {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("config\\mail.properties"));
            String mailTo = "sergeiverbovskiy4@gmail.com";
            String subject = "Sample Mail";
            String body = "Hello java mail";
            MailSender sender = new MailSender(mailTo, subject, body, properties);
            sender.send();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}