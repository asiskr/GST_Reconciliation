package StepDefinition;

import java.net.MalformedURLException;
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

import Pages.ATOLoginBusinessPage;
import Pages.ATOLoginPage;
import Pages.SelectTOPIdPage;
import io.cucumber.java.en.*;

public class ATOLoginStep extends BaseClass {

    private ATOLoginBusinessPage businessPage;
    private ATOLoginPage loginPage;
    private String recipientEmail = "asis.kaur@theoutsourcepro.com.au";

    @Given("User launch website")
    public void user_launch_website() throws InterruptedException, MalformedURLException {
        setupDriver("Chrome");
        loginPage = new ATOLoginPage();
        businessPage = new ATOLoginBusinessPage();

        if (TAXATION.equalsIgnoreCase("taxation")) {
            lauchSite("https://onlineservices.ato.gov.au/onlineservices/");
            loginPage.clickOnMyGOVButton();
            loginPage.sendingEmailAddress();
            byte[] screenshotBytes = loginPage.clickOnLoginButton();
            sendScreenshotEmail(recipientEmail, screenshotBytes);
        } else {
            lauchSite("https://onlineservices.ato.gov.au/Business/?logout=true");
            businessPage.clickOnBusinessAccountLoginButton();
            businessPage.sendingEmailAddress();
            byte[] screenshotBytes = businessPage.clickOnLoginButton();
            sendScreenshotEmail(recipientEmail, screenshotBytes);
        }
    }

    @Then("send sc")
    public void send_sc() {
        // This method is no longer needed here since sending email is handled in each scenario
    }

    private void sendScreenshotEmail(String recipientEmail, byte[] screenshotBytes) {
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
            message.setSubject("MY GOV CODE");

            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("Please find attached screenshot");

            MimeBodyPart attachmentBodyPart = new MimeBodyPart();
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
