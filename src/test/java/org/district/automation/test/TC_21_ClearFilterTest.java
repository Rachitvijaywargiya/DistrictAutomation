package org.district.automation.test;

import org.district.automation.base.BaseClass;
import org.district.automation.pages.MoviesPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_21_ClearFilterTest extends BaseClass {

    @Test
    public void TC10_verifyClearFilterFunctionality() {
        MoviesPage moviesPage = new MoviesPage(driver);

        moviesPage.section();
        moviesPage.openFilters();
        moviesPage.selectFilterOption("Genre", "Action");
        moviesPage.selectFilterOption("Language","Tamil");
        moviesPage.selectFilterOption("Format","2D");
        moviesPage.applyFilters();
        moviesPage.openFilters();
        moviesPage.clickClearFilters();
        moviesPage.applyFilters();

        boolean isCleared = moviesPage.areFiltersCleared();
        Assert.assertTrue(isCleared, "FAIL: The filters were not cleared successfully!");
        log.info("TC10 Passed: Filter reset functionality is working correctly.");
    }
}