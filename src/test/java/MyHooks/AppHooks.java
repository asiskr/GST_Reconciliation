package MyHooks;

import java.io.IOException;

import com.asis.Excel;
import com.asis.util.BaseClass;
import com.asis.util.ExcelUtil;
import com.asis.util.xeroexcel;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;

public class AppHooks extends BaseClass{
	
	
	@Before("@readATOExcel")
	public void readATOExcel() {
		System.out.println("Reading ato excel ");
		setProperties();
		ExcelUtil.readExcel(ATO_FILE_PATH,ATO_FILE_NAME);
		CLIENT_DATA = ExcelUtil.getClientDetail(ATO_CLIENT_SHEET_NAME);
		System.out.println(CLIENT_DATA);
		try {
			ExcelUtil.closeExcel();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Before("@readXEROExcel")
	public void readXEROExcel() {
		System.out.println("Reading xero excel ");
		xeroexcel.readExcel(XERO_FILE_PATH,XERO_FILE_NAME);
		CLIENT_XERO_DATA = xeroexcel.getQuestAnsw(XERO_LOGIN_SHEET_NAME);
		System.out.println(CLIENT_XERO_DATA);
		try {
			xeroexcel.closeExcel();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Before
	public void beforeScenario(Scenario scenario) {
		System.out.println("Started scenario -"+scenario.getName());		
	}
	
	@Before("@add30SecondWait")
	public void beforeScenariowait30Second() {
		System.out.println("Running after each scenario");
		try {
			Thread.sleep(25000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@After
	public void afterScenariowait(Scenario scenario) {
		System.out.println("Eneded scenario - "+scenario.getName());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@BeforeStep
	public void waitBeforeEachStep() {
		System.out.println("Running before each step");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
