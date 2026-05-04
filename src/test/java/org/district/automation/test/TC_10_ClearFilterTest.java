package org.district.automation.test;
import org.district.automation.base.BaseClass;
import org.example.base.Pages.MoviesPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_10_ClearFilterTest extends BaseClass {

    @Test
    public void TC10_verifyClearFilterFunctionality() {
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

        System.out.println("TC10 Passed: Filter reset functionality is working correctly.");
    }
}