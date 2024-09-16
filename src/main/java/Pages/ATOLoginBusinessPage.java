package Pages;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.asis.util.BaseClass;

import Driver_manager.DriverManager;

public class ATOLoginBusinessPage extends BaseClass{
	private byte[] screenshotBytes;

	@FindBy(xpath="//span[contains(text(),'Login')]")
	WebElement Login;
	@FindBy(xpath= "//input[@id='emailInputText']")
	WebElement emailAddress;
	@FindBy(xpath= "//button[@id='getCodeButton']")
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

	public byte[] clickOnLoginButton() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(loginButton));
		loginButton.click();
		Thread.sleep(3000);
		screenshotBytes = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
		return screenshotBytes;
	}
}
