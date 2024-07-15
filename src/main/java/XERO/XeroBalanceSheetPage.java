package XERO;

import java.util.HashMap;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.asis.util.BaseClass;
import Driver_manager.DriverManager;

public class XeroBalanceSheetPage extends BaseClass{


	@FindBy(xpath = "//button[contains(text(),'Accounting')]")
	WebElement accountingButton;
	@FindBy(xpath = "//a[contains(text(),'Reports')]")
	WebElement reports;
	@FindBy(xpath = "//*[@class='report-row-tooltip']//descendant::span[contains(text(),'Balance Sheet')]")
	WebElement balanceSheet;
	@FindBy(xpath = "//tr//descendant::div[contains(text(),'GST')]/ancestor::tr/td[2]//a")
	WebElement GST;

	public static double GST_asperBalanceSheet = 0.0;
	// Constructor
	public XeroBalanceSheetPage() {	
		PageFactory.initElements(DriverManager.getDriver(), this); 
	}
	//list  of all the actions on page
	public static void getPageTitle() {
	}

	public void clickOnAccountingButton() {
		wait.until(ExpectedConditions.elementToBeClickable(accountingButton));
		accountingButton.click();
		wait.until(ExpectedConditions.elementToBeClickable(reports));
		reports.click();
	}
	public void clickOnBalanceSheetButton() {
		wait.until(ExpectedConditions.elementToBeClickable(balanceSheet));
		balanceSheet.click();
	}
	public void fetchingGST() {
		GST_asperBalanceSheet = Double.parseDouble(GST.getText().replaceAll(",", ""));

		HashMap<String, Double> hm5 = new HashMap<>();
		hm5.put("GST as per Balance sheet", GST_asperBalanceSheet);
		LAST_TABLE_DATA.add(hm5);
		int index = LAST_TABLE_DATA.indexOf(hm5);
		System.out.println("Index of hm5: " + index);

		HashMap<String, Double> hm6 = new HashMap<>();
		hm6.put("Total - GST as per balance sheet",  (LAST_TABLE_DATA.get(2).get("Total") - LAST_TABLE_DATA.get(3).get("GST as per Balance sheet")));
		LAST_TABLE_DATA.add(hm6);

		for (int i = 0; i < LAST_TABLE_DATA.size(); i++) {
			HashMap<String, Double> map = LAST_TABLE_DATA.get(i);
			System.out.println("Index " + i + ": " + map);
		}
	}
}
