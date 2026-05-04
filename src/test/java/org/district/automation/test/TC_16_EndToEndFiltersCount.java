package org.district.automation.test;

import org.district.automation.base.BaseClass;
import org.district.automation.pages.SportsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_16_EndToEndFiltersCount extends BaseClass {
    @Test
    public void testE2EFilterFlow() {
        SportsPage sp = new SportsPage(driver);
        sp.clickEventsTab();
        sp.openFilters();
        sp.lowHigh();
        sp.selectGenreSports();
        sp.applyFilters(); // Act
        int count = sp.endToEnd();
        Assert.assertTrue(count > 0, "Filter applied but no events shown."); // Assert
        System.out.println(count);
    }
}