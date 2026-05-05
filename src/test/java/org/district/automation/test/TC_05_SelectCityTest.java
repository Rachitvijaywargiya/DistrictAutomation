package org.district.automation.test;

import org.district.automation.base.BaseClass;
import org.district.automation.pages.HomePage;
import org.district.automation.utility.ConfigReader;
import org.district.automation.utility.ExcelUtil;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TC_05_SelectCityTest extends BaseClass {

    @Test
    public void verifyUserCanChangeCity() {

        HomePage home = new HomePage(driver);

//        String city = ConfigReader.getString("city");
//        String entireLocation = ConfigReader.getString("entireLocation");
//        String state = ConfigReader.getString("state");
        String city = ExcelUtil.getCellData("LocationData", 1, 1);
        String state = ExcelUtil.getCellData("LocationData", 1, 2);
        String entireLocation = ExcelUtil.getCellData("LocationData", 1, 3);

        home.clickLocation();
        log.info("Clicked on location icon");

        home.selectLocationcity(city);
        home.selectPuneLoc();
        log.info("Selected location: {}", home.getTheNameOfSelectedCity());

        String currentLoc = home.getLocAfterClick(city);
        String currentEntireLoc = home.getEntireLocAfterClick(state);
        log.info("Selected entire location: {}", currentEntireLoc);

        SoftAssert soft = new SoftAssert();
        soft.assertEquals(currentLoc,city);
        soft.assertAll();

        currentEntireLoc = home.getEntireLocAfterClick(state);
        Assert.assertTrue(currentEntireLoc.contains(state));
        Assert.assertEquals(currentEntireLoc,entireLocation);

    }
}