package MYOB;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.asis.util.BaseClass;

import Driver_manager.DriverManager;

public class MYOBBalanceSheetPage extends BaseClass {

    @FindBy(xpath = "//div[contains(text(),'Balance sheet')]")
    WebElement balanceSheet;
    @FindBy(xpath = "//input[@name='BALANCE_DATE']")
    WebElement toDate;
    @FindBy(xpath = "//div[@role='row'][17]/div[1]//span[@class='btn__content _19fk75g19 _19fk75g1a']")
    WebElement gstCollected;
    @FindBy(xpath = "//div[@role='row'][18]/div[1]//span[@class='btn__content _19fk75g19 _19fk75g1a']")
    WebElement gstPaid;
    @FindBy(xpath = "//div[@role='row'][27]/div[1]//span[@class='btn__content _19fk75g19 _19fk75g1a']")
    WebElement gstAdjustmentActual;
    @FindBy(xpath = "//div[@role='row'][27]/div[2]//span[@class='btn__content _19fk75g19 _19fk75g1a']")
    WebElement gstAdjustmentLastYear;
    @FindBy(xpath = "//div[contains(text(),'Reporting')]")
    WebElement reporting;
    @FindBy(xpath = "//span[contains(text(),'Reports')]")
    WebElement reports;

    // Static variables to store values
    public static double gstColl;
    public static double gstPai;
    public static double gstAdjustmentAactu;
    public static double gstAdjustmentlast;
    public static double finalGst;

    // Declare LAST_TABLE_DATA as static
    public static final List<HashMap<String, Double>> LAST_TABLE_DATA = new ArrayList<>();

    public MYOBBalanceSheetPage() {    
        PageFactory.initElements(DriverManager.getDriver(), this); 
    }
    
    // List of all the actions on page
    public void clickBalanceSheet() {
        wait.until(ExpectedConditions.visibilityOf(balanceSheet));
        balanceSheet.click();
    }
    
    public void passToDate() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(toDate));
        String StringToDate =XERO_TO_DATE;
        toDate.sendKeys(Keys.CONTROL + "a");
        toDate.sendKeys(Keys.DELETE);

        toDate.sendKeys(StringToDate);
        Thread.sleep(3000);
    }
    
    public void getTextGst() {
        wait.until(ExpectedConditions.visibilityOf(gstCollected));
        String gstCollStr = gstCollected.getText().replaceAll(",", "").replaceAll("[()]", "");
        gstColl = gstCollStr.startsWith("(") ? -Double.parseDouble(gstCollStr) : Double.parseDouble(gstCollStr);
        System.out.println("GST Collected: " + gstColl);

        wait.until(ExpectedConditions.visibilityOf(gstPaid));
        String gstPaiStr = gstPaid.getText().replaceAll(",", "").replaceAll("[()]", "");
        gstPai = gstPaiStr.startsWith("(") ? -Double.parseDouble(gstPaiStr) : Double.parseDouble(gstPaiStr);
        System.out.println("GST Paid: " + gstPai);

        wait.until(ExpectedConditions.visibilityOf(gstAdjustmentActual));
        String gstAdjustmentAactuStr = gstAdjustmentActual.getText().replaceAll(",", "").replaceAll("[()]", "");
        gstAdjustmentAactu = gstAdjustmentAactuStr.startsWith("(") ? -Double.parseDouble(gstAdjustmentAactuStr) : Double.parseDouble(gstAdjustmentAactuStr);
        System.out.println("GST Adjustment Actual: " + gstAdjustmentAactu);

        wait.until(ExpectedConditions.visibilityOf(gstAdjustmentLastYear));
        String gstAdjustmentlastStr = gstAdjustmentLastYear.getText().replaceAll(",", "").replaceAll("[()]", "");
        gstAdjustmentlast = gstAdjustmentlastStr.startsWith("(") ? -Double.parseDouble(gstAdjustmentlastStr) : Double.parseDouble(gstAdjustmentlastStr);
        System.out.println("GST Adjustment Last Year: " + gstAdjustmentlast);
        
        if (gstAdjustmentlast == gstAdjustmentAactu) {
            finalGst = gstColl - gstPai;
        } else {
            finalGst = (gstColl - gstPai) + gstAdjustmentAactu;
        }
        System.out.println("Final GST: " + finalGst);

        HashMap<String, Double> hm5 = new HashMap<>();
        hm5.put("GST as per Balance sheet", finalGst);
        LAST_TABLE_DATA.add(hm5);
        
        if (LAST_TABLE_DATA.size() > 4) {
            HashMap<String, Double> hm6 = new HashMap<>();
            hm6.put("Total - GST as per balance sheet", 
                LAST_TABLE_DATA.get(3).get("Total") - LAST_TABLE_DATA.get(4).get("GST as per Balance sheet"));
            LAST_TABLE_DATA.add(hm6);
        }
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
