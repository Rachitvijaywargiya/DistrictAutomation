package org.district.automation.test;

import org.district.automation.base.BaseClass;
import org.district.automation.pages.SportsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_15_FilterResetTest extends BaseClass {

    @Test(description = "Verify filtering for Sports events and its persistence after refresh")
    public void testSportsFilterFlowAndPersistence() {
        SportsPage sp = new SportsPage(driver);
        sp.clickEventsTab();
        sp.openFilters();
        sp.lowHigh();
        sp.selectGenreSports();
        sp.applyFilters();
        boolean ele1 = sp.displayed();
        driver.navigate().refresh();
        sp.openFilters();
        sp.clearAll();
        sp.openFilters();
        sp.lowHigh();
        sp.selectGenre();
        Assert.assertFalse(sp.isSportsCheckboxSelected(), "Checkbox should have been cleared.");
        Assert.assertTrue(ele1, "No events found after applying filters.");
    }
}