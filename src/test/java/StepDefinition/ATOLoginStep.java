package StepDefinition;

import org.openqa.selenium.WebDriver;

import com.asis.util.BaseClass;

import Driver_manager.DriverManager;
import XERO.ATOClientNameSearchPage;
import XERO.ATOGoToQuarterName;
import XERO.ATOLoginBusinessPage;
import XERO.ATOLoginPage;
import XERO.SelectTOPIdPage;
import io.cucumber.java.en.*;

public class ATOLoginStep extends BaseClass {

	//    WebDriver driver = DriverManager.getDriver(); // Assuming you have a valid WebDriver instance from DriverManager
	public SelectTOPIdPage selectTOP= new SelectTOPIdPage();
	public ATOClientNameSearchPage clientSearchPage  = new ATOClientNameSearchPage();
	public ATOGoToQuarterName quaterNamePage;
	ATOLoginBusinessPage businessPage;
	ATOLoginPage loginPage;
	String taxtation = TAXATION;

	@Given("User launch website")
	public void user_launch_website() throws InterruptedException {
		BaseClass.setupDriver("Chrome");
		businessPage = new ATOLoginBusinessPage();
		loginPage = new ATOLoginPage();
		if (taxtation.equalsIgnoreCase("taxation")) {
			BaseClass.lauchSite("https://onlineservices.ato.gov.au/onlineservices/");  
			loginPage.clickOnMyGOVButton();
			loginPage.sendingEmailAddress();
			loginPage.clickOnLoginButton();
		}
		else {
			BaseClass.lauchSite("https://onlineservices.ato.gov.au/Business/?logout=true"); 
			businessPage.clickOnBusinessAccountLoginButton();
			businessPage.sendingEmailAddress();
			businessPage.clickOnLoginButton();
		}
	}
}
