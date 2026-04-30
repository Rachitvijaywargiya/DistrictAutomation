package org.district.automation.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    protected static final Logger log = LogManager.getLogger(BaseClass.class);

    @BeforeMethod
    public void setUp() throws Exception {
        if(Utility.fetchPropertyValue("browser").equals("chrome")) {
            ChromeOptions options = new ChromeOptions();

            Map<String, Object> prefs = new HashMap<>();
            prefs.put("profile.default_content_setting_values.geolocation", 2); // 1=Allow, 2=Block
            prefs.put("profile.managed_default_content_settings.geolocation", 2);

            options.setExperimentalOption("prefs", prefs);
            options.addArguments("--disable-geolocation");
            options.addArguments("--start-maximized");
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-popup-blocking");
            //chromeOptions.addArguments("--headless=new");

            driver = new ChromeDriver(options);
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

        log.info("Opening on selected browser {}", Utility.fetchPropertyValue("browser"));
        driver.get(Utility.fetchPropertyValue("baseUrl").toString());
        log.info("Running on URL {}", Utility.fetchPropertyValue("baseUrl").toString());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        try {
            //code for SS and Result ITestResult
            if (driver != null) {
                String testName = result.getMethod().getMethodName();
                testName =result.getTestClass().getRealClass().getSimpleName();
                if (result.getStatus() == ITestResult.FAILURE) {
                    ScreenshotUtil.takeScreenshot(driver, testName + "_FAILED");
                    log.error("Test case failed.");
                    //log.info("Screenshot captured for test: {}", testName);
                } else {
                    ScreenshotUtil.takeScreenshot(driver, testName + "_PASSED");
                    log.info("Test case passed.");
                    //log.info("Screenshot captured for test: {}", testName);
                }
                log.info("Screenshot captured for test: {}", testName);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            if (driver != null) {
                log.info("Closing the browser");
                driver.quit();
            }
        }
    }
}
