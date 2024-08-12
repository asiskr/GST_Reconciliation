package StepDefinition;

import java.util.HashMap;

import com.asis.util.BaseClass;

import XERO.ReadFiles;
import io.cucumber.java.en.*;

public class ReadFilesSteps extends BaseClass {
	public ReadFiles readFiles = new ReadFiles();

	@Given("IUser give the path of files")
	public void i_user_give_the_path_of_files() {
	}

	@When("give path")
	public void give_path() {
	}

	@When("extract data")
	public void extract_data() throws InterruptedException {    
		String pdfText1 = readFiles.readPdfFile(FILE1);
		HashMap<String, Double> extractedData1 = readFiles.extractFieldsToMap1(pdfText1);
		readFiles.clickOnJulyQuarter(extractedData1);
		
		String pdfText2 = readFiles.readPdfFile(FILE2);
		HashMap<String, Double> extractedData2 = readFiles.extractFieldsToMap2(pdfText2);
		readFiles.clickOnJulyQuarter(extractedData2);
		
		String pdfText3 = readFiles.readPdfFile(FILE3);
		HashMap<String, Double> extractedData3 = readFiles.extractFieldsToMap3(pdfText3);
		readFiles.clickOnJulyQuarter(extractedData3);
		
		String pdfText4 = readFiles.readPdfFile(FILE4);
		HashMap<String, Double> extractedData4 = readFiles.extractFieldsToMap4(pdfText4);
		readFiles.clickOnJulyQuarter(extractedData4);
	}
}
