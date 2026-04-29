package org.district.automation.test;

import org.district.automation.base.BaseClass;
import org.district.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TC_03_CitySelectorInteractionTest extends BaseClass {

    @Test
    public void verifyCitySelectorOpensLocationSelectionFlow() {
        HomePage home = new HomePage(driver);
        Assert.assertTrue(
                home.isCitySelectorDisplayed(),
                "City selector button is not visible on home page"
        );
        SoftAssert softAssert = new SoftAssert();
        home.selectCity("Pune");
        String locationAfterSelection = home.getDisplayedLocation();
        softAssert.assertNotNull(
                locationAfterSelection,
                "Location became null after city selector interaction"
        );
        softAssert.assertFalse(
                locationAfterSelection.isEmpty(),
                "Location text is empty after city selector interaction"
        );
        softAssert.assertAll();
    }
}