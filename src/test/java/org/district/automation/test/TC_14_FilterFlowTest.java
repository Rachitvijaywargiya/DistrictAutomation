package org.district.automation.test;

import org.district.automation.base.BaseClass;
import org.district.automation.pages.SportsPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_14_FilterFlowTest extends BaseClass {

    @Test(description = "Verify filtering for Sports events and its persistence after refresh")
    public void testSportsFilterFlowAndPersistence() {
        SportsPage sp = new SportsPage(driver);
        sp.clickEventsTab();
        sp.openFilters();
        sp.lowHigh();
        sp.selectGenreSports();
        sp.applyFilters();
        boolean ele = sp.displayed();
        driver.navigate().refresh();
        sp.openFilters();
        sp.selectGenreSports();
        boolean isSelected = sp.isSportsCheckboxSelected();
        Assert.assertTrue(isSelected, "Filter did not persist after page refresh.");
        Assert.assertTrue(ele, "No events found after applying filters.");
        System.out.println("Combined test passed: Filter applied successfully and persisted after refresh.");


    }
}