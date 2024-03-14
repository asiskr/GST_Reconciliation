package Pages;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.asis.util.BaseClass;
import com.asis.util.ExcelUtil;

import Driver_manager.DriverManager;

public class ATOfetchingICAStatement extends BaseClass {

	@FindBy(xpath = "//div[@role='menubar']//span[contains(text(),'Accounts and payments')]")
	WebElement accountsAnsPayments;

	@FindBy(xpath ="//div[@role='menubar']//span[contains(text(),'Accounts and payments')]/parent::div/following-sibling::ul/li")
	WebElement options;

	@FindBy(xpath = "//span[contains(text(),'Activity statement')]/ancestor::div[@class='row table-panel-header']/following-sibling::div//span[contains(text(),'Activity statement ')]/parent::a")
	WebElement activityStatements;

	@FindBy(xpath = "//span[contains(text(),'Filter')]/parent::a")
	WebElement filter;

	@FindBy(xpath = "//input[contains(@id,'from-date')]")
	WebElement From;

	@FindBy(xpath = "//input[contains(@id,'to-date')]")
	WebElement To;

	@FindBy(xpath = "//button[contains(text(),'Filter')]")
	WebElement submit;

	@FindBy(xpath = "//button[contains(text(),'Print-friendly version')]")
	WebElement printFriendlyVersion;
	
	@FindBy(xpath = "//table[@class='table']/tbody/tr")
	List<WebElement> tableTr;
	
	String defaultTab = "";

	// Constructor
	public ATOfetchingICAStatement() {
		PageFactory.initElements(DriverManager.getDriver(), this);
	}
	public String getPageTitle() {
		return DriverManager.getDriver().getTitle();
	}
	public void gotoICAStatement() throws InterruptedException {
		js.executeScript("window.scrollBy(0,-250)");
	}
	public void clickAccountsAnsPayments() {
		accountsAnsPayments.click();
	}
	public void clickOptions() {
		List<WebElement> options = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@role='menubar']//span[contains(text(),'Accounts and payments')]/parent::div/following-sibling::ul/li")));
		for(WebElement option:options) {
			if(option.getText().trim().equalsIgnoreCase("Tax accounts")) {
				option.click();
				break;
			}
		}
	}
	public void clickActivityStatements() {
		activityStatements.click();
	}
	public void clickFilter() {
		js.executeScript("arguments[0].scrollIntoView(true)", filter);
		filter.click();
	}

	public void enterFromDaate() throws ParseException {
		DateFormat fromDateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH); 
		DateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date from_date = fromDateFormat.parse(CLIENT_DATA.get("from_date"));

		String StringFromDate = outputFormat.format(from_date);
		From.clear();
		From.sendKeys(StringFromDate);
		try {
			ExcelUtil.closeExcel();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void enterToDate() throws ParseException {
		DateFormat fromDateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH); 
		DateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date to_date = fromDateFormat.parse(CLIENT_DATA.get("to_date"));
		String StringToDate = outputFormat.format(to_date);
		To.clear();
		To.sendKeys(StringToDate);
		try {
			ExcelUtil.closeExcel();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void clickSubmitButton() {
		js.executeScript("arguments[0].click();", submit);
	}
	
	public void clickPrintFriendlyVersion() {
		js.executeScript("arguments[0].click();", printFriendlyVersion);
	}
	public void switchingTabs() {
		ArrayList<String> tabs = new ArrayList<>(DriverManager.getDriver().getWindowHandles());
		defaultTab = tabs.get(0);
		DriverManager.getDriver().switchTo().window(tabs.get(1));
	}
	
	public void extractActivityStatement() {
		for(WebElement tr : tableTr) {
			if(tr.isDisplayed()) {
				List<WebElement> tdData =  tr.findElements(By.tagName("td"));
				ArrayList<String> tdRowData = new ArrayList<String>();
				for(WebElement td: tdData) {
					tdRowData.add(td.getText());
				}
				ACTIVITY_STATEMENT_DATA.add(tdRowData);
			}
		}			
	}
	public void closingTabs() {
		DriverManager.getDriver().switchTo().window(defaultTab);
	}
}

