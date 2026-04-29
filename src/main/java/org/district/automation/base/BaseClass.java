package org.district.automation.base;

import org.district.automation.utility.ScreenshotUtil;
import org.district.automation.utility.Utility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public class BaseClass {
    public WebDriver driver;

    @BeforeMethod
    public void setUp() throws Exception {
        if(Utility.fetchPropertyValue("browser").equals("chrome")) {
            ChromeOptions chromeOptions = new ChromeOptions();
            Map<String, Object> prefs = new HashMap<>();
            prefs.put("profile.default_content_setting_values.geolocation", 2); // 1=Allow, 2=Block
            prefs.put("profile.managed_default_content_settings.geolocation", 2);

            chromeOptions.setExperimentalOption("prefs", prefs);
            chromeOptions.addArguments("--disable-geolocation");
            chromeOptions.addArguments("--start-maximized");
            chromeOptions.addArguments("--disable-notifications");
            chromeOptions.addArguments("--disable-popup-blocking");
            //chromeOptions.addArguments("--headless=new");
            driver = new ChromeDriver(chromeOptions);
        }else if(Utility.fetchPropertyValue("browser").equals("firefox")){
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
        }
        else if(Utility.fetchPropertyValue("browser").equals("edge")){
            EdgeOptions edgeOptions = new EdgeOptions();
            edgeOptions.addArguments("--start-maximized");
            edgeOptions.addArguments("--disable-notifications");
            edgeOptions.addArguments("--disable-popup-blocking");
            //edgeOptions.addArguments("--headless=new");
            driver = new EdgeDriver(edgeOptions);
            driver.manage().window().maximize();
        }

        System.out.println("Opening on selected browser " + Utility.fetchPropertyValue("browser"));
        driver.get(Utility.fetchPropertyValue("baseUrl").toString());
        System.out.println("Running on URL " + Utility.fetchPropertyValue("baseUrl").toString());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        try {
            //code for SS and Result ITestResult
            if (driver != null) {
                String testName = result.getMethod().getMethodName();
                if (result.getStatus() == ITestResult.FAILURE) {
                    ScreenshotUtil.takeScreenshot(driver, testName + "_FAILED");
                    System.out.println("Screenshot taken Successfully but test case failed");
                } else {
                    ScreenshotUtil.takeScreenshot(driver, testName + "_PASSED");
                    System.out.println("Screenshot taken Successfully and test case passed");
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            if (driver != null) {
                System.out.println("Closing the browser");
                driver.quit();
            }
        }
    }
}
