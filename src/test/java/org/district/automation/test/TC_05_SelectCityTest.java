package org.district.automation.test;

import org.district.automation.base.BaseClass;
import org.district.automation.pages.HomePage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class TC_05_SelectCityTest extends BaseClass {

    @Test
    public void verifyUserCanChangeCity() {

        HomePage home = new HomePage(driver);
        home.clickLocation();
        home.selectLocationcity("Pune");
        home.selectPuneLoc();
        String currentLoc = home.getLocAfterClick();
        String currentEntireLoc = home.getEntireLocAfterClick();

        SoftAssert soft = new SoftAssert();
        soft.assertEquals(currentLoc,"Pune");
        soft.assertAll();

        Assert.assertEquals(currentEntireLoc,"Pune, Maharashtra");
        Assert.assertTrue(currentEntireLoc.contains("Maharashtra"));

    }
}