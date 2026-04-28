package org.district.automation.test;

import org.district.automation.base.BaseClass;
import org.district.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_04_DefaultCityDisplayedTest extends BaseClass {

    @Test
    public void verifyDefaultCityDisplayed() {

        HomePage home = new HomePage(driver);

        String location = home.getDisplayedLocation();

        Assert.assertTrue(
                location != null && location.length() > 0,
                "Default location is not displayed"
        );
    }
}