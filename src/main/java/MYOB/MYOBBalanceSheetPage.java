package MYOB;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MYOBBalanceSheetPage {

	@FindBy(xpath="//div[contains(text(),'Balance sheet')]")
	WebElement balanceSheet;
	@FindBy(xpath="//input[@id='Input_ryrPgfpC5N0']")
	WebElement toDate;
	
}
