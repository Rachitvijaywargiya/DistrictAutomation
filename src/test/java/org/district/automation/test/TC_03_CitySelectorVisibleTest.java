package org.district.automation.test;

import org.district.automation.base.BaseClass;
import org.district.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_03_CitySelectorVisibleTest extends BaseClass {

    @Test
    public void verifyCitySelectorIsDisplayed() {
        HomePage home = new HomePage(driver);
        Assert.assertTrue(home.isCitySelectorDisplayed(),
                "City selector button is not displayed");
    }
}