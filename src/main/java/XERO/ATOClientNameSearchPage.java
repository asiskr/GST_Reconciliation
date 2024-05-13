package XERO;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.asis.util.BaseClass;
import com.asis.util.ExcelUtil;

import Driver_manager.DriverManager;

public class ATOClientNameSearchPage extends BaseClass{

	//list of webelements
	@FindBy(xpath = "//input[@type='search']")
	WebElement clientNameSearch;

	@FindBy(xpath = "//ul[contains(@class, 'error')]/li[contains(text(), 'No matches found')]")
	WebElement noMatches;

	//constructor
	public ATOClientNameSearchPage(){
		PageFactory.initElements(DriverManager.getDriver(), this);       
	}

	//list  of all the actions on page
	public void getPageTitle() {
	}

	public void enterClientName() throws InterruptedException {		
		String client_name = ATO_CLIENT_NAME;

		wait.until(ExpectedConditions.elementToBeClickable(clientNameSearch));
		Thread.sleep(1000);
		clientNameSearch.sendKeys(client_name);
	}

	public void doSearchClientName() throws InterruptedException {
	    Thread.sleep(3000);
	    clientNameSearch.sendKeys(Keys.ENTER);
	    /*
	    if(noMatches.isDisplayed()) {
	        DriverManager.getDriver().quit();
	    } 
	    */
	        clientNameSearch.sendKeys(Keys.ENTER);    
	}

}
