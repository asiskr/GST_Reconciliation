package StepDefinition;

import com.asis.util.BaseClass;

import Pages.ATOClientNameSearchPage;
import Pages.ATOGoToQuarterName;
import Pages.SelectTOPIdPage;
import io.cucumber.java.en.*;

public class ATOSearchClientNameStep extends BaseClass{
	public ATOClientNameSearchPage clientSearchPage  = new ATOClientNameSearchPage();
	public SelectTOPIdPage selectTOP= new SelectTOPIdPage();
	public ATOGoToQuarterName quaterNamePage;

	public static String taxtation = TAXATION;

	@Given("I am on home page")
	public void i_am_on_home_page() {
	}

	@When("I enter client name in search box")
	public void i_enter_client_name_in_search_box() throws InterruptedException {
		if (taxtation.equalsIgnoreCase("taxation")) {
			clientSearchPage.enterClientName();
		}
		else {
			selectTOP.clickOnTOPButton();
		}
		System.out.println("client name");
	}

	@When("I press enter keyword")
	public void i_press_enter_keyword() throws InterruptedException {
		if (taxtation.equalsIgnoreCase("taxation")) {
			clientSearchPage.doSearchClientName();
		}
		else {
			selectTOP.clickOnNextButton();
		}
	}

	@Then("I should land on client home screen")
	public void i_should_land_on_client_home_screen() {
	}

	@When("I go to Lodgments")
	public void i_go_to_lodgments () throws InterruptedException {
		quaterNamePage = new ATOGoToQuarterName();
		quaterNamePage.clickLodgments();
	}

	@And("Select Activity statements")
	public void select_activity_statements () {
		quaterNamePage.checkingOptionAndClickingActivityStatements();
	}


}
