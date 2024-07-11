package TestRunners;

import com.asis.util.BaseClass;

import io.cucumber.core.cli.Main;

public class CucumberTestConfig extends BaseClass{
    public static String[] getFeaturePaths() {
        String software = System.getProperty("SOFTWARE1", "myob");
        if ("MYOB".equalsIgnoreCase(SOFTWARE1)) {
            return new String[]{
                "src/test/resources/Features/_1MYOBLogin.feature",
                "src/test/resources/Features/_2MYOBReports.feature",
                "src/test/resources/Features/_3MYOBActivity.feature",
                "src/test/resources/Features/_4MYOBPayroll.feature",
                "src/test/resources/Features/_5MYOBAgedReceivable.feature",
                "src/test/resources/Features/_6MYOBAgedPayable.feature",
                "src/test/resources/Features/_7MYOBBalanceSheet.feature"
            };
        } else {
            return new String[]{
                "src/test/resources/Features/1test_ATOLogin.feature",
                "src/test/resources/Features/2test_ATOsearchClientName.feature",
                "src/test/resources/Features/3test_ATOSelectingQuarterDate.feature",
                "src/test/resources/Features/4test_ATOExtractingBASValues.feature",
                "src/test/resources/Features/5test_ATOFetchingICAStatement.feature",
                "src/test/resources/Features/6test_CloseATOProtal.feature",
                "src/test/resources/Features/7test_XeroLogin.feature",
                "src/test/resources/Features/8test_XeroSecurityQuestion.feature",
                "src/test/resources/Features/9test_XEROSearchClient.feature",
                "src/test/resources/Features/test1_XeroSelectingQuarterDate.feature",
                "src/test/resources/Features/test2_XeroGetQuarterValues.feature",
                "src/test/resources/Features/test3_XeroExtractingAgedRecievableSummary.feature",
                "src/test/resources/Features/test4_XeroExtractingAgedPayableValues.feature",
                "src/test/resources/Features/test5_XeroBalanceSheet.feature",
                "src/test/resources/Features/test6_XeroGSTReconciliation.feature",
                "src/test/resources/Features/test7_GenratingExcel.feature"
            };
        }
    }

    public static String[] getGluePaths() {
        String software = System.getProperty("SOFTWARE1", "myob");
        if ("myob".equalsIgnoreCase(software)) {
            return new String[]{"MYOBSteps", "MyHooks"};
        } else {
            return new String[]{"StepDefinition", "MyHooks"};
        }
    }
}
