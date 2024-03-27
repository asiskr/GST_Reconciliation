package Pages;

import java.util.HashMap;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.asis.util.BaseClass;
import Driver_manager.DriverManager;

public class XeroAgedRecievableSummaryPage extends BaseClass{


	@FindBy(xpath = "//button[contains(text(),'Accounting')]")
	WebElement accountingButton;
	@FindBy(xpath = "//a[contains(text(),'Reports')]")
	WebElement reports;
	@FindBy(xpath = "//*[@class='report-row-tooltip']//descendant::span[contains(text(),'Aged Receivables Summary')]")
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
	public static void getPageTitle() {
//		return DriverManager.getDriver().getTitle();
	}

	public void clickOnAccountingButton() {
		wait.until(ExpectedConditions.elementToBeClickable(accountingButton));
		accountingButton.click();
		wait.until(ExpectedConditions.elementToBeClickable(reports));
//		accountingButton.click();
		reports.click();
	}
	public void clickOnRecievable() {
		wait.until(ExpectedConditions.elementToBeClickable(recievable));
		recievable.click();
	}
	public void clickOnColmSelected() {
		wait.until(ExpectedConditions.elementToBeClickable(colSelected));
		colSelected.click();
	}
	public void clickOnOutstanding_GST() {
		wait.until(ExpectedConditions.elementToBeClickable(outstanding_gst_rec));
		outstanding_gst_rec.click();
	}
	public void clickOnEndOfMonth() {
		wait.until(ExpectedConditions.elementToBeClickable(end_of_month));
		end_of_month.click();
	}
	public void clickOnLastFinancialYear() {
		wait.until(ExpectedConditions.elementToBeClickable(last_financial_year));
		last_financial_year.click();
	}
	public void clickOnUpdate() {
		wait.until(ExpectedConditions.elementToBeClickable(update));
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
