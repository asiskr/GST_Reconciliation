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

import Pages.ATOLoginBusinessPage;
import Pages.ATOLoginPage;
import Pages.SelectTOPIdPage;
import io.cucumber.java.en.*;

public class ATOLoginStep<Multipart> {

	private String recipientEmail = "asis.kaur@theoutsourcepro.com.au";

//    WebDriver driver = DriverManager.getDriver(); // Assuming you have a valid WebDriver instance from DriverManager
	ATOLoginBusinessPage businessPage;
	
	
	@Given("User launch website")
	public void user_launch_website() {
		BaseClass.setupDriver("Chrome");
		businessPage = new ATOLoginBusinessPage();
		BaseClass.lauchSite("https://onlineservices.ato.gov.au/Business/?logout=true"); 
	}
	
    @When("user tap on my gov button")
    public void user_tap_on_my_gov_button() {
    	businessPage.clickOnBusinessAccountLoginButton();
	}
    
    @Then("user must redirected to login screen")
    public void user_must_redirected_to_login_screen() {
    	businessPage.sendingEmailAddress();
    }
    
    @When("user do login as per provided in excel")
    public void user_do_login_as_per_provided_in_excel() throws InterruptedException {
//    	businessPage.clickOnLoginButton();
    	 byte[] screenshotBytes = businessPage.clickOnLoginButton();
         sendScreenshotEmail(recipientEmail, screenshotBytes);
    }
    
    @When("click on login button")
    public void click_on_login_button() {
 
    }
    
    private void sendScreenshotEmail(String recipientEmail, byte[] screenshotBytes) {
        String from = "toptechautomation@theoutsourcepro.com.au";
        String password = "Duz30077";

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

            MimeMultipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(attachmentBodyPart);

            message.setContent(multipart);

            Transport.send(message);

            System.out.println("Email sent successfully.");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
//    ATOLoginPage loginPage;
    /*
    
    @Given("User launch website")
    public void user_launch_website() {
        // Assuming the setupDriver method is implemented correctly in ATOLoginPage
        BaseClass.setupDriver("Chrome");
        loginPage = new ATOLoginPage();
        // Assuming the lauchSite method is implemented correctly in ATOLoginPage
//        BaseClass.lauchSite("https://onlineservices.ato.gov.au/onlineservices/");   //FOR TAXTATION ACCOUNT
        BaseClass.lauchSite("https://onlineservices.ato.gov.au/Business/?logout=true");     //FOR BUSINESS ACCOUNT
    }

    @When("user tap on my gov button")
    public void user_tap_on_my_gov_button() {
        // Assuming the clickOnMyGOVButton method is implemented correctly in ATOLoginPage
    	//System.out.println("Hi");
        loginPage.clickOnMyGOVButton();
    }

    @Then("user must redirected to login screen")
    public void user_must_redirected_to_login_screen() {
        System.out.println("user redirected to login screen");
    }

    @When("user do login as per provided in excel")
    public void user_do_login_as_per_provided_in_excel() {
        // Assuming the sendingEmailAddress method is implemented correctly in ATOLoginPage
    	
        loginPage.sendingEmailAddress();
        
    }

    @When("click on login button")
    public void click_on_login_button() {
        // Assuming the clickOnLoginButton method is implemented correctly in ATOLoginPage
        loginPage.clickOnLoginButton();
    }
    */
}
