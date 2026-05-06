package org.district.automation.test;

import org.district.automation.base.BaseClass;
import org.district.automation.pages.MoviesPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_17_ExtractLanguagesAndGenres extends BaseClass {

    @Test
    public void TC06_extractLanguagesAndGenres() {
        log.info("Starting TC06: Extracting and validating available languages and genres.");
        MoviesPage moviesPage = new MoviesPage(driver);

        moviesPage.section();
        moviesPage.openFilters();
        moviesPage.printAvailableFilters();

        int languageCount = moviesPage.getLanguageCount();
        log.info("Validating language count. Found: {}", languageCount);

        Assert.assertTrue(languageCount > 0, "FAIL: No languages found in filters!");
        Assert.assertTrue(moviesPage.areLanguagesUnique(), "FAIL: Duplicate languages detected!");

        int genreCount = moviesPage.getGenreCount();
        log.info("Validating genre count. Found: {}", genreCount);
        Assert.assertTrue(genreCount > 0, "FAIL: No genres found in filters!");

        log.info("TC06 Passed: Successfully extracted and verified languages and genres.");
    }
}