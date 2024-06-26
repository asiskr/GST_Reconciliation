package MYOB;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.asis.util.BaseClass;

import Driver_manager.DriverManager;

public class MYOBGSTReportPage extends BaseClass{

	@FindBy(xpath="//div[@data-id='GST return']//a")
	WebElement gstReturn;
	@FindBy(xpath="//input[@name='GST_FROM_DATE']")
	WebElement from;
	@FindBy(xpath="//input[@name='GST_TO_DATE']")
	WebElement to;
	@FindBy(xpath="//div[@title='Total sales']/following-sibling::div[@role='cell'][2]")
	WebElement G1;
	@FindBy(xpath="//div[contains(., 'GST on sales')]/following-sibling::div[@role='cell'][2]")
	WebElement A1;
	@FindBy(xpath="//div[contains(., 'GST on purchases')]/following-sibling::div[@role='cell'][2]")
	WebElement B1;

	@FindBy(xpath="//div[contains(text(),'Reporting')]")
	WebElement reporting;
	@FindBy(xpath="//span[contains(text(),'Reports')]")
	WebElement reports;
	/*
	@FindBy(xpath="//div[contains(., 'Total salary, wages and other payments')]/following-sibling::div[@role='cell']/text()")
	WebElement W1;
	@FindBy(xpath="//div[contains(., 'Amount withheld from payments shown at W1')]/following-sibling::div[@role='cell']/text()")
	WebElement _4;
	 */
	public MYOBGSTReportPage(){	
		PageFactory.initElements(DriverManager.getDriver(), this);    
	}
	public void clickGSTReportPage(){	
		wait.until(ExpectedConditions.elementToBeClickable(gstReturn));
		gstReturn.click();
	}
	public void passFromDate() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(from));
		from.clear();
		from.sendKeys("01/07/2022");
		Thread.sleep(3000);
	}
	public void passToDate() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(to));
		to.clear();
		to.sendKeys("30/06/2023");
		Thread.sleep(3000);
	}
	public void extractG1A1B1Value() {
		fetchCaptureA1G1B1Data.add(G1.getText().replaceAll("[,]", ""));
		System.out.println("G1: " + G1.getText().replaceAll("[,]", ""));
		fetchCaptureA1G1B1Data.add(A1.getText().replaceAll("[,]", ""));
		System.out.println("A1: " + A1.getText().replaceAll("[,]", ""));
		fetchCaptureA1G1B1Data.add(B1.getText().replaceAll("[,]", ""));
		System.out.println("B1: " + B1.getText().replaceAll("[,]", ""));
		//		fetchCaptureA1G1B1Data.add(W1.getText().replaceAll("[,]", ""));
		//		fetchCaptureA1G1B1Data.add(_4.getText().replaceAll("[,]", ""));
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
