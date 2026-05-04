package org.district.automation.test;
import org.district.automation.base.BaseClass;
import org.example.base.Pages.MoviesPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_06_ExtractLanguagesAndGenres extends BaseClass {

    private static final Logger logger = LoggerFactory.getLogger(TC_06_ExtractLanguagesAndGenres.class);

    @Test
    public void TC06_extractLanguagesAndGenres() {
        logger.info("Starting TC06: Extracting and validating available languages and genres.");

        // 1. Initialize the consolidated MoviesPage
        MoviesPage moviesPage = new MoviesPage(driver);

        // 2. Navigation Steps
        moviesPage.section();
        moviesPage.openFilters();

        // 3. Action: Print filters to log/console
        moviesPage.printAvailableFilters();

        // 4. Verification (Assertions)
        // Verify Languages
        int languageCount = moviesPage.getLanguageCount();
        logger.info("Validating language count. Found: {}", languageCount);
        Assert.assertTrue(languageCount > 0, "FAIL: No languages found in filters!");
        Assert.assertTrue(moviesPage.areLanguagesUnique(), "FAIL: Duplicate languages detected!");

        // Verify Genres
        int genreCount = moviesPage.getGenreCount();
        logger.info("Validating genre count. Found: {}", genreCount);
        Assert.assertTrue(genreCount > 0, "FAIL: No genres found in filters!");

        logger.info("TC06 Passed: Successfully extracted and verified languages and genres.");
    }
}