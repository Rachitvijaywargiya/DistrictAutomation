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

        String city = home.getLocAfterClick();
        String fullLocation = home.getEntireLocAfterClick();
        log.info("City: {}", city);
        log.info("Full Location: {}", fullLocation);

        SoftAssert soft = new SoftAssert();
        soft.assertEquals(city, "Pune");
        soft.assertTrue(fullLocation.contains("Maharashtra"));
        soft.assertAll();
    }
}