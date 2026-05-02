package org.district.automation.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.district.automation.utility.ConfigReader;
import org.district.automation.utility.ScreenshotUtil;
import org.district.automation.utility.Utility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.net.InetAddress;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.SkipException;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;


public class BaseClass {
    public WebDriver driver;
    protected static final Logger log = LogManager.getLogger(BaseClass.class);

    private boolean hasInternetConnection() {
        try {
            return InetAddress.getByName("8.8.8.8").isReachable(2000);
        } catch (Exception e) {
            return false;
        }
    }

    @BeforeMethod
    public void setUp() throws Exception {


    // PRE-CHECK INTERNET
        if (!hasInternetConnection()) {
            log.error("No internet connection detected. Skipping test execution.");
            throw new SkipException("Skipped due to no internet connection");
        }

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

            if (ConfigReader.getBoolean("headless")) {
                options.addArguments("--headless");
            }

            driver = new ChromeDriver(options);
        }else if(Utility.fetchPropertyValue("browser").equals("firefox")){
            FirefoxOptions firefoxOptions = new FirefoxOptions();

            // Disable geolocation
            firefoxOptions.addPreference("geo.enabled", false);
            firefoxOptions.addPreference("geo.provider.use_corelocation", false);
            firefoxOptions.addPreference("geo.prompt.testing", false);
            firefoxOptions.addPreference("geo.prompt.testing.allow", false);

            // Disable notifications
            firefoxOptions.addPreference("dom.webnotifications.enabled", false);
            firefoxOptions.addPreference("dom.push.enabled", false);

            if (ConfigReader.getBoolean("headless")) {
                firefoxOptions.addArguments("--headless");
            }

            driver = new FirefoxDriver();
            driver.manage().window().maximize();
        }
        else if(Utility.fetchPropertyValue("browser").equals("edge")){
            EdgeOptions edgeOptions = new EdgeOptions();

            Map<String, Object> prefs = new HashMap<>();
            prefs.put("profile.default_content_setting_values.geolocation", 2);
            prefs.put("profile.managed_default_content_settings.geolocation", 2);

            edgeOptions.setExperimentalOption("prefs", prefs);
            edgeOptions.addArguments("--disable-geolocation");
            edgeOptions.addArguments("--start-maximized");
            edgeOptions.addArguments("--disable-notifications");
            edgeOptions.addArguments("--disable-popup-blocking");

            if (ConfigReader.getBoolean("headless")) {
                edgeOptions.addArguments("--headless");
            }

            driver = new EdgeDriver(edgeOptions);
        }

        log.info("Opening on selected browser {}", Utility.fetchPropertyValue("browser"));

        try {
            driver.get(Utility.fetchPropertyValue("baseUrl").toString());
            log.info("Running on URL {}", Utility.fetchPropertyValue("baseUrl").toString());

        } catch (WebDriverException e) {
            if (e.getMessage().contains("ERR_INTERNET_DISCONNECTED")) {
                log.error("Internet disconnected while loading URL: {}",
                        Utility.fetchPropertyValue("baseUrl"));
                driver.quit();
                throw new SkipException("Skipped due to internet disconnection");
            }
            throw e;
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        try {
            //code for SS and Result ITestResult
            if (driver != null) {
                String testName = result.getTestClass()
                        .getRealClass()
                        .getSimpleName();

                if (result.getStatus() == ITestResult.FAILURE) {
                    ScreenshotUtil.takeScreenshot(driver, testName + "_FAILED");
                    log.error("Test case failed.");
                } else if (result.getStatus() == ITestResult.SUCCESS) {
                    ScreenshotUtil.takeScreenshot(driver, testName + "_PASSED");
                    log.info("Test case passed.");
                    //log.info("Screenshot captured for test: {}", testName);
                }
                log.info("Screenshot captured for test: {}", testName);
            }
        } catch (Exception e) {
            log.error("Error while taking screenshot: {}", e.getMessage());
        } finally {
            if (driver != null) {
                log.info("Closing the browser");
                driver.quit();
                driver = null;
            }
        }
    }
}
