package org.district.automation.test;

import org.district.automation.base.BaseClass;
import org.district.automation.pages.HomePage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TC_05_SelectCityTest extends BaseClass {

    @Test
    public void verifyUserCanChangeCity() {

        HomePage home = new HomePage(driver);

        home.selectCity("Pune");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(home.getLocationTextElement()));

        String location = home.getDisplayedLocation();

        Assert.assertTrue(
                location.contains("Pune"),
                "City was not changed to Pune. Actual location: " + location
        );
    }
}