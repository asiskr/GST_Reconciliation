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

public class XeroAgedPayableSummaryPage extends BaseClass {

	@FindBy(xpath = "//button[contains(text(),'Accounting')]")
	WebElement accountingButton;

	@FindBy(xpath = "//a[contains(text(),'Reports')]")
	WebElement reports;

	@FindBy(xpath = "//*[@class='report-row-tooltip']//descendant::span[contains(text(),'Aged Payables Summary')]")
	WebElement payable;

	@FindBy(xpath = "//button[@id='report-settings-columns-button']")
	WebElement colmSelected;

	@FindBy(xpath = "//span[contains(text(),'Outstanding GST')]")
	WebElement Outstanding_GST;

	@FindBy(xpath = "//input[@id='report-settings-custom-date-input-to']")
	WebElement date;

	@FindBy(xpath = "//button[contains(text(),'Update')]")
	WebElement Update;

	@FindBy(xpath = "//div[contains(text(),'Nothing to show here')]")
	boolean noShowDiv;

	@FindBy(xpath = "//tr//descendant::div[text()='Total']/ancestor::tr/td[7]/span/div")
	WebElement GST2;

	public static double payableAmount = 0.0;

	// Constructor
	public XeroAgedPayableSummaryPage() {    
		PageFactory.initElements(DriverManager.getDriver(), this); 
	}
	public void clickAccountingButton() {
		wait.until(ExpectedConditions.elementToBeClickable(accountingButton));
		accountingButton.click();
		wait.until(ExpectedConditions.elementToBeClickable(reports));
		reports.click();
	}
	public void clickPayable() {
		wait.until(ExpectedConditions.elementToBeClickable(payable));
		payable.click();
	}
	public void clickColmSelected() {
		wait.until(ExpectedConditions.elementToBeClickable(colmSelected));
		colmSelected.click();
	}

	public void clickOutstanding_GST() throws InterruptedException {
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(Outstanding_GST));
		((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].click();", Outstanding_GST);
	}
	public void clickEndOfMonth() throws InterruptedException {
		Thread.sleep(2000);
		// Implementation needed if required
	}
	public void clickLastFinancialYear() {
		wait.until(ExpectedConditions.elementToBeClickable(date));
		date.click();
		date.sendKeys(XERO_TO_DATE);
	}
	public void clickUpdate() throws InterruptedException {
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(Update));
		Update.click();
	}
	public boolean isElementPresent(By locator) {
		try {
			//			DriverManager.getDriver().findElement(locator);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	public void getAgedPayableValues() throws TimeoutException, InterruptedException {
		if (noShowDiv) {
			payableAmount=0.0;
			System.out.println(payableAmount);
			HashMap<String, Double> hm3 = new HashMap<>();
			hm3.put("Less: GST on Creditors", payableAmount);
			LAST_TABLE_DATA.add(hm3);
		}
		else {
			payableAmount= Double.parseDouble((GST2).getText().replaceAll(",", ""));

			HashMap<String, Double> hm3 = new HashMap<>();
			hm3.put("Less: GST on Creditors", payableAmount);
			LAST_TABLE_DATA.add(hm3);	
		}		
		 System.out.println("payableAmount : " +payableAmount);
		 HashMap<String, Double> hm4 = new HashMap<>();
			double juneBAS = LAST_TABLE_DATA.get(0).get("June BAS") != null ? LAST_TABLE_DATA.get(0).get("June BAS") : 0.0;
			double total = juneBAS + payableAmount + XeroAgedRecievableSummaryPage.RecievableAmount;
			hm4.put("Total", total);
			LAST_TABLE_DATA.add(hm4);
			System.out.println(hm4);
			System.out.println("Total: " + LAST_TABLE_DATA.get(3));
	}
		/*
		boolean exists = false;
		try {
			exists = noShowDiv.isDisplayed();
		} catch (NoSuchElementException e) {
			exists = false; // Element is not present, hence set exists to false
		}

		double payableAmount = 0.0;
		if (exists) {
			System.out.println("No data to show: " + payableAmount);
		} else { 
			wait.until(ExpectedConditions.visibilityOf(GST2));
			String gstText = GST2.getText().replaceAll(",", "");
			try {
				payableAmount = Double.parseDouble(gstText);
			} catch (NumberFormatException e) {
				System.err.println("Error parsing GST amount: " + gstText);
			}
		}
		HashMap<String, Double> hm3 = new HashMap<>();
		hm3.put("Less: GST on Creditors", payableAmount);
		LAST_TABLE_DATA.add(hm3);

		HashMap<String, Double> hm4 = new HashMap<>();
		double juneBAS = LAST_TABLE_DATA.get(0).get("June BAS") != null ? LAST_TABLE_DATA.get(0).get("June BAS") : 0.0;
		double total = juneBAS + payableAmount + XeroAgedRecievableSummaryPage.RecievableAmount;
		hm4.put("Total", total);
		LAST_TABLE_DATA.add(hm4);
		System.out.println("Total: " + LAST_TABLE_DATA.get(1));
		System.out.println("payableAmount: " + payableAmount);

	}
		 */
}
