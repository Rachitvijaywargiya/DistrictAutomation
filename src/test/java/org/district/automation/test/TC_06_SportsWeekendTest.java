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
        log.info("Printing Sports Events Details for Coming Weekend");
        sportsPage.printAllWeekendSportsEvents();
        int eventCount = sportsPage.getAllEventDates().size();
        log.info("Number of sports events found for weekend: {}", eventCount);

        Assert.assertTrue(
                eventCount > 0,
                "No sports activities found for the coming weekend"
        );

        log.info("TC_11 completed successfully");
    }
}