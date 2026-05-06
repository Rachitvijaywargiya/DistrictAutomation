package org.district.automation.test;

import org.district.automation.base.BaseClass;
import org.district.automation.pages.SportsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;

public class TC_12_FilterPanelVisibilityTest extends BaseClass {

    @Test
    public void testFilterPanelVisibility() {
        SportsPage sp = new SportsPage(driver);

        sp.clickEventsTab();
        sp.openFilters();

        Assert.assertTrue(sp.isFilterPanelVisible(), "Filter panel not visible.");
    }
}
