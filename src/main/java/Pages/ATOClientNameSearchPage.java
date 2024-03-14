package Pages;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.asis.util.BaseClass;
import com.asis.util.ExcelUtil;

import Driver_manager.DriverManager;

public class ATOClientNameSearchPage extends BaseClass{

	//list of webelements
	@FindBy(xpath = "//input[@type='search']")
	WebElement clientNameSearch;

	//constructor
	public ATOClientNameSearchPage(){
		PageFactory.initElements(DriverManager.getDriver(), this);       
	}

	//list  of all the actions on page
	public String getPageTitle() {
		return DriverManager.getDriver().getTitle();
	}

	public void enterClientName() {		
		String client_name = CLIENT_DATA.get("client_name");
		clientNameSearch.sendKeys(client_name);
		try {
			ExcelUtil.closeExcel();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void doSearchClientName() {
		clientNameSearch.sendKeys(Keys.ENTER);
	}
}
