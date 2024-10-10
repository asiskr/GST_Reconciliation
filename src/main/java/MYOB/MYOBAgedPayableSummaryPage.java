package MYOB;

import java.util.HashMap;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import com.asis.util.BaseClass;
import Driver_manager.DriverManager;

public class MYOBAgedPayableSummaryPage extends BaseClass {

    @FindBy(xpath = "//div[contains(text(),'Payables reconciliation with tax')]")
    WebElement payable;

    @FindBy(xpath = "//input[@name='AS_AT_DATE']")
    WebElement toDate;

    @FindBy(xpath = "//div[@role='row' and .//span[text()='Total']]//div[3]//span")
    public WebElement total;

    @FindBy(xpath = "//div[contains(text(),'Reporting')]")
    WebElement reporting;

    @FindBy(xpath = "//span[contains(text(),'Reports')]")
    WebElement reports;

    public static double PayableAmount = 0.0;
    public static double total1 = 0.0;

    public MYOBAgedPayableSummaryPage() {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    // List of all the actions on page
    public void clickPayable() {
        try {
            wait.until(ExpectedConditions.visibilityOf(payable));
            payable.click();
        } catch (Exception e) {
            System.err.println("Failed to click Payable button: " + e.getMessage());
            throw e;
        }
    }

    public void passToDate() throws InterruptedException {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(toDate));
            String StringToDate = ATO_TO_DATE;
            toDate.sendKeys(Keys.CONTROL + "a");
            toDate.sendKeys(Keys.DELETE);
            toDate.sendKeys(StringToDate);
            Thread.sleep(3000); // Add sleep for manual intervention if needed
        } catch (Exception e) {
            System.err.println("Failed to enter 'To Date': " + e.getMessage());
            throw e;
        }
    }

    public void getPayableTotal() throws InterruptedException {
        try {
            // Retry mechanism for handling potential delays
            int retries = 3;
            for (int i = 0; i < retries; i++) {
                try {
                	JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
                    js.executeScript("arguments[0].scrollIntoView(true);", total); // Scroll to the 'total' element
                    Thread.sleep(2000);
                    wait.until(ExpectedConditions.visibilityOf(total));
                    String totalPayableStr = total.getText().replaceAll(",", "");
                    
                    try {
                        PayableAmount = Double.parseDouble(totalPayableStr);
                        System.out.println("Aged Payable Amount: " + PayableAmount);

                        HashMap<String, Double> hm3 = new HashMap<>();
                        hm3.put("Less: GST on Creditors", PayableAmount);
                        LAST_TABLE_DATA.add(hm3);
                        break; // Exit loop if successful
                    } catch (NumberFormatException e) {
                        System.err.println("Error parsing payable amount: " + totalPayableStr);
                        if (i == retries - 1) throw e; // Only rethrow after max retries
                    }
                } catch (org.openqa.selenium.NoSuchElementException e) {
                    System.err.println("Retrying to locate the 'Total' element: Attempt " + (i + 1));
                    if (i == retries - 1) throw e; // Rethrow after max retries
                }
            }
        } catch (Exception e) {
            System.err.println("Error extracting payable total: " + e.getMessage());
            throw e;
        }
    }

    public void clickReportingButton() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(reporting));
            reporting.click();
        } catch (Exception e) {
            System.err.println("Failed to click Reporting button: " + e.getMessage());
            throw e;
        }
    }

    public void clickReportsButton() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(reports));
            reports.click();
        } catch (Exception e) {
            System.err.println("Failed to click Reports button: " + e.getMessage());
            throw e;
        }
    }
}
