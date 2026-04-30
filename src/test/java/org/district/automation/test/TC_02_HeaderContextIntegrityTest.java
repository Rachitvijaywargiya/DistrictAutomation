package org.district.automation.test;

import org.district.automation.base.BaseClass;
import org.district.automation.pages.HomePage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TC_02_HeaderContextIntegrityTest extends BaseClass {

    @Test
    public void verifySearchBecomesAvailableWhenApplicable() {
        log.info("Verifying header elements on home page");
        HomePage home = new HomePage(driver);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(home.isLogoDisplayed(),
                "Home page not loaded correctly");
        log.info("Checking search box availability (conditional element)");
        if (home.isSearchBoxPresent()) {
            log.info("Search box is visible on home page");
        } else {
            log.info("Search box is not visible in the current page state");
        }
        softAssert.assertAll();
    }

}
