package Pages;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.asis.util.BaseClass;
import com.google.common.io.Files;

import Driver_manager.DriverManager;

import java.io.File;
import java.io.IOException;

public class Screenshot extends BaseClass {

    public Screenshot() {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    public byte[] captureAndConvertScreenshot() {
        WebDriver driver = DriverManager.getDriver();
        // Take screenshot as bytes
        byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        return screenshotBytes;
    }
}
