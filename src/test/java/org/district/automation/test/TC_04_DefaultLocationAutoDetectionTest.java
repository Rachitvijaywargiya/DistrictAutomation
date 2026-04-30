package org.district.automation.test;
import org.district.automation.base.BaseClass;
import org.district.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TC_04_DefaultLocationAutoDetectionTest extends BaseClass {

    @Test
    public void verifyAutoDetectedLocationIsResolvedAndUsable() {

        HomePage home = new HomePage(driver);
        log.info("Fetching auto-detected location on page load");
        String location = home.getDisplayedLocation();
        log.info("Auto-detected location is: " + location);
        Assert.assertNotNull(location, "Auto-detected location is null");

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertFalse(
                location.trim().isEmpty(),
                "Auto-detected location is empty"
        );
        softAssert.assertFalse(
                location.equalsIgnoreCase("Select Location"),
                "Location placeholder is still displayed"
        );
        softAssert.assertTrue(
                location.length() > 5,
                "Resolved location is too short to be meaningful"
        );

        softAssert.assertAll();
    }
}