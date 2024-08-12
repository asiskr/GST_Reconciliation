package XERO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.support.PageFactory;

import com.asis.QuaterData;
import com.asis.util.BaseClass;
import Driver_manager.DriverManager;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadFiles extends BaseClass {

	private String ATO_JULY_QUARTER = "some_value"; // Set this appropriately
    public ReadFiles() {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    // Method to read and return PDF content as a string
    public String readPdfFile(String pdfFilePath) {
        String text = "";
        try {
            // Load the PDF document
            PDDocument document = PDDocument.load(new File(pdfFilePath));
            PDFTextStripper pdfStripper = new PDFTextStripper();
            text = pdfStripper.getText(document);
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Uncomment to debug the PDF content
        // System.out.println("Extracted PDF Text:");
        // System.out.println(text);
        return text; // Return the extracted text
    }

    public HashMap<String, Double> extractFieldsToMap1(String text) {
        HashMap<String, Double> dataMap1 = new HashMap<>();

        // Define patterns to extract each field
        String[] patterns = {
                "1A Owed to ATO \\$(\\d+,?\\d+\\.\\d{2})",
                "1B Owed by ATO \\$(\\d+,?\\d+\\.\\d{2})",
                "G1 Total sales \\$(\\d+,?\\d+\\.\\d{2})",
                "4 Income tax withheld amount \\$(\\d+,?\\d+\\.\\d{2})",
                "W1 Total salary, wages and other payments \\$(\\d+,?\\d+\\.\\d{2})"
        };

        String[] keys = {"1A", "1B", "G1", "4", "W1"};

        // Extract and store each field in the HashMap
        for (int i = 0; i < patterns.length; i++) {
            Pattern p = Pattern.compile(patterns[i]);
            Matcher m = p.matcher(text);
            if (m.find()) {
                Double value = Double.parseDouble(m.group(1).replace(",", ""));
                dataMap1.put(keys[i], value);
                System.out.println("Extracted " + keys[i] + ": " + value);
            }
        }

        return dataMap1;
    }

    public void clickOnJulyQuarter(HashMap<String, Double> data) throws InterruptedException {
        System.out.println("ATO_JULY_QUARTER Value: " + ATO_JULY_QUARTER);

        if (ATO_JULY_QUARTER == null || ATO_JULY_QUARTER.isBlank()) {
            System.out.println("ATO_JULY_QUARTER is blank or null.");
            return;
        }

        HashMap<String, Double> dataFromStatement1 = goToStatementDetail1();
//        System.out.println("Data from Statement:");
//        System.out.println(dataFromStatement);

        if (dataFromStatement1 == null) {
            dataFromStatement1 = new HashMap<>(); // Initialize if null
        }

        // Combine or override data from PDF with existing statement data
        dataFromStatement1.putAll(data);

        System.out.println("Combined Data:");
        System.out.println(dataFromStatement1);

        if (qd_sept == null) {
            System.out.println("qd_sept is null.");
            return;
        }

        ArrayList<QuaterData> sept_quater_data_row = new ArrayList<>();

        qd_sept.set_G1(dataFromStatement1.get("G1"), true);
        qd_sept.set_1A(dataFromStatement1.get("1A"), true);
        qd_sept.set_1B(dataFromStatement1.get("1B"), true);
        qd_sept.set_W1(dataFromStatement1.get("W1"), true);
        qd_sept.set_4(dataFromStatement1.get("4"), true);
        qd_sept.set_GST_Refund(qd_sept.get_1A() - qd_sept.get_1B(), true);
        qd_sept.set_ATO_Total_Refund((qd_sept.get_GST_Refund() + qd_sept.get_4() + qd_sept.get_5A() - qd_sept.get_7D()), true);
        sept_quater_data_row.add(qd_sept);
        ATO_ROW_DATA.add(sept_quater_data_row);

//        System.out.println("Processed Quarter Data:");
//        System.out.println(sept_quater_data_row);
    }

    private HashMap<String, Double> goToStatementDetail1() {
        // Return some default data or null as needed
        // Example: return some default values for debugging
        HashMap<String, Double> defaultData1 = new HashMap<>();
        defaultData1.put("G1", 0.0);
        defaultData1.put("1A", 0.0);
        defaultData1.put("1B", 0.0);
        defaultData1.put("W1", 0.0);
        defaultData1.put("4", 0.0);
        return defaultData1;
    }
    
    public HashMap<String, Double> extractFieldsToMap2(String text) {
        HashMap<String, Double> dataMap2 = new HashMap<>();

        // Define patterns to extract each field
        String[] patterns = {
                "1A Owed to ATO \\$(\\d+,?\\d+\\.\\d{2})",
                "1B Owed by ATO \\$(\\d+,?\\d+\\.\\d{2})",
                "G1 Total sales \\$(\\d+,?\\d+\\.\\d{2})",
                "4 Income tax withheld amount \\$(\\d+,?\\d+\\.\\d{2})",
                "W1 Total salary, wages and other payments \\$(\\d+,?\\d+\\.\\d{2})"
        };

        String[] keys = {"1A", "1B", "G1", "4", "W1"};

        // Extract and store each field in the HashMap
        for (int i = 0; i < patterns.length; i++) {
            Pattern p = Pattern.compile(patterns[i]);
            Matcher m = p.matcher(text);
            if (m.find()) {
                Double value = Double.parseDouble(m.group(1).replace(",", ""));
                dataMap2.put(keys[i], value);
                System.out.println("Extracted " + keys[i] + ": " + value);
            }
        }

        return dataMap2;
    }

    
    
    public void clickOnOctQuarter(HashMap<String, Double> data) throws InterruptedException {
        System.out.println("ATO_OCT_QUARTER Value: " + ATO_OCT_QUARTER);

        if (ATO_OCT_QUARTER == null || ATO_OCT_QUARTER.isBlank()) {
            System.out.println("ATO_OCT_QUARTER is blank or null.");
            return;
        }

        HashMap<String, Double> dataFromStatement2 = goToStatementDetail2();
//        System.out.println("Data from Statement:");
//        System.out.println(dataFromStatement);

        if (dataFromStatement2 == null) {
        	dataFromStatement2 = new HashMap<>(); // Initialize if null
        }

        // Combine or override data from PDF with existing statement data
        dataFromStatement2.putAll(data);

        System.out.println("Combined Data:");
        System.out.println(dataFromStatement2);

        if (qd_dec == null) {
            System.out.println("qd_dec is null.");
            return;
        }

        ArrayList<QuaterData> dec_quater_data_row = new ArrayList<>();

        qd_dec.set_G1(dataFromStatement2.get("G1"), true);
        qd_dec.set_1A(dataFromStatement2.get("1A"), true);
        qd_dec.set_1B(dataFromStatement2.get("1B"), true);
        qd_dec.set_W1(dataFromStatement2.get("W1"), true);
        qd_dec.set_4(dataFromStatement2.get("4"), true);
        qd_dec.set_GST_Refund(qd_dec.get_1A() - qd_dec.get_1B(), true);
        qd_dec.set_ATO_Total_Refund((qd_dec.get_GST_Refund() + qd_dec.get_4() + qd_dec.get_5A() - qd_dec.get_7D()), true);
        dec_quater_data_row.add(qd_dec);
        ATO_ROW_DATA.add(dec_quater_data_row);

//        System.out.println("Processed Quarter Data:");
//        System.out.println(sept_quater_data_row);
    }

    private HashMap<String, Double> goToStatementDetail2() {
        // Return some default data or null as needed
        // Example: return some default values for debugging
        HashMap<String, Double> defaultData2 = new HashMap<>();
        defaultData2.put("G1", 0.0);
        defaultData2.put("1A", 0.0);
        defaultData2.put("1B", 0.0);
        defaultData2.put("W1", 0.0);
        defaultData2.put("4", 0.0);
        return defaultData2;
    }
    
    public HashMap<String, Double> extractFieldsToMap3(String text) {
        HashMap<String, Double> dataMap3 = new HashMap<>();

        // Define patterns to extract each field
        String[] patterns = {
                "1A Owed to ATO \\$(\\d+,?\\d+\\.\\d{2})",
                "1B Owed by ATO \\$(\\d+,?\\d+\\.\\d{2})",
                "G1 Total sales \\$(\\d+,?\\d+\\.\\d{2})",
                "4 Income tax withheld amount \\$(\\d+,?\\d+\\.\\d{2})",
                "W1 Total salary, wages and other payments \\$(\\d+,?\\d+\\.\\d{2})"
        };

        String[] keys = {"1A", "1B", "G1", "4", "W1"};

        // Extract and store each field in the HashMap
        for (int i = 0; i < patterns.length; i++) {
            Pattern p = Pattern.compile(patterns[i]);
            Matcher m = p.matcher(text);
            if (m.find()) {
                Double value = Double.parseDouble(m.group(1).replace(",", ""));
                dataMap3.put(keys[i], value);
                System.out.println("Extracted " + keys[i] + ": " + value);
            }
        }

        return dataMap3;
    }

    public void clickOnJanQuarter(HashMap<String, Double> data) throws InterruptedException {
        System.out.println("ATO_JAN_QUARTER Value: " + ATO_JAN_QUARTER);

        if (ATO_JAN_QUARTER == null || ATO_JAN_QUARTER.isBlank()) {
            System.out.println("ATO_JAN_QUARTER is blank or null.");
            return;
        }

        HashMap<String, Double> dataFromStatement3 = goToStatementDetail3();
//        System.out.println("Data from Statement:");
//        System.out.println(dataFromStatement);

        if (dataFromStatement3 == null) {
        	dataFromStatement3 = new HashMap<>(); // Initialize if null
        }

        // Combine or override data from PDF with existing statement data
        dataFromStatement3.putAll(data);

        System.out.println("Combined Data:");
        System.out.println(dataFromStatement3);

        if (qd_mar == null) {
            System.out.println("qd_mar is null.");
            return;
        }

        ArrayList<QuaterData> mar_quater_data_row = new ArrayList<>();

        qd_mar.set_G1(dataFromStatement3.get("G1"), true);
        qd_mar.set_1A(dataFromStatement3.get("1A"), true);
        qd_mar.set_1B(dataFromStatement3.get("1B"), true);
        qd_mar.set_W1(dataFromStatement3.get("W1"), true);
        qd_mar.set_4(dataFromStatement3.get("4"), true);
        qd_mar.set_GST_Refund(qd_mar.get_1A() - qd_mar.get_1B(), true);
        qd_mar.set_ATO_Total_Refund((qd_mar.get_GST_Refund() + qd_mar.get_4() + qd_mar.get_5A() - qd_mar.get_7D()), true);
        mar_quater_data_row.add(qd_mar);
        ATO_ROW_DATA.add(mar_quater_data_row);

//        System.out.println("Processed Quarter Data:");
//        System.out.println(sept_quater_data_row);
    }

    private HashMap<String, Double> goToStatementDetail3() {
        // Return some default data or null as needed
        // Example: return some default values for debugging
        HashMap<String, Double> defaultData3 = new HashMap<>();
        defaultData3.put("G1", 0.0);
        defaultData3.put("1A", 0.0);
        defaultData3.put("1B", 0.0);
        defaultData3.put("W1", 0.0);
        defaultData3.put("4", 0.0);
        return defaultData3;
    }
    
    
    public HashMap<String, Double> extractFieldsToMap4(String text) {
        HashMap<String, Double> dataMap4 = new HashMap<>();

        // Define patterns to extract each field
        String[] patterns = {
                "1A Owed to ATO \\$(\\d+,?\\d+\\.\\d{2})",
                "1B Owed by ATO \\$(\\d+,?\\d+\\.\\d{2})",
                "G1 Total sales \\$(\\d+,?\\d+\\.\\d{2})",
                "4 Income tax withheld amount \\$(\\d+,?\\d+\\.\\d{2})",
                "W1 Total salary, wages and other payments \\$(\\d+,?\\d+\\.\\d{2})"
        };

        String[] keys = {"1A", "1B", "G1", "4", "W1"};

        // Extract and store each field in the HashMap
        for (int i = 0; i < patterns.length; i++) {
            Pattern p = Pattern.compile(patterns[i]);
            Matcher m = p.matcher(text);
            if (m.find()) {
                Double value = Double.parseDouble(m.group(1).replace(",", ""));
                dataMap4.put(keys[i], value);
                System.out.println("Extracted " + keys[i] + ": " + value);
            }
        }

        return dataMap4;
    }

    public void clickOnAprQuarter(HashMap<String, Double> data) throws InterruptedException {
        System.out.println("ATO_APR_QUARTER Value: " + ATO_APR_QUARTER);

        if (ATO_APR_QUARTER == null || ATO_APR_QUARTER.isBlank()) {
            System.out.println("ATO_APR_QUARTER is blank or null.");
            return;
        }

        HashMap<String, Double> dataFromStatement4 = goToStatementDetail4();
//        System.out.println("Data from Statement:");
//        System.out.println(dataFromStatement);

        if (dataFromStatement4 == null) {
        	dataFromStatement4 = new HashMap<>(); // Initialize if null
        }
        // Combine or override data from PDF with existing statement data
        dataFromStatement4.putAll(data);

        System.out.println("Combined Data:");
        System.out.println(dataFromStatement4);

        if (qd_jun == null) {
            System.out.println("qd_jun is null.");
            return;
        }

        ArrayList<QuaterData> jun_quater_data_row = new ArrayList<>();

        qd_jun.set_G1(dataFromStatement4.get("G1"), true);
        qd_jun.set_1A(dataFromStatement4.get("1A"), true);
        qd_jun.set_1B(dataFromStatement4.get("1B"), true);
        qd_jun.set_W1(dataFromStatement4.get("W1"), true);
        qd_jun.set_4(dataFromStatement4.get("4"), true);
        qd_jun.set_GST_Refund(qd_jun.get_1A() - qd_jun.get_1B(), true);
        qd_jun.set_ATO_Total_Refund((qd_jun.get_GST_Refund() + qd_jun.get_4() + qd_jun.get_5A() - qd_jun.get_7D()), true);
        jun_quater_data_row.add(qd_jun);
        ATO_ROW_DATA.add(jun_quater_data_row);

//        System.out.println("Processed Quarter Data:");
//        System.out.println(sept_quater_data_row);
    }

    private HashMap<String, Double> goToStatementDetail4() {
        // Return some default data or null as needed
        // Example: return some default values for debugging
        HashMap<String, Double> defaultData4 = new HashMap<>();
        defaultData4.put("G1", 0.0);
        defaultData4.put("1A", 0.0);
        defaultData4.put("1B", 0.0);
        defaultData4.put("W1", 0.0);
        defaultData4.put("4", 0.0);
        return defaultData4;
    }
}
