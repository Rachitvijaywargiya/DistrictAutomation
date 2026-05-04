package org.district.automation.test;

import org.district.automation.base.BaseClass;
import org.district.automation.pages.SportsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_11_NavigationalTests extends BaseClass {
    @Test
    public void testEventsNavigation() {
        SportsPage sp = new SportsPage(driver);
        sp.clickEventsTab();
        sp.openFilters();
        System.out.println("Current URL is: " + driver.getCurrentUrl());
        Assert.assertTrue(driver.getCurrentUrl().contains("events"));
    }
}