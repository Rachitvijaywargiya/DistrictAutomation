package org.district.automation.test;
import org.district.automation.base.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TC_01_HomePageLoadTest extends BaseClass {

    @Test
    public void verifyHomePageLoadsCorrectly() {
        log.info("Verifying that District home page loads correctly");
        SoftAssert softAssert = new SoftAssert();
        String currentUrl = driver.getCurrentUrl();
        log.info("Current URL: " + currentUrl);
        Assert.assertTrue(
                currentUrl.contains("district.in"),
                "User did not land on District home page"
        );
        String title = driver.getTitle();
        log.info("Page title: " + title);
        softAssert.assertTrue(
                title.toLowerCase().contains("district"),
                "Page title does not contain application name"
        );
        log.info("Verifying presence of master header in page source");
        softAssert.assertTrue(
                driver.getPageSource().contains("master-header"),
                "Home page header not loaded properly"
        );
        softAssert.assertAll();
    }
}
