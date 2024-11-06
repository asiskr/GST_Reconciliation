package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import com.asis.util.BaseClass;
import Driver_manager.DriverManager;

public class XeroGSTReconciliationPage extends BaseClass {

    @FindBy(xpath = "//button[contains(text(),'Accounting')]")
    WebElement accountingButton;

    @FindBy(xpath = "//a[contains(text(),'Reports')]")
    WebElement reports;

    @FindBy(xpath = "//*[@class='report-row-tooltip']//descendant::span[contains(text(),'GST Reconciliation')]")
    WebElement GSTreconcil;

    @FindBy(xpath = "//input[@id='fromDate']")
    WebElement From;

    @FindBy(xpath = "//input[@id='toDate']")
    WebElement To;

    @FindBy(xpath = "//a[contains(@class, 'xbtn') and @href='javascript:']")
    WebElement UpdateButton;

    @FindBy(xpath = "//span[contains(text(),'Export')]")
    WebElement export;

    @FindBy(xpath = "//a[@title='Export to Excel']")
    WebElement exportToExcel;

    // Constructor
    public XeroGSTReconciliationPage() {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    public static void getPageTitle() {
        // Placeholder for any title-related actions if needed
    }

    public void clickOnAccountingButton() {
        wait.until(ExpectedConditions.elementToBeClickable(accountingButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(reports)).click();
    }

    public void clickOnGSTReconciliationButton() {
        wait.until(ExpectedConditions.elementToBeClickable(GSTreconcil)).click();
    }

    public void clickExport() {
        String StringFromDate = ATO_FROM_DATE;
        wait.until(ExpectedConditions.elementToBeClickable(From)).clear();
        From.sendKeys(StringFromDate);
    }

    public void clickOnExportToExcel() throws InterruptedException {
        String StringToDate = ATO_TO_DATE;
        wait.until(ExpectedConditions.elementToBeClickable(To)).clear();
        To.sendKeys(StringToDate);
        Thread.sleep(2000); // Consider replacing this with explicit waits if possible

        // Updated logic to handle the UpdateButton element
        WebElement updateButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[contains(@class, 'xbtn') and @href='javascript:']")));
        wait.until(ExpectedConditions.elementToBeClickable(updateButton)).click();

        js.executeScript("arguments[0].scrollIntoView(true);", export);
        wait.until(ExpectedConditions.elementToBeClickable(export)).click();

        wait.until(ExpectedConditions.elementToBeClickable(exportToExcel)).click();
        Thread.sleep(3000); // Consider replacing this with explicit waits if possible

        DriverManager.getDriver().quit();
    }
}
