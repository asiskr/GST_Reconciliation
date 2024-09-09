package Pages;

import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;

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
	boolean noShowDiv;

	@FindBy(xpath = "//thead//th[.//span[contains(text(), 'Current')]]")
	WebElement current;

	@FindBy(xpath = "//tr//descendant::div[text()='Total']/ancestor::tr/td[8]/span/div")
	WebElement GST4;

	@FindBy(xpath = "//tr//descendant::div[text()='Total']/ancestor::tr/td[9]/span/div")
	WebElement GST1;

	public static double RecievableAmount = 0.0;

	// Constructor
	public XeroAgedRecievableSummaryPage() {
		PageFactory.initElements(DriverManager.getDriver(), this);
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

	public void clickOnLastFinancialYear() {
		wait.until(ExpectedConditions.elementToBeClickable(date)).click();
		date.sendKeys(XERO_TO_DATE);
	}

	public void clickOnUpdate() {
		wait.until(ExpectedConditions.elementToBeClickable(update)).click();
	}
	public void getAgedRecievableValues() throws TimeoutException, InterruptedException {
		HashMap<String, Double> hm2 = new HashMap<>();
		  try {
//		        wait.until(ExpectedConditions.visibilityOf(GST2));
		        String text = GST1.getText().replaceAll(",", "").trim();
		        RecievableAmount = Double.parseDouble(text); // Use Double to handle decimal values
//		        System.out.println("RecievableAmount is : " +RecievableAmount);
		    } catch (Exception e) {
		    	RecievableAmount = 0.0; // Default to 0.0 in case of any exception
//		        System.out.println(e);
		    }
		    
		/*
		HashMap<String, Double> hm2 = new HashMap<>();

		if (noShowDiv) {
			RecievableAmount = 0.0;
			System.out.println(RecievableAmount);
			hm2.put("Add: GST on Debtors", RecievableAmount);
			LAST_TABLE_DATA.add(hm2);
			System.out.println("Add: GST on Debtors");
		} else {
			// Fetch the text from the web element
			String text = GST1.getText().replaceAll(",", "").trim();

			// Initialize RecievableAmount
			double receivableAmount;

			// Check if the text is "-" and set RecievableAmount to 0.0 if true
			if (text.equals("-")) {
				receivableAmount = 0.0;
			} else {
				try {
					// Attempt to parse the value if it's not "-"
					receivableAmount = Double.parseDouble(text);
				} catch (NumberFormatException e) {
					// Handle the exception if parsing fails
					receivableAmount = 0.0;
					System.err.println("Error parsing number: " + e.getMessage());
				}
			}

			// Assign the amount to RecievableAmount
			RecievableAmount = receivableAmount;
			System.out.println(RecievableAmount);
*/
			// Put the value into the HashMap and add it to the list
			hm2.put("Add: GST on Debtors", RecievableAmount);
			LAST_TABLE_DATA.add(hm2);
	}
}
