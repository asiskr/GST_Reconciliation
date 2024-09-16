package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.asis.util.BaseClass;
import Driver_manager.DriverManager;

public class ATOLoginPage extends BaseClass{	
	
	@FindBy(xpath="//a[@id='btn-myGovID']")
	WebElement myGOV;
	@FindBy(xpath= "//input[@type='email']")
	WebElement emailAddress;
	@FindBy(xpath= "//button[@type='submit']")
	WebElement loginButton;

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
}
