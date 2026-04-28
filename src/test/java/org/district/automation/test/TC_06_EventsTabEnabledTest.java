package org.district.automation.test;

import org.district.automation.base.BaseClass;
import org.district.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_06_EventsTabEnabledTest extends BaseClass {

    @Test
    public void verifyEventsTabEnabled() {
        HomePage home = new HomePage(driver);
        Assert.assertTrue(home.isEventsTabEnabled(),
                "Events tab is not enabled");
    }
}