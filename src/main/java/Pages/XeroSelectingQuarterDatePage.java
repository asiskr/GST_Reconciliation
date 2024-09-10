package Pages;

import java.text.ParseException;
import java.util.NoSuchElementException;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.asis.util.BaseClass;
import Driver_manager.DriverManager;

public class XeroSelectingQuarterDatePage extends BaseClass {

    @FindBy(xpath = "//button[@data-name='navigation-menu/accounting']")
    WebElement accountingButton;
    @FindBy(xpath = "//a[contains(text(),'Reports')]")
    WebElement reports;
    @FindBy(xpath = "//*[@class='report-row-tooltip']//descendant::span[contains(text(),'Activity Statement')]")
    WebElement activityStatement;
    @FindBy(xpath = "//h2[contains(text(),'Activity Statement Settings')]")
    WebElement activityStatementSettings;
    @FindBy(xpath = "//h2[contains(text(),'Lodge activity statements with Xero')]")
    WebElement lodgeStatement;
    @FindBy(xpath = "//button[contains(text(),'Switch to manual Activity Statements')]")
    WebElement manualStatement;
    @FindBy(xpath = "//h2[contains(text(),'Needs attention')]")
    WebElement needsAttention;
    @FindBy(xpath = "//button[contains(text(),'Proceed')]")
    WebElement proceed1;
    @FindBy(xpath = "//button[@data-automationid='bas-header-settings-button']")
    WebElement settings;
    @FindBy(xpath = "//button[contains(text(),'Go to previous BAS experience')]")
    WebElement previousBAS;
    @FindBy(xpath = "//button[contains(text(),'Proceed')]")
    WebElement proceed;
    @FindBy(xpath = "//button[contains(text(),'Lodge reports to ATO outside of Xero')]")
    WebElement lodgeReports;
    @FindBy(xpath = "//button[contains(@onclick, 'SubmitAction') and .//span[text()='Save']]")
    WebElement save1;
    @FindBy(xpath = "//button[contains(@onclick, 'SubmitAction') and .//span[text()='Save']]")
    WebElement save2;
    @FindBy(xpath = "//input[@id='fromDate']")
    WebElement from;
    @FindBy(xpath = "//input[@id='dateTo']")
    WebElement to;
    @FindBy(xpath = "//a[@id='ext-gen27']")
    WebElement updateButton;

    public XeroSelectingQuarterDatePage() {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    public void clickAccountingButton() throws InterruptedException {
        Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(accountingButton));
        accountingButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(reports));
        reports.click();
    }

    public void clickActivityStatement() {
        wait.until(ExpectedConditions.elementToBeClickable(activityStatement));
        activityStatement.click();
    }

    public void performActionBasedOnAttention() throws ParseException, InterruptedException {
        try {
            if (isElementPresent(needsAttention)) {
                handleNeedsAttention();

                Thread.sleep(1000);
                if (isElementPresent(activityStatementSettings)) {
                    try {
                        wait.until(ExpectedConditions.elementToBeClickable(manualStatement));
                        manualStatement.click();
                    } catch (TimeoutException e) {
//                        System.out.println("'Switch to manual Activity Statements' button not found. Skipping this step.");
                    }

                    if (isElementPresent(lodgeStatement)) {
                        try {
                            wait.until(ExpectedConditions.elementToBeClickable(lodgeReports));
                            lodgeReports.click();
                        } catch (TimeoutException e) {
//                            System.out.println("'Lodge reports to ATO outside of Xero' button not found. Skipping this step.");
                        }

                        try {
                            wait.until(ExpectedConditions.elementToBeClickable(proceed1));
                            proceed1.click();
                        } catch (TimeoutException e) {
//                            System.out.println("'Proceed' button not found. Skipping this step.");
                        }
                    }
                    goToPreviousBAS();
                    clickAccountingButton();
                    clickActivityStatement();
                }
            }
        } catch (NoSuchElementException e) {
//            System.out.println("No 'Needs attention' section found. Proceeding with date selection.");
        }
        enterFromDate();
        enterToDate();
        clickUpdateButton();
    }

    public boolean isElementPresent(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (NoSuchElementException | TimeoutException e) {
            return false;
        }
    }

    public void handleNeedsAttention() {
        wait.until(ExpectedConditions.elementToBeClickable(settings));
        settings.click();
    }

    public void goToPreviousBAS() {
        wait.until(ExpectedConditions.elementToBeClickable(previousBAS));
        previousBAS.click();
        wait.until(ExpectedConditions.elementToBeClickable(proceed));
        proceed.click();
        wait.until(ExpectedConditions.elementToBeClickable(save1));
        save1.click();
        wait.until(ExpectedConditions.elementToBeClickable(save2));
        save2.click();
    }

    public void enterFromDate() throws ParseException {
        String stringFromDate = ATO_FROM_DATE;
        wait.until(ExpectedConditions.elementToBeClickable(from));
        from.clear();
        from.sendKeys(stringFromDate);
    }

    public void enterToDate() throws ParseException {
        String stringToDate = ATO_TO_DATE;
        wait.until(ExpectedConditions.elementToBeClickable(to));
        to.clear();
        to.sendKeys(stringToDate);
    }

    public void clickUpdateButton() {
        wait.until(ExpectedConditions.elementToBeClickable(updateButton));
        updateButton.click();
    }
}
