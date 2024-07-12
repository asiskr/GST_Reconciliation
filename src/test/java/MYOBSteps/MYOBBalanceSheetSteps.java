package MYOBSteps;

import com.asis.util.BaseClass;

import MYOB.MYOBAgedPayableSummaryPage;
import MYOB.MYOBBalanceSheetPage;
import io.cucumber.java.en.*;

public class MYOBBalanceSheetSteps extends BaseClass {

    public MYOBBalanceSheetPage balanceSheet = new MYOBBalanceSheetPage();
    
    @Given("User is on the report page")
    public void user_is_on_the_report_page() {
        System.out.println("User is on the report page");
    }

    @When("user clicks on Balance Sheet report")
    public void user_clicks_on_balance_sheet_report() {
        balanceSheet.clickBalanceSheet();
        System.out.println("User clicked on Balance Sheet report");
    }

    @Then("User enters the to date at balance sheet page")
    public void user_enters_the_to_date_at_balance_sheet_page() throws InterruptedException {
        balanceSheet.passToDate();
        System.out.println("User entered the to date at balance sheet page");
    }

    @Then("user extracts GSTCollected, GSTPaid, GSTActual, GSTLastYear")
    public void user_extracts_gst_collected_gst_paid_gst_actual_gst_last_year() {
        balanceSheet.getTextGst();
        
        // Ensure LAST_TABLE_DATA is accessed correctly
        if (MYOBBalanceSheetPage.LAST_TABLE_DATA.size() > 4) {
            System.out.println("Total: " + MYOBAgedPayableSummaryPage.LAST_TABLE_DATA.get(4).get("Total"));
        } else {
            System.out.println("Total value not found in LAST_TABLE_DATA.");
            
        }
        
        // Print the entire LAST_TABLE_DATA for debugging
        System.out.println("Contents of LAST_TABLE_DATA:");
        for (int i = 0; i < MYOBBalanceSheetPage.LAST_TABLE_DATA.size(); i++) {
            System.out.println("Index " + i + ": " + MYOBBalanceSheetPage.LAST_TABLE_DATA.get(i));
        }
    }

    @When("user clicks on the reporting and reports button")
    public void user_clicks_on_the_reporting_and_reports_button() throws InterruptedException {
        balanceSheet.clickReportingButton();
        balanceSheet.clickReportsButton();
        System.out.println("User clicked on the reporting and reports button");
    }
}
