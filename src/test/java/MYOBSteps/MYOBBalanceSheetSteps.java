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
               
//        balanceSheet.getMYOBData();
//        for (int i = 0; i < MYOBBalanceSheetPage.LAST_TABLE_DATA.size(); i++) {
//            System.out.println("Index " + i + ": " + MYOBBalanceSheetPage.LAST_TABLE_DATA.get(i));
//        }
//        String recipientEmail = BaseClass.SENDER_TO ; // Replace with the actual recipient's email address
//        balanceSheet.generateExcelAndSendEmail(recipientEmail);
    }

    @When("user clicks on the reporting and reports button")
    public void user_clicks_on_the_reporting_and_reports_button() throws InterruptedException {
        balanceSheet.clickReportingButton();
        balanceSheet.clickReportsButton();
        System.out.println("User clicked on the reporting and reports button");
    }
}
