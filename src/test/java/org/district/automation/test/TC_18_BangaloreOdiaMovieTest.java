package org.district.automation.test;
import org.district.automation.base.BaseClass;

import org.district.automation.pages.MoviesPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_18_BangaloreOdiaMovieTest extends BaseClass {

    @Test
    public void testKannadaMoviesInBangalore() {
        log.info("Starting testKannadaMoviesInBangalore execution.");

        // 1. Initialize the Page Object
        MoviesPage moviesPage = new MoviesPage(driver);

        // 2. Execution Steps
        moviesPage.section();
        moviesPage.selectLocation("Bangalore");
        moviesPage.selectLanguage("Odia");

        // 3. Action & Log Output
        int movieCount = moviesPage.printAndGetMovieCount();

        // 4. Assertion - Verify that movies are present
        Assert.assertTrue(movieCount > 0, "FAIL: No Kannada movies were found in Bangalore!");

        log.info("testKannadaMoviesInBangalore Passed: Kannada movies found in Bangalore: {}", movieCount);
    }
}
