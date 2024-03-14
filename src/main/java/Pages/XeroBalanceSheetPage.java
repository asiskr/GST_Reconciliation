package Pages;

import java.util.HashMap;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.asis.util.BaseClass;
import Driver_manager.DriverManager;

public class XeroBalanceSheetPage extends BaseClass{


	@FindBy(xpath = "//button[contains(text(),'Accounting')]")
	WebElement accountingButton;
	@FindBy(xpath = "//a[contains(text(),'Balance Sheet')]")
	WebElement balanceSheet;
	@FindBy(xpath = "//tr//descendant::div[contains(text(),'GST')]/ancestor::tr/td[2]//a")
	WebElement GST;

	public static double GST_asperBalanceSheet = 0.0;
	// Constructor
	public XeroBalanceSheetPage() {	
		PageFactory.initElements(DriverManager.getDriver(), this); 
	}
	//list  of all the actions on page
	public static String getPageTitle() {
		return DriverManager.getDriver().getTitle();
	}

	public void clickOnAccountingButton() {
		accountingButton.click();
	}
	public void clickOnBalanceSheetButton() {
		balanceSheet.click();
	}
	public void fetchingGST() {
		
		System.out.println("Printing LAST_TABLE_DATA "+LAST_TABLE_DATA);
		
		GST_asperBalanceSheet= Double.parseDouble((GST).getText().replaceAll(",", ""));
		HashMap<String, Double> hm5 = new HashMap<>();
		hm5.put("GST as per Balance sheet", GST_asperBalanceSheet);
		LAST_TABLE_DATA.add(hm5);
		
		HashMap<String, Double> hm6 = new HashMap<>();
		hm6.put("Total - GST as per balance sheet",  (LAST_TABLE_DATA.get(3).get("Total") - LAST_TABLE_DATA.get(4).get("GST as per Balance sheet")));
		LAST_TABLE_DATA.add(hm6);
	}
}
