package MYOB;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.asis.util.BaseClass;

import Driver_manager.DriverManager;

public class MYOBPayrollEmployeSummarryPage extends BaseClass{

	@FindBy(xpath="//div[contains(text(),'Payroll activity')]")
	WebElement payroll;
	@FindBy(xpath="//input[@id='Input_Hy-A7wCqER']")
	WebElement fromDate;
	@FindBy(xpath="//input[@id='Input_H1Q0QvAqEC']")
	WebElement toDate;
	@FindBy(xpath="//div[@role='row' and .//span[text()='Grand total']]//div[2]//span")
	WebElement _W1;
	@FindBy(xpath="//div[@role='row' and .//span[text()='Grand total']]//div[4]//span")
	WebElement _W2;
	@FindBy(xpath="//div[contains(text(),'Reporting')]")
	WebElement reporting;
	@FindBy(xpath="//span[contains(text(),'Reports')]")
	WebElement reports;
	
	public MYOBPayrollEmployeSummarryPage(){	
		PageFactory.initElements(DriverManager.getDriver(), this);    
	}
	public void clickPayroll() {
		wait.until(ExpectedConditions.elementToBeClickable(payroll));
		payroll.click();
	}
	public void passFromDate() {
		wait.until(ExpectedConditions.elementToBeClickable(toDate));
		fromDate.click();
		fromDate.sendKeys("01/07/2022");
	}
	public void passToDate() {
		wait.until(ExpectedConditions.elementToBeClickable(toDate));
		toDate.click();
		toDate.sendKeys("01/07/2022");
	}
	public void getW1W2() {
		fetchCaptureA1G1B1Data.add(_W1.getText().replaceAll("[,]", ""));
		fetchCaptureA1G1B1Data.add(_W2.getText().replaceAll("[,]", ""));
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
