package org.district.automation.test;

import org.district.automation.base.BaseClass;
import org.district.automation.pages.SportsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_10_MultipleFiltersClearTest extends BaseClass {

    @Test
    public void verifyMultipleFiltersAndClearFunctionality() throws Exception {
        log.info("TC_15 started: Verify Multiple Genre Filters and Clear Filters");
        SportsPage sportsPage = new SportsPage(driver);

        log.info("Navigating to Events page");
        sportsPage.clickEventsTab();
        log.info("Scrolling till All events section");
        sportsPage.scrollToAllEvents();
        log.info("Opening Filters and selecting Sports genre");
        sportsPage.openFilters();
        sportsPage.selectSportsGenre();
        sportsPage.applyFilters();
        log.info("Re-opening Filters and applying multiple Genre filters");
        sportsPage.openFilters();
        sportsPage.selectFirstNGenreFilters(3);
        log.info("Clearing all applied filters");
        sportsPage.clearAllFilters();
        boolean anyFilterSelected = sportsPage.areAnyGenreFiltersSelected();
        log.info("Are any filters still selected? {}", anyFilterSelected);

        Assert.assertFalse(
                anyFilterSelected,
                "Filters were not cleared successfully"
        );

        log.info("TC_15 completed successfully: Multiple filters applied and cleared correctly");
    }
}