package org.district.automation.base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.BeforeClass;

import java.time.Duration;

public class BaseClass {

    protected WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = new EdgeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--start-maximized");
        driver.get("https://www.district.in/");
    }

//    @AfterClass
//    public void tearDown() {
//        driver.quit();
//    }
}
