package org.district.automation.test;

import org.district.automation.base.BaseClass;
import org.district.automation.pages.SportsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TC_06_SportsWeekendTest extends BaseClass {

    @Test
    public void verifySportsWeekendEvents() throws Exception {

        log.info("TC_11 started: Verify Sports Activities for Coming Weekend");

        SportsPage sportsPage = new SportsPage(driver);

        log.info("Clicking on Events tab");
        sportsPage.clickEventsTab();

        log.info("Scrolling till All events section");
        sportsPage.scrollToAllEvents();

        log.info("Opening Filters and selecting Sports genre");
        sportsPage.openFilters();
        sportsPage.selectSportsGenre();
        sportsPage.applyFilters();

        log.info("Clicking This Weekend filter");
        sportsPage.clickThisWeekend();

        // ✅ ADDITION: Print Sports Events Details
        log.info("Printing Sports Events Details for Coming Weekend");

        List<String> names = sportsPage.getAllEventNames();
        List<String> dates = sportsPage.getAllEventDates();
        List<Integer> prices = sportsPage.getDisplayedPrices();

        int size = Math.min(names.size(), Math.min(dates.size(), prices.size()));

        for (int i = 0; i < size; i++) {
            log.info(
                    "Event " + (i + 1) +
                            " | Name: " + names.get(i) +
                            " | Date: " + dates.get(i) +
                            " | Price: ₹" + prices.get(i)
            );
        }

        int eventCount = sportsPage.getAllEventDates().size();
        log.info("Number of sports events found for weekend: " + eventCount);

        Assert.assertTrue(
                eventCount > 0,
                "No sports activities found for the coming weekend"
        );

        log.info("TC_11 completed successfully");
    }
}