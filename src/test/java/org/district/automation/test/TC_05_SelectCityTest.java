package org.district.automation.test;

import org.district.automation.base.BaseClass;
import org.district.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TC_05_SelectCityTest extends BaseClass {

    @Test
    public void verifyUserCanChangeCity() {
        HomePage home = new HomePage(driver);
        home.clickLocation();
        log.info("Clicked on location icon");
        home.selectLocationcity("Pune");
        home.selectPuneLoc();
        log.info("Selected location: " + home.getTheNameOfSelectedCity());
        String currentLoc = home.getLocAfterClick();
        String currentEntireLoc = home.getEntireLocAfterClick();
        log.info(currentEntireLoc);
        SoftAssert soft = new SoftAssert();
        soft.assertEquals(currentLoc,"Pune");
        soft.assertAll();
        Assert.assertEquals(currentEntireLoc,"Pune, Maharashtra");
        currentEntireLoc = home.getEntireLocAfterClick();
        Assert.assertTrue(currentEntireLoc.contains("Maharashtra"));

    }
}