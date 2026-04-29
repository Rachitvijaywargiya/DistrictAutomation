package org.district.automation.test;
import org.district.automation.base.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TC_01_HomePageLoadTest extends BaseClass {

    @Test
    public void verifyHomePageLoadsCorrectly() {
        Assert.assertTrue(
                driver.getCurrentUrl().contains("district.in"),
                "User did not land on District home page"
        );
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(
                driver.getTitle().toLowerCase().contains("district"),
                "Page title does not contain application name"
        );
        softAssert.assertTrue(
                driver.getPageSource().contains("master-header"),
                "Home page header not loaded properly"
        );
        softAssert.assertAll();
    }
}
