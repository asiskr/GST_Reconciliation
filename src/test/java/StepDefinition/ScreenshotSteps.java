package StepDefinition;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import com.asis.util.BaseClass;

import Pages.Screenshot;
import io.cucumber.java.en.*;

public class ScreenshotSteps extends BaseClass {

    private Screenshot sc = new Screenshot();
    private String recipientEmail = "asis.kaur@theoutsourcepro.com.au";

    @Given("User launch google website")
    public void user_launch_google_website() throws InterruptedException {
        BaseClass.setupDriver("Chrome");
        BaseClass.lauchSite("https://www.google.com/");
    }

    @Then("send sc")
    public void send_sc() {
        String from = "toptechautomation@theoutsourcepro.com.au";
        String password = "Guf87765";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.office365.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("Screenshot");

            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("Please find attached screenshot");

            MimeBodyPart attachmentBodyPart = new MimeBodyPart();
            byte[] screenshotBytes = sc.captureAndConvertScreenshot(); // Assuming this method exists in Screenshot class
            DataSource source = new ByteArrayDataSource(screenshotBytes, "image/png");
            attachmentBodyPart.setDataHandler(new DataHandler(source));
            attachmentBodyPart.setFileName("screenshot.png");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(attachmentBodyPart);

            message.setContent(multipart);

            Transport.send(message);

            System.out.println("Email sent successfully.");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
