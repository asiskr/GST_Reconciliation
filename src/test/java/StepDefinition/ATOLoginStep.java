package StepDefinition;

import org.openqa.selenium.WebDriver;

import com.asis.util.BaseClass;

import Driver_manager.DriverManager;
import Pages.ATOClientNameSearchPage;
import Pages.ATOGoToQuarterName;
import Pages.ATOLoginBusinessPage;
import Pages.ATOLoginPage;
import Pages.SelectTOPIdPage;
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
		String recipientEmail = BaseClass.SENDER_TO ;
		if (taxtation.equalsIgnoreCase("taxation")) {
			BaseClass.lauchSite("https://onlineservices.ato.gov.au/onlineservices/");  
			loginPage.clickOnMyGOVButton();
			loginPage.sendingEmailAddress();
			loginPage.clickOnLoginButton();
			loginPage.sendCodeEmail(recipientEmail);
		}
		else {
			BaseClass.lauchSite("https://onlineservices.ato.gov.au/Business/?logout=true"); 
			businessPage.clickOnBusinessAccountLoginButton();
			businessPage.sendingEmailAddress();
			businessPage.clickOnLoginButton();
			loginPage.sendCodeEmail(recipientEmail);
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
