package StepDefinition;

import org.openqa.selenium.WebDriver;

import com.asis.util.BaseClass;

import Driver_manager.DriverManager;
import Pages.ATOLoginBusinessPage;
import Pages.ATOLoginPage;
import io.cucumber.java.en.*;

public class ATOLoginStep {

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
    public void user_do_login_as_per_provided_in_excel() {
    	businessPage.clickOnLoginButton();
    }
    
    @When("click on login button")
    public void click_on_login_button() {
 
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
