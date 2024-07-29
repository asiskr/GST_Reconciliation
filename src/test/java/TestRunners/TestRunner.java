package TestRunners;
import org.junit.runner.RunWith;

import com.asis.util.BaseClass;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {
				"src/test/resources/Features/11test_MYOBLogin.feature",
				"src/test/resources/Features/12test_ATOLogin.feature",
				"src/test/resources/Features/13test_ATOsearchClientName.feature",
				"src/test/resources/Features/14test_ATOSelectingQuarterDate.feature",
				"src/test/resources/Features/15test_ATOExtractingBASValues.feature",
				"src/test/resources/Features/16test_ATOFetchingICAStatement.feature",
				"src/test/resources/Features/17test_CloseATOProtal.feature",
				"src/test/resources/Features/2test_MYOBReports.feature",
				"src/test/resources/Features/3test_MYOBActivity.feature",
				"src/test/resources/Features/4test_MYOBPayroll.feature",
				"src/test/resources/Features/5test_MYOBAgedReceivable.feature",
				"src/test/resources/Features/6test_MYOBAgedPayable.feature",
				"src/test/resources/Features/7test_MYOBBalanceSheet.feature",
				"src/test/resources/Features/_7MYOBGenratingEmail.feature",
				//            "src/test/resources/Features/7test_XeroLogin.feature",
				//            "src/test/resources/Features/8test_XeroSecurityQuestion.feature",
				//            "src/test/resources/Features/9test_XEROSearchClient.feature",
				//            "src/test/resources/Features/test1_XeroSelectingQuarterDate.feature",
				//            "src/test/resources/Features/test2_XeroGetQuarterValues.feature",
				//            "src/test/resources/Features/test3_XeroExtractingAgedRecievableSummary.feature",
				//            "src/test/resources/Features/test4_XeroExtractingAgedPayableValues.feature",
				//            "src/test/resources/Features/test5_XeroBalanceSheet.feature",
				//            "src/test/resources/Features/test6_XeroGSTReconciliation.feature",
				//            "src/test/resources/Features/test7_GenratingExcel.feature"
		},
		glue = {"MYOBSteps", "MyHooks", "StepDefinition"},
		plugin = {"pretty"}
		)
public class TestRunner {

}
