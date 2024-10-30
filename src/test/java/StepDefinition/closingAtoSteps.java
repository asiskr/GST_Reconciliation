package StepDefinition;

import Pages.closingATOPage;
import Pages.ATOExtractingBASValuesPage;
import io.cucumber.java.en.*;

public class closingAtoSteps {

	public closingATOPage extractValues  = new closingATOPage();

	
	@Given("User done with the ATO data")
	public void user_done_with_the_ATO_data() {
		System.out.println("closingATOPage title"+ closingATOPage.getPageTitle());
		
	}

	@When("User close the browser")
	public void user_close_the_browser() {
		extractValues.closeBrowser();
	}
}
