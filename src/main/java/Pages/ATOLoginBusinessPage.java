
package Pages;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.asis.util.BaseClass;
import Driver_manager.DriverManager;

public class ATOLoginBusinessPage extends BaseClass {

    private byte[] screenshotBytes;

    @FindBy(xpath = "//span[contains(text(),'Login')]")
    private WebElement loginButton;

    @FindBy(xpath = "//input[@type='email']")
    private WebElement emailAddress;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitButton;

    public ATOLoginBusinessPage() {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    public void clickOnBusinessAccountLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    public void sendingEmailAddress() {
		String user_id=ATO_USER_NAME;
		wait.until(ExpectedConditions.elementToBeClickable(emailAddress));
		emailAddress.sendKeys(user_id);
	}

    public byte[] clickOnLoginButton() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        Thread.sleep(1000);
        submitButton.click();
        Thread.sleep(1000);
        screenshotBytes = ((TakesScreenshot)DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
        return screenshotBytes;
    }
}
