package org.district.automation.test;

import org.district.automation.base.BaseClass;
import org.district.automation.pages.SportsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TC_09_NoPastSportsEventsTest extends BaseClass {

    @Test
    public void verifyNoPastDatedSportsEvents() throws Exception {
        log.info("TC_14 started: Verify No Past-Dated Sports Events");
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

        List<String> eventDates = sportsPage.getAllEventDates();
        log.info("Event dates retrieved from UI: {}", eventDates);
        Assert.assertTrue(
                !eventDates.isEmpty(),
                "No sports events found to validate dates"
        );
        for (String date : eventDates) {
            Assert.assertFalse(
                    date.contains("yesterday"),
                    "Past-dated sports event found: " + date
            );
        }

        log.info("TC_14 completed successfully: No past-dated sports events displayed");
    }
}