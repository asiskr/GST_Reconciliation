package Pages;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.asis.util.BaseClass;
import com.asis.util.ExcelUtil;

import Driver_manager.DriverManager;

public class ATOLoginPage extends BaseClass{	

	@FindBy(xpath="//a[@id='btn-myGovID']")
	WebElement myGOV;
	@FindBy(xpath= "//input[@placeholder='myGovID email']")
	WebElement emailAddress;
	@FindBy(xpath= "//button[@title='Submit']")
	WebElement loginButton;
	@FindBy(xpath= "//span[@class='sentcode']")
	WebElement code;

	public ATOLoginPage(){	
		PageFactory.initElements(DriverManager.getDriver(), this);       
	}

	public void clickOnMyGOVButton() {
		myGOV.click();
	}

	public void sendingEmailAddress() {
		String user_id=ATO_USER_NAME;
		wait.until(ExpectedConditions.elementToBeClickable(emailAddress));
		emailAddress.sendKeys(user_id);
	}

	public void clickOnLoginButton() {
		wait.until(ExpectedConditions.elementToBeClickable(loginButton));
		loginButton.click();
	}
	public void sendCodeEmail(String recipientEmail) {
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
            message.setSubject("Your authentication code");
            message.setText("Your authentication code is: " + getCode());
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private String getCode() {
        wait.until(ExpectedConditions.visibilityOf(code));
        return code.getText();
    }
}