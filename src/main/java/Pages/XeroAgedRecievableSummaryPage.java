package Pages;

import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.asis.util.BaseClass;
import Driver_manager.DriverManager;

public class XeroAgedRecievableSummaryPage extends BaseClass {

	@FindBy(xpath = "//button[contains(text(),'Accounting')]")
	WebElement accountingButton;

	@FindBy(xpath = "//a[contains(text(),'Reports')]")
	WebElement reports;

	@FindBy(xpath = "//*[@class='report-row-tooltip']//descendant::span[contains(text(),'Aged Receivables Summary')]")
	WebElement recievable;

	@FindBy(xpath = "//button[@id='report-settings-columns-button']")
	WebElement colSelected;

	@FindBy(xpath = "//span[contains(text(),'Outstanding GST')]")
	WebElement outstanding_gst_rec;

	@FindBy(xpath = "//input[@id='report-settings-custom-date-input-to']")
	WebElement date;

	@FindBy(xpath = "//*[@id=\"report-settings\"]/div/div/div[7]/button")
	WebElement update;

	@FindBy(xpath = "//div[contains(text(),'Nothing to show here')]")
	WebElement noShowDiv;

	@FindBy(xpath = "//thead//th[.//span[contains(text(), 'Current')]]")
	WebElement current;

	@FindBy(xpath = "//tr//descendant::div[text()='Total']/ancestor::tr/td[8]/span/div")
	WebElement GST4;

	@FindBy(xpath = "//tr//descendant::div[text()='Total']/ancestor::tr/td[7]/span/div")
	WebElement GST1;

	public static double RecievableAmount = 0.0;

	// Constructor
	public XeroAgedRecievableSummaryPage() {
		PageFactory.initElements(DriverManager.getDriver(), this);
	}

	// List of all the actions on page
	public static void getPageTitle() {
		// Implement the title retrieval logic if necessary
	}

	public void clickOnAccountingButton() {
		wait.until(ExpectedConditions.elementToBeClickable(accountingButton)).click();
		wait.until(ExpectedConditions.elementToBeClickable(reports)).click();
	}

	public void clickOnRecievable() {
		wait.until(ExpectedConditions.elementToBeClickable(recievable)).click();
	}

	public void clickOnColmSelected() {
		wait.until(ExpectedConditions.elementToBeClickable(colSelected)).click();
	}

	public void clickOnOutstanding_GST() {
		wait.until(ExpectedConditions.elementToBeClickable(outstanding_gst_rec));
		((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].click();", outstanding_gst_rec);
	}

	public void clickOnEndOfMonth() {
		// Implement end of month logic if necessary
	}

	public void clickOnLastFinancialYear() {
		wait.until(ExpectedConditions.elementToBeClickable(date)).click();
		date.sendKeys(XERO_TO_DATE);
	}

	public void clickOnUpdate() {
		wait.until(ExpectedConditions.elementToBeClickable(update)).click();
	}

	public boolean isElementPresent(By locator) {
		try {
			//            DriverManager.getDriver().findElement(locator);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public void getAgedRecievableValues() throws TimeoutException, InterruptedException {
		Thread.sleep(1000);
		HashMap<String, Double> hm2 = new HashMap<>();
		Thread.sleep(2000);
		if (isElementPresent(By.xpath("//div[contains(text(),'Nothing to show here')]"))) {
			RecievableAmount = 0.0;
			hm2.put("Add: GST on Debtors", RecievableAmount);
		} else if (isElementPresent(By.xpath("//thead//th[.//span[contains(text(), 'Current')]]"))) {
			RecievableAmount = Double.parseDouble(GST4.getText().replaceAll(",", ""));
			hm2.put("Add: GST on Debtors", RecievableAmount);
		} else {
			RecievableAmount = Double.parseDouble(GST1.getText().replaceAll(",", ""));
			hm2.put("Add: GST on Debtors", RecievableAmount);
		}

		LAST_TABLE_DATA.add(hm2);
		System.out.println("RecievableAmount : " + RecievableAmount);
	}
}

/*
	    try {
	        // Wait until the GST1 element is visible, or noShowDiv is present
	    	Thread.sleep(2000);
	        if (isElementPresent(By.xpath("//div[contains(text(),'Nothing to show here')]"))) {
	            RecievableAmount = 0.0;
	            System.out.println(RecievableAmount);
	        } else {
	            wait.until(ExpectedConditions.visibilityOf(GST1));
	            RecievableAmount = Double.parseDouble(GST1.getText().replaceAll(",", ""));
	        }
	        System.out.println("RecievableAmount : " +RecievableAmount);
	        HashMap<String, Double> hm2 = new HashMap<>();
	        hm2.put("Add: GST on Debtors", RecievableAmount);
	        LAST_TABLE_DATA.add(hm2);
	    } catch (NoSuchElementException e) {
	        System.err.println("Element not found: " + e.getMessage());
	        RecievableAmount = 0.0;
	    } catch (NumberFormatException e) {
	        System.err.println("Error parsing GST amount: " + e.getMessage());
	        RecievableAmount = 0.0;
	    }
	}
 */

/*
		boolean exists = false;
		try {
			exists = noShowDiv.isDisplayed();
		} catch (NoSuchElementException e) {
			exists = false; // Element is not present, hence set exists to false
		}

		double RecievableAmount = 0.0;
		if (exists) {
			System.out.println("No data to show: " + RecievableAmount);
		} else { 


			wait.until(ExpectedConditions.visibilityOf(GST1));
			String gstText2 = GST1.getText().replaceAll(",", "");
			try {
				RecievableAmount = Double.parseDouble(gstText2);
			} catch (NumberFormatException e) {
				System.err.println("Error parsing GST amount: " + gstText2);
			}
		}
		HashMap<String, Double> hm2 = new HashMap<>();
		hm2.put("Add: GST on Debtors", RecievableAmount);
		LAST_TABLE_DATA.add(hm2);
		System.out.println("Add: GST on Debtors "+LAST_TABLE_DATA.get(0));	
	}
 */