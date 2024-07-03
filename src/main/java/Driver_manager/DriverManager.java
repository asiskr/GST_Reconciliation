package Driver_manager;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverManager {
	
	private static WebDriver driver;
	
	public static void setDriver(String browser) throws MalformedURLException {
	    if(browser.equals("Chrome")) {
	        System.setProperty("webdriver.chrome.driver","/usr/bin/chromedriver");
	        ChromeOptions options = new ChromeOptions();
	        // options.setBinary("/usr/bin/google-chrome");
	        options.addArguments("--headless=new");
	        options.addArguments("--no-sandbox");
	        // options.addArguments("--disable-gpu");

	        driver = new RemoteWebDriver(new URL("http://10.122.0.2:4444/wd/hub"), options);
	    } else {
	        System.setProperty("webdriver.gecko.driver","/usr/bin/geckodriver");
	        FirefoxOptions options = new FirefoxOptions();
	        options.addArguments("--headless=new");
	        options.addArguments("--no-sandbox");
//	        options.addArguments("--disable-gpu");
	        driver = new RemoteWebDriver(new URL("http://10.122.0.2:4444/wd/hub"), options);
	    }
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    driver.manage().window().maximize();
	}

	
	public static WebDriver getDriver() {
		return driver;
	}
}
