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

public class MYOBAgedRecieveablePage extends BaseClass {

    @FindBy(xpath = "//div[contains(text(),'Receivables reconciliation with tax')]")
    WebElement receivable;

    @FindBy(xpath = "//input[@name='AS_AT_DATE']")
    WebElement toDate;

    @FindBy(xpath = "//div[@role='row' and .//span[text()='Total']]//div[3]//span")
    WebElement total;

    @FindBy(xpath = "//div[contains(text(),'Reporting')]")
    WebElement reporting;

    @FindBy(xpath = "//span[contains(text(),'Reports')]")
    WebElement reports;

    public static double RecievableAmounts = 0.0;

    public MYOBAgedRecieveablePage() {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    public void clickReceivableButton() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(receivable));
            receivable.click();
        } catch (Exception e) {
            System.err.println("Failed to click Receivable button: " + e.getMessage());
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
            Thread.sleep(3000);
        } catch (Exception e) {
            System.err.println("Failed to enter 'To Date': " + e.getMessage());
            throw e;
        }
    }

    public void receivableAmount() throws InterruptedException {
        try {
            // Adding a retry mechanism for better handling of delayed elements
            int retries = 3;
            for (int i = 0; i < retries; i++) {
                try {
                	JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
                    js.executeScript("arguments[0].scrollIntoView(true);", total); // Scroll to the 'total' element
                    Thread.sleep(5000);
                    wait.until(ExpectedConditions.visibilityOf(total));
                    String totalAmount = total.getText().replaceAll(",", "");
                    RecievableAmounts = Double.parseDouble(totalAmount);
                    System.out.println("Aged Receivable Amount: " + RecievableAmounts);

                    HashMap<String, Double> hm2 = new HashMap<>();
                    hm2.put("Add: GST on Debtors", RecievableAmounts);
                    LAST_TABLE_DATA.add(hm2);
                    break; // Exit loop if successful
                } catch (org.openqa.selenium.NoSuchElementException e) {
                    System.err.println("Retrying to locate the element: Attempt " + (i + 1));
                    if (i == retries - 1) {
                        throw e; 
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error extracting Aged Receivable amount: " + e.getMessage());
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
