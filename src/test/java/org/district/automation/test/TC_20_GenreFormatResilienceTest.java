package org.district.automation.test;

import org.district.automation.base.BaseClass;
import org.district.automation.pages.MoviesPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_20_GenreFormatResilienceTest extends BaseClass {

    @Test
    public void TC09_verifyGenreFormatResilience(){
        MoviesPage moviesPage = new MoviesPage(driver);

        moviesPage.section();
        moviesPage.selectFilterOption("Genre", "Horror");
        moviesPage.selectFilterOption("Language", "Tamil");
        moviesPage.selectFilterOption("Format", "4DX-2D");
        moviesPage.applyFilters();

        String status = moviesPage.getFilterResultMessage();

        Assert.assertNotNull(status, "FAIL: Could not determine filter result state.");
        Assert.assertTrue(status.contains("No results found") || status.equals("Movies Found"),
                "FAIL: The filter result state did not match expected outcomes.");
        log.info("TC09 Passed: Resilience check and verification completed for Horror/Tamil/4DX-2D.");
    }
}