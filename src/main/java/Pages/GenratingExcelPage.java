package Pages;

import java.util.ArrayList;
import java.util.HashMap;

import com.asis.Excel;
import com.asis.QuaterData;
import com.asis.util.BaseClass;

public class GenratingExcelPage extends BaseClass {
	
	public void getXeroData() {
		ArrayList<QuaterData> xero_data = new ArrayList<>();
		QuaterData xeroObj = new QuaterData("As per the book");
		xeroObj.set_G1(Double.parseDouble(fetchCaptureA1G1B1Data.get(0)),false);
		xeroObj.set_1A(Double.parseDouble(fetchCaptureA1G1B1Data.get(1)),false);
		xeroObj.set_1B(Double.parseDouble(fetchCaptureA1G1B1Data.get(2)),false);
		xeroObj.set_W1(0,false);
		xeroObj.set_4(0,false);
		xeroObj.set_GST_Refund(xeroObj.get_1A() - xeroObj.get_1B(),false);
		xeroObj.set_ATO_Total_Refund(xeroObj.get_GST_Refund() + xeroObj.get_4() + xeroObj.get_5A() - xeroObj.get_7D(),false);
		xero_data.add(xeroObj);
		XERO_DATA.add(xero_data);

		ArrayList<QuaterData> variance_data = new ArrayList<>();
		QuaterData variance = new QuaterData("Variance");
		variance.set_G1(QuaterData.getTotal_of_year_G1() - xeroObj.get_G1(),false);
		variance.set_1A(QuaterData.getTotal_of_year_1A() - xeroObj.get_1A(),false);
		variance.set_1B(QuaterData.getTotal_of_year_1B() - xeroObj.get_1B(),false);
		variance.set_W1(QuaterData.getTotal_of_year_W1() - xeroObj.get_W1(),false);
		variance.set_4(QuaterData.getTotal_of_year_4() - xeroObj.get_4(),false);
		variance.set_GST_Refund(variance.get_1A() - variance.get_1B(),false);
		variance.set_ATO_Total_Refund(variance.get_GST_Refund() + variance.get_4() + variance.get_5A() - variance.get_7D(),false);
		variance_data.add(variance);
		XERO_DATA.add(variance_data);	

		HashMap<String, Double> hm7 = new HashMap<>();
		hm7.put("Reason for Variance:", 0.0);
		LAST_TABLE_DATA.add(hm7);

		HashMap<String, Double> hm8 = new HashMap<>();
		hm8.put("Reporting variance", variance.get_GST_Refund());
		LAST_TABLE_DATA.add(hm8);

		HashMap<String, Double> hm9 = new HashMap<>();
		hm9.put("Unknown variance",	LAST_TABLE_DATA.get(5).get("Total - GST as per balance sheet")+
		LAST_TABLE_DATA.get(6).get("Reason for Variance:")+
		LAST_TABLE_DATA.get(7).get("Reporting variance"));
		LAST_TABLE_DATA.add(hm9);


		ArrayList<QuaterData> bas_relodged_data = new ArrayList<>();
		QuaterData bas_relodged = new QuaterData("BAS to be relodged for Period ended Jun 23");
		bas_relodged.set_G1(qd_jun.get_G1() - variance.get_G1(),false);
		bas_relodged.set_1A(qd_jun.get_1A() - variance.get_1A(),false);
		bas_relodged.set_1B(qd_jun.get_1B() - variance.get_1B(),false);
		bas_relodged.set_W1(qd_jun.get_W1() - variance.get_W1(),false);
		bas_relodged.set_4(qd_jun.get_4() - variance.get_4(),false);
		bas_relodged.set_GST_Refund(qd_jun.get_GST_Refund() - variance.get_GST_Refund(),false);
		bas_relodged.set_ATO_Total_Refund(bas_relodged.get_GST_Refund() + bas_relodged.get_4() + bas_relodged.get_5A() - bas_relodged.get_7D(),false);
		bas_relodged_data.add(bas_relodged);
		XERO_DATA.add(bas_relodged_data);	
	}

	public void generateExcel() {
		String[] client_data = {BaseClass.CLIENT_DATA.get("client_name").trim(), BaseClass.CLIENT_DATA.get("to_date").trim()};
		Excel obj = new Excel();
		obj.createFinancialSummaryExcelWithData("Final_data.xls", BaseClass.ATO_ROW_DATA, BaseClass.XERO_DATA, BaseClass.ACTIVITY_STATEMENT_DATA,client_data);
	}
}
