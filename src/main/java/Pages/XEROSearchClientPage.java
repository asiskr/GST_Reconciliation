package Pages;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.asis.util.BaseClass;

import Driver_manager.DriverManager;



public class XEROSearchClientPage extends BaseClass{

	@FindBy(xpath = "//button[@data-automationid='xnav-appbutton']")
	WebElement search;
	@FindBy(xpath = "//button[contains(text(),'Change organisation')]")
	WebElement chnageOrganization;
	@FindBy(xpath = "//input[@title='Search organisations']")
	WebElement searchBox;
	@FindBy(xpath = "//a[@data-menu-item-type='ORG']")
	WebElement options;
	
	public XEROSearchClientPage() {
		PageFactory.initElements(DriverManager.getDriver(), this);
	}
	public void  getPageTitle() {
	}
	public void clickOnSearchButton() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(search));
		search.click();
	}
	public void clickOnchnageOrganizationButton() {
		wait.until(ExpectedConditions.elementToBeClickable(chnageOrganization));
		chnageOrganization.click();
	}
//	public void writeinchnageOrganizationBox() throws InterruptedException {
//		String client_name = ATO_CLIENT_NAME;
//		wait.until(ExpectedConditions.elementToBeClickable(searchBox));
//		Thread.sleep(1000);
//		searchBox.sendKeys(client_name);
//		Thread.sleep(5000);
//		if (options.getText().trim().toLowerCase().startsWith(ATO_CLIENT_NAME.toLowerCase())) {
//		    options.click();
//		}
//
//	}
	public void writeinchnageOrganizationBox() throws InterruptedException, TimeoutException {
//	    String client_name = XERO_CLIENT_NAME;

	    try {
	        // Wait for the search box to be clickable
	        wait.until(ExpectedConditions.elementToBeClickable(searchBox));
	        searchBox.sendKeys(XERO_Client_Name);
	        
	        // Optional sleep to allow page update (can adjust or remove based on need)
	        Thread.sleep(3000);
	        
	        System.out.println("Waiting for the options element to be clickable...");

	        // Wait explicitly for the options element to be clickable with a timeout
	        wait.until(ExpectedConditions.elementToBeClickable(options));

	        // Log the option's text to see what is being captured
	        System.out.println("Option found with text: " + options.getText());
	        
	        // Validate if the option matches the client name
	        System.out.println("Expected client name: " + XERO_Client_Name);
	        System.out.println("Actual option text: " + options.getText());

	        if (options.getText().trim().equalsIgnoreCase(XERO_Client_Name.trim())) {
	            options.click();
	            System.out.println("Client found and selected: " + XERO_Client_Name);
	        } else {
	            System.out.println("Client name does not match the option: " + options.getText());
	            // Stop the execution if the client name does not match
	            return;
	        }
	    } catch (NoSuchElementException e) {
	        System.out.println("Element not found: " + e.getMessage());
	    }

	}

	public void doSearchClientName() throws InterruptedException {
	}
}
