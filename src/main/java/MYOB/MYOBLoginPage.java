package MYOB;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.asis.util.BaseClass;

import Driver_manager.DriverManager;

public class MYOBLoginPage extends BaseClass{

	@FindBy(xpath="//input[@id='username']")
	WebElement emailAddress;
	@FindBy(xpath= "//button[contains(text(),'Next')]")
	WebElement nextButton;
	@FindBy(xpath= "//input[@id='password']")
	WebElement password;
	@FindBy(xpath= "//button[contains(text(),'Sign in')]")
	WebElement signin;
	String userId = "bookkeeping@fortunaadvisors.com.au";
	String passwordValue = "ForB00k!23";

	public MYOBLoginPage(){	
		PageFactory.initElements(DriverManager.getDriver(), this);    
	}
	public void enterEmailAddress() {
		wait.until(ExpectedConditions.elementToBeClickable(emailAddress));
		System.out.println(emailAddress);
		emailAddress.sendKeys(userId);
	}
	public void clickOnNextButton() {
		wait.until(ExpectedConditions.elementToBeClickable(nextButton));
		nextButton.click();
	}
	public void enterPassword() {
		wait.until(ExpectedConditions.elementToBeClickable(password));
		password.sendKeys(passwordValue);
	}
	public void clickOnSigninButton() {
		wait.until(ExpectedConditions.elementToBeClickable(signin));
		signin.click();
	}
}
