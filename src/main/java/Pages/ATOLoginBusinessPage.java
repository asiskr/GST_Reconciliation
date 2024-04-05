package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.asis.util.BaseClass;

import Driver_manager.DriverManager;

public class ATOLoginBusinessPage extends BaseClass{

	@FindBy(xpath="//span[contains(text(),'Login')]")
	WebElement Login;
	@FindBy(xpath= "//input[@placeholder='myGovID email']")
	WebElement emailAddress;
	@FindBy(xpath= "//button[@title='Submit']")
	WebElement loginButton;
	
	public ATOLoginBusinessPage(){	
		PageFactory.initElements(DriverManager.getDriver(), this);       
	}

	public void clickOnBusinessAccountLoginButton() {
		Login.click();
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
}
