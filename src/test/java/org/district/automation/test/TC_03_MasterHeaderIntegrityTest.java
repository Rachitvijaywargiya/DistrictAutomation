package org.district.automation.test;

import org.district.automation.base.BaseClass;
import org.district.automation.pages.HomePage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TC_03_MasterHeaderIntegrityTest extends BaseClass {

    @Test
    public void verifyMasterHeaderAndNavigationTabs() {
        log.info("Verifying master header integrity on home page");
        HomePage home = new HomePage(driver);
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(
                driver.getPageSource().contains("master-header"),
                "Master header is not present on the page"
        );

        log.info("Master header is present");
        String location = home.getDisplayedLocation();
        log.info("Displayed location in header: {}", location);

        softAssert.assertNotNull(
                location,
                "Location text is missing in header"
        );
        softAssert.assertFalse(
                location.trim().isEmpty(),
                "Location text is empty in header"
        );
        softAssert.assertTrue(
                driver.getPageSource().contains("For you"),
                "'For you' tab is missing in master header"
        );
        softAssert.assertTrue(
                driver.getPageSource().contains("Movies"),
                "'Movies' tab is missing in master header"
        );
        softAssert.assertTrue(
                driver.getPageSource().contains("Events"),
                "'Events' tab is missing in master header"
        );
        log.info("Master header navigation tabs are present");

        softAssert.assertAll();
    }
}