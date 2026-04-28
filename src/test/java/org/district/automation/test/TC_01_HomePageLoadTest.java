package org.district.automation.test;

import org.district.automation.base.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_01_HomePageLoadTest extends BaseClass {

    @Test
    public void verifyHomePageLoadsSuccessfully() {
        String title = driver.getTitle();
        Assert.assertTrue(title.contains("District"),
                "Home page title is incorrect");
    }
}
