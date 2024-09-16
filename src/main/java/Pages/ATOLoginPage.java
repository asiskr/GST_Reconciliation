package Pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.asis.util.BaseClass;
import com.asis.util.ExcelUtil;

import Driver_manager.DriverManager;

public class ATOLoginPage extends BaseClass{	
	
	private byte[] screenshotBytes;
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

	public byte[] clickOnLoginButton() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(loginButton));
		loginButton.click();
		Thread.sleep(3000);
		screenshotBytes = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
		return screenshotBytes;
	}
}
