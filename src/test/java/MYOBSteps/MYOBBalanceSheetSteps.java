package MYOBSteps;

import com.asis.util.BaseClass;

import MYOB.MYOBBalanceSheetPage;
import io.cucumber.java.en.*;

public class MYOBBalanceSheetSteps extends BaseClass{

	public MYOBBalanceSheetPage balanceSheet = new MYOBBalanceSheetPage();
	
	@Given("User is on the  report page")
	public void user_is_on_the_report_page() {
	}

	@When("user click on Balance Sheet report")
	public void user_click_on_balance_sheet_report() {
		balanceSheet.clickBalanceSheet();
	}

	@Then("User enter the to date at balance sheet page")
	public void user_enter_the_to_date_at_balance_sheet_page() throws InterruptedException {
		balanceSheet.passToDate();
	}

	@Then("user extract GSTCollected,GSTPaid, GSTActual, GSTLastYear")
	public void user_extract_gst_collected_gst_paid_gst_actual_gst_last_year() {
		balanceSheet.getTextGst();
	}

	@When("user click on the reporting and reports button")
	public void user_click_on_the_reporting_and_reports_button() {
		balanceSheet.clickReportingButton();
		balanceSheet.clickReportsButton();
	}
}
