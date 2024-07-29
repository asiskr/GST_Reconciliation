package MYOB;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.asis.util.BaseClass;

import Driver_manager.DriverManager;
import XERO.XeroAgedRecievableSummaryPage;

public class MYOBAgedPayableSummaryPage extends BaseClass {

	@FindBy(xpath = "//div[contains(text(),'Payables reconciliation with tax')]")
	WebElement payable;
	@FindBy(xpath = "//input[@name='AS_AT_DATE']")
	WebElement toDate;
	@FindBy(xpath = "//div[@role='row' and .//span[text()='Total']]//div[3]//span")
	public WebElement total;
	@FindBy(xpath = "//div[contains(text(),'Reporting')]")
	WebElement reporting;
	@FindBy(xpath = "//span[contains(text(),'Reports')]")
	WebElement reports;

	public static double PayableAmount = 0.0;
	public static double total1 = 0.0;



	public MYOBAgedPayableSummaryPage() {
		PageFactory.initElements(DriverManager.getDriver(), this);
	}

	// List of all the actions on page
	public void clickPayable() {
		wait.until(ExpectedConditions.visibilityOf(payable));
		payable.click();
	}

	public void toDate() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(toDate));
		String StringToDate = XERO_TO_DATE;
		toDate.sendKeys(Keys.CONTROL + "a");
		toDate.sendKeys(Keys.DELETE);

		toDate.sendKeys(StringToDate);
		Thread.sleep(3000);
	}

	public void getPayableTotal() {
		wait.until(ExpectedConditions.visibilityOf(total));
		String totalPayableStr = total.getText().replaceAll(",", "");
		try {
			PayableAmount = Double.parseDouble(totalPayableStr);
		} catch (NumberFormatException e) {
			System.err.println("Error parsing payable amount: " + totalPayableStr);
			return;
		}

		// Example of adding "Less: GST on Creditors"
		HashMap<String, Double> hm3 = new HashMap<>();
		hm3.put("Less: GST on Creditors", PayableAmount);
		LAST_TABLE_DATA.add(hm3);
//		LAST_TABLE_DATA.set(2,hm3);
		

		// Example of calculating and adding "Total"
//		HashMap<String, Double> hm4 = new HashMap<>();
//		double juneBAS = LAST_TABLE_DATA.get(0).getOrDefault("June BAS", 0.0);
//		double total = juneBAS + MYOBAgedPayableSummaryPage.PayableAmount + MYOBAgedRecieveablePage.RecievableAmounts;
//		hm4.put("Total", total);
//		LAST_TABLE_DATA.add(hm4);
//		LAST_TABLE_DATA.set(3,hm4);

		// Print all elements in LAST_TABLE_DATA with their indices
		System.out.println("Contents of LAST_TABLE_DATA:");
		for (int i = 0; i < LAST_TABLE_DATA.size(); i++) {
			HashMap<String, Double> map = LAST_TABLE_DATA.get(i);
			System.out.println("Index " + i + ": " + map);

		}
	}




	/*
        // Ensure LAST_TABLE_DATA is large enough
        while (LAST_TABLE_DATA.size() < 5) {
            LAST_TABLE_DATA.add(new HashMap<>());
        }

        HashMap<String, Double> hm3 = new HashMap<>();
        hm3.put("Less: GST on Creditors", PayableAmount);
        LAST_TABLE_DATA.set(2, hm3);
        LAST_TABLE_DATA.add(hm3);

        HashMap<String, Double> hm4 = new HashMap<>();
		double juneBAS = LAST_TABLE_DATA.get(0).get("June BAS") != null ? LAST_TABLE_DATA.get(0).get("June BAS") : 0.0;
		double total = juneBAS + PayableAmount + MYOBAgedRecieveablePage.RecievableAmounts;
		hm4.put("Total", total);
		LAST_TABLE_DATA.add(hm4);
        LAST_TABLE_DATA.set(3, hm4);
		System.out.println("Total: " + LAST_TABLE_DATA.get(3));


        // Debugging output
        System.out.println("Contents of LAST_TABLE_DATA after extraction:");
        for (int i = 0; i < LAST_TABLE_DATA.size(); i++) {
            System.out.println("Index " + i + ": " + LAST_TABLE_DATA.get(i));
        }
	 */

	public void clickReportingButton() {
		wait.until(ExpectedConditions.elementToBeClickable(reporting));
		reporting.click();
	}

	public void clickReportsButton() {
		wait.until(ExpectedConditions.elementToBeClickable(reports));
		reports.click();
	}
}
