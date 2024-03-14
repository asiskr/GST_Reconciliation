package Pages;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.asis.util.BaseClass;
import Driver_manager.DriverManager;

public class XeroAgedPayableSummaryPage extends BaseClass{

	@FindBy(xpath = "//button[contains(text(),'Accounting')]")
	WebElement accountingButton;
	@FindBy(xpath = "//a[contains(text(),'Aged Payables Summary')]")
	WebElement payable;
	@FindBy(xpath = "//button[@id='report-settings-columns-button']")
	WebElement colmSelected;
	@FindBy(xpath = "//span[contains(text(),'Outstanding GST')]")
	WebElement Outstanding_GST;
	@FindBy(xpath = "//body/div[4]/div[1]/div[1]/div[4]/div[1]/div[1]/div[1]/div[1]/div[2]/button[1]/div[1]")
	WebElement endOfMonth;
	@FindBy(xpath = "//span[contains(text(),'End of last quarter')]")
	WebElement lastFinancialYear;
	@FindBy(xpath = "//button[contains(text(),'Update')]")
	WebElement Update;
	@FindBy(xpath = "//div[contains(text(),'Nothing to show here')]")
	WebElement exist;
	@FindBy(xpath = "//tr//descendant::div[text()='Total']/ancestor::tr/td[9]/span/div")
	WebElement GST2;
	public static double payableAmount = 0.0;
	public static double Total = 0.0;
	// Constructor
	public XeroAgedPayableSummaryPage() {	
		PageFactory.initElements(DriverManager.getDriver(), this); 
	}
	//list  of all the actions on page
	public static String getPageTitle() {
		return DriverManager.getDriver().getTitle();
	}

	public void clickAccountingButton() {
		accountingButton.click();
	}
	public void clickPayable() {
		payable.click();
	}
	public void clickColmSelected() {
		colmSelected.click();
	}
	public void clickOutstanding_GST() {
		Outstanding_GST.click();
	}
	public void clickEndOfMonth() {
		endOfMonth.click();
	}
	public void clickLastFinancialYear() {
		lastFinancialYear.click();
	}
	public void clickUpdate() {
		Update.click();
	}
	public void getAgedPayableValues() {
		double payableAmount = 0.0;
		if (exist.isDisplayed()) {
			payableAmount=0.0;
			HashMap<String, Double> hm3 = new HashMap<>();
			hm3.put("Less: GST on Creditors", payableAmount);
			LAST_TABLE_DATA.add(hm3);
		} else { 
			payableAmount=Double.parseDouble(GST2.getText().replaceAll(",", ""));
			HashMap<String, Double> hm3 = new HashMap<>();
			hm3.put("Less: GST on Creditors", payableAmount);
			LAST_TABLE_DATA.add(hm3);

		}
		HashMap<String, Double> hm4 = new HashMap<>();
		hm4.put("Total", (LAST_TABLE_DATA.get(0).get("June BAS")+ payableAmount+XeroAgedRecievableSummaryPage.RecievableAmount));

		LAST_TABLE_DATA.add(hm4);
		}
}
