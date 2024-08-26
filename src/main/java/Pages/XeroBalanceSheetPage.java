package Pages;

import java.util.HashMap;
import java.util.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.asis.util.BaseClass;
import Driver_manager.DriverManager;
import org.openqa.selenium.TimeoutException;

public class XeroBalanceSheetPage extends BaseClass {

	@FindBy(xpath = "//button[contains(text(),'Accounting')]")
	WebElement accountingButton;

	@FindBy(xpath = "//a[contains(text(),'Reports')]")
	WebElement reports;

	@FindBy(xpath = "//*[@class='report-row-tooltip']//descendant::span[contains(text(),'Balance Sheet')]")
	WebElement balanceSheet;

	@FindBy(xpath = "//input[@id='report-settings-custom-date-input-to']")
	WebElement date;

	@FindBy(xpath = "//button[contains(text(),'Update')]")
	WebElement update;

	@FindBy(xpath = "//tr//descendant::div[contains(text(),'GST')]/ancestor::tr/td[2]//a")
	WebElement GST;

	@FindBy(xpath = "//tr//descendant::div[contains(text(),'GST Input Tax Adj')]/ancestor::tr/td[2]//a")
	WebElement GSTInputTax;

	@FindBy(xpath = "//tr//descendant::div[contains(text(),'GST Payable Adj')]/ancestor::tr/td[2]//a")
	WebElement GSTPayable;

	public static double GST_asperBalanceSheet = 0.0;
	public static double GSTNormal_asperBalanceSheet = 0.0;
	public static double GSTInputTax_asperBalanceSheet = 0.0;
	public static double GSTPayable_asperBalanceSheet = 0.0;

	// Constructor
	public XeroBalanceSheetPage() {
		PageFactory.initElements(DriverManager.getDriver(), this);
	}

	// Click on Accounting and navigate to Reports
	public void clickOnAccountingButton() {
		wait.until(ExpectedConditions.elementToBeClickable(accountingButton));
		accountingButton.click();
		wait.until(ExpectedConditions.elementToBeClickable(reports));
		reports.click();
	}

	// Click on Balance Sheet and set the date
	public void clickOnBalanceSheetButton() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(balanceSheet));
		balanceSheet.click();
		Thread.sleep(3000); // Increase sleep time if necessary
		wait.until(ExpectedConditions.elementToBeClickable(date));
		date.click();
		date.sendKeys(XERO_TO_DATE);
		Thread.sleep(3000); // Increase sleep time if necessary
		wait.until(ExpectedConditions.elementToBeClickable(update));
		update.click();
		Thread.sleep(5000); // Increase sleep time if necessary to allow page to load completely
	}

	public void fetchingGST() {
		GSTNormal_asperBalanceSheet = getElementValue(GST, "GST");
		GSTInputTax_asperBalanceSheet = getElementValue(GSTInputTax, "GST Input Tax Adj");
		GSTPayable_asperBalanceSheet = getElementValue(GSTPayable, "GST Payable Adj");

		GST_asperBalanceSheet = GSTNormal_asperBalanceSheet + GSTInputTax_asperBalanceSheet + GSTPayable_asperBalanceSheet;
		        System.out.println("GST_asperBalanceSheet: " + GST_asperBalanceSheet);

		HashMap<String, Double> hm5 = new HashMap<>();
		hm5.put("GST as per Balance sheet", GST_asperBalanceSheet);
		LAST_TABLE_DATA.add(hm5);

		
        HashMap<String, Double> hm6 = new HashMap<>();
		hm6.put("Total - GST as per balance sheet", (LAST_TABLE_DATA.get(3).get("Total") - LAST_TABLE_DATA.get(4).get("GST as per Balance sheet")));
		LAST_TABLE_DATA.add(hm6);
		 
	}

	private double getElementValue(WebElement element, String name) {
		try {
			if (isElementPresent(element)) {
				String gstText = element.getText().replaceAll(",", "").replaceAll("[()]", "");
				//                System.out.println(name + " value: " + gstText); // Log value for debugging
				return parseFinancialValue(gstText);
			} else {
				//                System.out.println(name + " not found, setting value to 0.0");
				return 0.0;
			}
		} catch (NoSuchElementException e) {
			//            System.out.println(name + " not found, setting value to 0.0");
			return 0.0;
		} catch (Exception e) {
			//            System.err.println("Error retrieving " + name + " value: " + e.getMessage());
			return 0.0;
		}
	}

	private boolean isElementPresent(WebElement element) {
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			return true;
		} catch (NoSuchElementException | TimeoutException e) {
			return false;
		}
	}

	private double parseFinancialValue(String value) {
		value = value.replaceAll("[,()]", "");
		try {
			return Double.parseDouble(value);
		} catch (NumberFormatException e) {
			System.err.println("Error parsing financial value: " + value);
			return 0.0;
		}
	}
}
