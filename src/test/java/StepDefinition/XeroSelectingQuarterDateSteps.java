package StepDefinition;

import java.text.ParseException;

import Pages.XeroSelectingQuarterDatePage;
import io.cucumber.java.en.*;

public class XeroSelectingQuarterDateSteps {

    public XeroSelectingQuarterDatePage xeroselectDate = new XeroSelectingQuarterDatePage();
    @Given("user is on client home page")
    public void user_is_on_client_home_page() {
    }
    @When("user clicks on the Accounting button")
    public void user_clicks_on_accounting_button() throws InterruptedException {
        xeroselectDate.clickAccountingButton();
    }
    @When("user clicks on Activity Statement")
    public void user_clicks_on_activity_statement() {
        xeroselectDate.clickActivityStatement();
    }
    @Then("user performs actions based on Needs Attention")
    public void user_performs_actions_based_on_needs_attention() throws ParseException, InterruptedException {
        xeroselectDate.performActionBasedOnAttention();
    }  
    @Then("user is on client Activity statement page")
    public void user_is_on_client_activity_statement_page() {
    }
}
