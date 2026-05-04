package org.district.automation.test;

import org.district.automation.base.BaseClass;
import org.district.automation.pages.SportsPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_13_GenreAndSportsTest extends BaseClass {

    @Test(description = "Verify Genre menu expansion and Sports checkbox selection")
    public void testGenreAndSportsSelectionFlow() {
        SportsPage sp = new SportsPage(driver);
        sp.clickEventsTab();
        sp.openFilters();
        sp.lowHigh();
        sp.selectGenreSports();
        boolean isMenuVisible = driver.findElement(By.xpath ("//*[contains(@style,'border-radius: 20px 0px 0px 20px')]")).isDisplayed();
        Assert.assertTrue(isMenuVisible, "Genre menu failed to expand.");
        boolean isSelected = sp.isSportsCheckboxSelected();
        Assert.assertTrue(isSelected, "Sports checkbox was not selected correctly.");
        System.out.println("Combined test passed: Genre menu expanded and Sports selected.");
    }
}