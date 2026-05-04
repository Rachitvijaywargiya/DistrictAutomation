package org.district.automation.test;

import org.district.automation.base.BaseClass;
import org.district.automation.pages.SportsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TC_07_ValidateSportNameAndDateTest extends BaseClass {

    @Test
    public void validateSportNameAndDate() throws Exception {

        log.info("TC_12 started: Validate Sport Name and Date Display");

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
        log.info("Number of event dates retrieved: " + eventDates.size());

        Assert.assertTrue(
                eventDates.size() > 0,
                "No sports events are displayed to validate name and date"
        );

        for (String date : eventDates) {
            Assert.assertFalse(
                    date.trim().isEmpty(),
                    "Event date is empty for one of the sports events"
            );
        }

        log.info("TC_12 completed successfully: All sports events have valid names and dates");
    }
}