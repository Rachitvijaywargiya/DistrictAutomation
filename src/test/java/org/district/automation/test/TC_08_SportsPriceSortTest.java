package org.district.automation.test;

import org.district.automation.base.BaseClass;
import org.district.automation.pages.SportsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

public class TC_08_SportsPriceSortTest extends BaseClass {

    @Test
    public void verifySportsSortedByLowestPrice() throws Exception {
        log.info("TC_13 started: Verify Sports Activities Sorted by Lowest Charges");
        SportsPage sportsPage = new SportsPage(driver);

        log.info("Navigating to Events page");
        sportsPage.clickEventsTab();
        log.info("Scrolling till All events section");
        sportsPage.scrollToAllEvents();
        log.info("Opening Filters and selecting Sports genre");
        sportsPage.openFilters();
        sportsPage.selectSportsGenre();
        sportsPage.applyFilters();
        log.info("Clicking This Weekend filter");
        sportsPage.clickThisWeekend();
        log.info("Applying Price: Low to High sorting");
        sportsPage.applyLowToHighSort();
        sportsPage.applyFilters();

        List<Integer> uiPrices = sportsPage.getDisplayedPrices();
        log.info("Prices retrieved from UI: {}", uiPrices);
        Assert.assertTrue(
                !uiPrices.isEmpty(),
                "No sports events available to validate price sorting"
        );

        int lowestPrice = Collections.min(uiPrices);
        List<Integer> topPrices =
                uiPrices.subList(0, Math.min(3, uiPrices.size()));

        Assert.assertTrue(
                topPrices.contains(lowestPrice),
                "Lowest priced sports activity is not shown in top results"
        );

        log.info("TC_13 completed successfully: Lowest priced sports activity is prominently displayed");
    }
}