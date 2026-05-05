package org.district.automation.test;
import org.district.automation.base.BaseClass;
import org.district.automation.pages.MoviesPage;
import org.testng.Assert;
import org.testng.annotations.Test;
public class TC_21_ClearFilterTest extends BaseClass {
    @Test
    public void TC10_verifyClearFilterFunctionality() throws InterruptedException {
        // 1. Initialize Page
        MoviesPage moviesPage = new MoviesPage(driver);

        // 2. Navigate to Movies section
        moviesPage.section();

        // 3. Open filters, select an option, and apply
        moviesPage.openFilters();
        moviesPage.selectFilterOption("Genre", "Action");
        moviesPage.selectFilterOption("Language","Tamil");
        moviesPage.selectFilterOption("Format","2D");
        moviesPage.applyFilters();

        // 4. Reopen filter tray, clear the values, and re-apply
        moviesPage.openFilters();
        moviesPage.clickClearFilters();
        moviesPage.applyFilters();

        // 5. Verify the state
        boolean isCleared = moviesPage.areFiltersCleared();
        Assert.assertTrue(isCleared, "FAIL: The filters were not cleared successfully!");

        log.info("TC10 Passed: Filter reset functionality is working correctly.");
    }
}