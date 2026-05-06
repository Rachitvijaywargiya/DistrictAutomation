package org.district.automation.test;

import org.district.automation.base.BaseClass;

import org.district.automation.pages.MoviesPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_18_BangaloreOdiaMovieTest extends BaseClass {

    @Test
    public void testKannadaMoviesInBangalore() {
        log.info("Starting testKannadaMoviesInBangalore execution.");
        MoviesPage moviesPage = new MoviesPage(driver);

        moviesPage.section();
        moviesPage.selectLocation("Bangalore");
        moviesPage.selectLanguage("Hindi");

        int movieCount = moviesPage.printAndGetMovieCount();
        Assert.assertTrue(movieCount > 0, "FAIL: No Kannada movies were found in Bangalore!");
        log.info("testKannadaMoviesInBangalore Passed: Kannada movies found in Bangalore: {}", movieCount);
    }
}
