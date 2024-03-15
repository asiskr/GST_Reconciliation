package TestRunners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features= {"src/test/resources/Features/"},
			/*{
					"src/test/resources/Features/1test_ATOLogin.feature",
					"src/test/resources/Features/2test_ATOsearchClientName.feature",
			},
	
	/*{
				"src/test/resources/Features/1test_ATOLogin.feature",
				"src/test/resources/Features/2test_ATOsearchClientName.feature",
				"src/test/resources/Features/3test_ATOSelectingQuarterDate.feature",
				"src/test/resources/Features/4test_ATOExtractingBASValues.feature",
				"src/test/resources/Features/5test_ATOFetchingICAStatement.feature",
				"src/test/resources/Features/6test_CloseATOProtal.feature",
				"src/test/resources/Features/7test_XeroLogin.feature",
				"src/test/resources/Features/8test_XeroSecurityQuestion.feature",
				"src/test/resources/Features/9test_XeroSelectingQuarterDate.feature",
				"src/test/resources/Features/test1_XeroGetQuarterValues.feature",
				"src/test/resources/Features/test2_XeroExtractingAgedRecievableSummary.feature",
				"src/test/resources/Features/test3_XeroExtractingAgedPayableValues.feature",
				"src/test/resources/Features/test4_XeroBalanceSheet.feature",
				"src/test/resources/Features/test5_XeroGSTReconciliation.feature",
				 "src/test/resources/Features/test6_GenratingExcel.feature",
		},*/
		glue={"StepDefinition","MyHooks"},
		plugin = {"pretty"}
//		tags= "@order1 or @order2 or @order5 or @order6"
		)

public class TestRunner {

}
