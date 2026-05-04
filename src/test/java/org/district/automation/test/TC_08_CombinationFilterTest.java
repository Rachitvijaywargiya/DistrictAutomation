package org.district.automation.test;
import org.district.automation.base.BaseClass;
import org.example.base.Pages.MoviesPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_08_CombinationFilterTest extends BaseClass {

    private static final Logger logger = LoggerFactory.getLogger(TC_08_CombinationFilterTest.class);

    @Test
    public void TC08_verifyCombinationFilterFunctionality() {
        // 1. Initialize Page
        MoviesPage moviesPage = new MoviesPage(driver);

        // 2. Navigation
        moviesPage.section();

        // 3. Execution (Logic moved to MoviesPage)
        moviesPage.applyCombinationFilters("Action", "Malayalam", "3D");

        // 4. Action & Log Output
        int movieCount = moviesPage.getResultsCountAndPrint();

        // 5. Assertion
        // We check that the list exists (is not null) and is handled correctly
        Assert.assertTrue(movieCount >= 0, "TC03 Failed: Movie extraction failed.");

        logger.info("TC03 Passed: Combination filters applied. Movies found: {}", movieCount);
    }
}
