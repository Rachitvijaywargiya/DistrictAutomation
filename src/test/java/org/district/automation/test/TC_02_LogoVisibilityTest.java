package org.district.automation.test;

import org.district.automation.base.BaseClass;
import org.district.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_02_LogoVisibilityTest extends BaseClass {

    @Test
    public void verifyLogoIsDisplayed() {
        HomePage home = new HomePage(driver);
        Assert.assertTrue(home.isLogoDisplayed(),
                "District logo is not visible");
    }
}