package MYOB;

import java.util.HashMap;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.asis.util.BaseClass;

import Driver_manager.DriverManager;

public class MYOBAgedRecieveablePage extends BaseClass{

	@FindBy(xpath="//div[contains(text(),'Receivables reconciliation with tax')]")
	WebElement receivable;
	@FindBy(xpath="//input[@name='AS_AT_DATE']")
	WebElement toDate;
	@FindBy(xpath="//div[@role='row' and .//span[text()='Total']]//div[3]//span")
	WebElement total;
	@FindBy(xpath="//div[contains(text(),'Reporting')]")
	WebElement reporting;
	@FindBy(xpath="//span[contains(text(),'Reports')]")
	WebElement reports;
	public static double RecievableAmounts = 0.0;

	public MYOBAgedRecieveablePage(){	
		PageFactory.initElements(DriverManager.getDriver(), this);    
	}
	public void clickReceivableButton() {
		wait.until(ExpectedConditions.elementToBeClickable(receivable));
		receivable.click();
	}
	
	public void passToDate() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(toDate));
		String StringToDate =XERO_TO_DATE;
		toDate.sendKeys(Keys.CONTROL + "a");
		toDate.sendKeys(Keys.DELETE);

		toDate.sendKeys(StringToDate);
		Thread.sleep(3000);
	}
	public void receivableAmount() {
		wait.until(ExpectedConditions.visibilityOf(total));
		String totalAmount= total.getText().replaceAll(",", "");
		try {
			RecievableAmounts = Double.parseDouble(totalAmount);
		} catch (NumberFormatException e) {
			System.err.println("Error parsing GST amount: " + totalAmount);
		}
		HashMap<String, Double> hm2 = new HashMap<>();
		hm2.put("Add: GST on Debtors", RecievableAmounts);
		LAST_TABLE_DATA.add(hm2);
		System.out.println("Add: GST on Debtors "+LAST_TABLE_DATA.get(0));	
	}

	public void clickReportingButton() {
		wait.until(ExpectedConditions.elementToBeClickable(reporting));
		reporting.click();
	}
	public void clickReportsButton() {
		wait.until(ExpectedConditions.elementToBeClickable(reports));
		reports.click();
	}
}
