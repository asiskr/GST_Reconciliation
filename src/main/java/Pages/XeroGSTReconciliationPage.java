package Pages;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.asis.util.BaseClass;
import Driver_manager.DriverManager;

public class XeroGSTReconciliationPage extends BaseClass {


	@FindBy(xpath = "//button[contains(text(),'Accounting')]")
	WebElement accountingButton;
	@FindBy(xpath = "//a[contains(text(),'GST Reconciliation')]")
	WebElement GSTreconcil;
	@FindBy(xpath = "//span[contains(text(),'Export')]")
	WebElement export;
	@FindBy(xpath = "//a[@title='Export to Excel']")
	WebElement exportToExcel;
	
	// Constructor
	public XeroGSTReconciliationPage() {	
		PageFactory.initElements(DriverManager.getDriver(), this); 
	}
	//list  of all the actions on page
	public static String getPageTitle() {
		return DriverManager.getDriver().getTitle();
	}

	public void clickOnAccountingButton() {
		accountingButton.click();
	}
	public void clickOnGSTReconciliationButton() {
		GSTreconcil.click();
	}
	public void clickExport() {
		js.executeScript("arguments[0].scrollIntoView(true)", export);
		export.click();
	}
	public void clickOnExportToExcel() {
		exportToExcel.click();
		DriverManager.getDriver().quit();
	}
}
