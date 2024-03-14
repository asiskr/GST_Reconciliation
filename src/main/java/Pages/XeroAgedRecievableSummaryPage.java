package Pages;

import java.util.HashMap;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.asis.util.BaseClass;
import Driver_manager.DriverManager;

public class XeroAgedRecievableSummaryPage extends BaseClass{


	@FindBy(xpath = "//button[contains(text(),'Accounting')]")
	WebElement accountingButton;
	@FindBy(xpath = "//a[contains(text(),'Aged Receivables Summary')]")
	WebElement recievable;
	@FindBy(xpath = "//button[@id='report-settings-columns-button']")
	WebElement colSelected;
	@FindBy(xpath = "//label[@data-automationid='column-selection-taxamountdue--body--checkbox']")
	WebElement outstanding_gst_rec;
	@FindBy(xpath = "//body/div[4]/div[1]/div[1]/div[4]/div[1]/div[1]/div[1]/div[1]/div[2]/button[1]/div[1]")
	WebElement end_of_month;
	@FindBy(xpath = "//span[contains(text(),'End of last financial year')]")
	WebElement last_financial_year;
	@FindBy(xpath = "//*[@id=\"report-settings\"]/div/div/div[7]/button")
	WebElement update;
	@FindBy(xpath = "//div[contains(text(),'Nothing to show here')]")
	WebElement exist;
	@FindBy(xpath = "//tr//descendant::div[text()='Total']/ancestor::tr/td[9]/span/div")
	WebElement GST1;
	public static double RecievableAmount = 0.0;
	// Constructor
	public XeroAgedRecievableSummaryPage() {	
		PageFactory.initElements(DriverManager.getDriver(), this); 
	}
	//list  of all the actions on page
	public static String getPageTitle() {
		return DriverManager.getDriver().getTitle();
	}

	public void clickOnAccountingButton() {
		accountingButton.click();
	}
	public void clickOnRecievable() {
		recievable.click();
	}
	public void clickOnColmSelected() {
		colSelected.click();
	}
	public void clickOnOutstanding_GST() {
		outstanding_gst_rec.click();
	}
	public void clickOnEndOfMonth() {
		end_of_month.click();
	}
	public void clickOnLastFinancialYear() {
		last_financial_year.click();
	}
	public void clickOnUpdate() {
		update.click();
	}
	public void getAgedRecievableValues() {
		
		if (exist.isDisplayed()) {
			HashMap<String, Double> hm2 = new HashMap<>();
			hm2.put("Add: GST on Debtors", RecievableAmount);
			LAST_TABLE_DATA.add(hm2);
		} else { 
			RecievableAmount=Double.parseDouble(GST1.getText().replaceAll(",", ""));
			HashMap<String, Double> hm2 = new HashMap<>();
			hm2.put("Add: GST on Debtors", RecievableAmount);
			LAST_TABLE_DATA.add(hm2);
		}
	}
}
