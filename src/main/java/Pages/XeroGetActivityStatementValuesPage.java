package Pages;

import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.asis.Excel;
import com.asis.util.BaseClass;
import Driver_manager.DriverManager;

public class XeroGetActivityStatementValuesPage extends BaseClass{

	@FindBy(xpath = "//tr//descendant::span[contains(text(),'Total sales')]/ancestor::tr/td[3]//descendant::span[1]")
	WebElement G1;
	@FindBy(xpath = "//tr//descendant::span[contains(text(),'GST on sales')]/ancestor::tr/td[3]//descendant::span[1]")
	WebElement A1;
	@FindBy(xpath = "//tr//descendant::span[contains(text(),'GST on purchases')]/ancestor::tr/td[3]//descendant::span[1]")
	WebElement B1;
	// Constructor
	public XeroGetActivityStatementValuesPage() {	
		PageFactory.initElements(DriverManager.getDriver(), this); 
	}
	//list  of all the actions on page
	public String getPageTitle() {
		return DriverManager.getDriver().getTitle();
	}
	public void captureA1G1B1Data() {
		
		fetchCaptureA1G1B1Data.add(G1.getText().replaceAll("[,]", ""));
		fetchCaptureA1G1B1Data.add(A1.getText().replaceAll("[,]", ""));
		fetchCaptureA1G1B1Data.add(B1.getText().replaceAll("[,]", ""));
	}
	public void generateExcel() {
		String[] client_data = {BaseClass.CLIENT_DATA.get("client_name").trim(), BaseClass.CLIENT_DATA.get("to_date").trim()};
		Excel obj = new Excel();
		obj.createFinancialSummaryExcelWithData("Final_data.xls", BaseClass.ATO_ROW_DATA, BaseClass.XERO_DATA, BaseClass.ACTIVITY_STATEMENT_DATA,client_data);
	}
}
