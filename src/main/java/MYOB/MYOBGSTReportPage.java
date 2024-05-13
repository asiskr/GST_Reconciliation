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
	@FindBy(xpath="//input[@id='Input_ByNvUdDJbG0']")
	WebElement from;
	@FindBy(xpath="//input[@id='Input_ByUvL_Pk-MR']")
	WebElement to;
	@FindBy(xpath="//div[@title='Total sales']/following-sibling::div[@role='cell']/text()")
	WebElement G1;
	@FindBy(xpath="//div[contains(., 'GST on sales')]/following-sibling::div[@role='cell']/text()")
	WebElement A1;
	@FindBy(xpath="//div[contains(., 'GST on purchases')]/following-sibling::div[@role='cell']/text()")
	WebElement B1;
	/*
	@FindBy(xpath="//div[contains(., 'Total salary, wages and other payments')]/following-sibling::div[@role='cell']/text()")
	WebElement W1;
	@FindBy(xpath="//div[contains(., 'Amount withheld from payments shown at W1')]/following-sibling::div[@role='cell']/text()")
	WebElement _4;
	*/
	public MYOBGSTReportPage(){	
		PageFactory.initElements(DriverManager.getDriver(), this);    
	}
	public void passFromDate() {
		wait.until(ExpectedConditions.elementToBeClickable(from));
		from.click();
		from.sendKeys("01/07/2022");
	}
	public void passToDate() {
		wait.until(ExpectedConditions.elementToBeClickable(to));
		to.click();
		to.sendKeys("30/06/2023");
	}
	public void extractG1A1B1Value() {
		fetchCaptureA1G1B1Data.add(G1.getText().replaceAll("[,]", ""));
		fetchCaptureA1G1B1Data.add(A1.getText().replaceAll("[,]", ""));
		fetchCaptureA1G1B1Data.add(B1.getText().replaceAll("[,]", ""));
//		fetchCaptureA1G1B1Data.add(W1.getText().replaceAll("[,]", ""));
//		fetchCaptureA1G1B1Data.add(_4.getText().replaceAll("[,]", ""));
	}
}
