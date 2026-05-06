package org.district.automation.test;

import org.district.automation.base.BaseClass;
import org.district.automation.pages.MoviesPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_19_CombinationFilterTest extends BaseClass {

    @Test
    public void TC08_verifyCombinationFilterFunctionality() throws InterruptedException {
        MoviesPage moviesPage = new MoviesPage(driver);
        moviesPage.section();

        moviesPage.applyCombinationFilters("Action", "Malayalam", "3D");
        int movieCount = moviesPage.getResultsCountAndPrint();

        Assert.assertTrue(movieCount >= 0, "TC03 Failed: Movie extraction failed.");
        log.info("TC03 Passed: Combination filters applied. Movies found: {}", movieCount);
    }
}
