package Driver_manager;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverManager {
	
	public static WebDriver driver;
	
	public static void setDriver(String browser) throws MalformedURLException {
		if(browser == "Chrome") {
			ChromeOptions options = new ChromeOptions();
			
			options.addArguments("--headless=new");
			options.addArguments("--no-sandbox");
//			options.addArguments("--disable-gpu");
			WebDriver driver = new RemoteWebDriver(new URL("http://10.122.0.2:5555/wd/hub"),options);
			
		}else {
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("--headless");
			options.addArguments("--disable-gpu");
			driver = new FirefoxDriver(options);
		}
	}
	
	public static WebDriver getDriver() {
		return driver;
	}
}
