package org.example.base.Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;
import java.util.*;

public class MoviesPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private static final Logger logger = LogManager.getLogger(MoviesPage.class);

    @FindBy(linkText = "Movies")
    private WebElement moviesTab;

    @FindBy(xpath = "//button[contains(.,'Filters')]")
    private WebElement filtersButton;

    @FindBy(xpath = "//span[normalize-space()='Language']")
    private WebElement languageMenu;

    @FindBy(xpath = "//span[normalize-space()='Genre']")
    private WebElement genreMenu;

    private By checkboxText = By.xpath("//div[contains(@class,'checkbox-container')]//span[contains(@class,'dds-text-base')]");

    // --- Locators from LocationPage ---
    @FindBy(xpath = "//button[.//span[contains(@class, 'dds-text-primary')]]")
    private WebElement locationHeader;

    @FindBy(css = "input[placeholder*='Search']")
    private WebElement searchInput;

    // --- Locators from LanguageBarPage ---
    @FindBy(xpath = "//span[normalize-space()='Filters']")
    private WebElement filterButton;

    @FindBy(xpath = "//span[normalize-space()='Language']")
    private WebElement languageTab;

    @FindBy(xpath = "//button[@aria-label='Apply Filters']")
    private WebElement applyButton;


    @FindBy(css = ".dds-badge-close")
    private List<WebElement> activeFilterPills;

    // --- Locators from MovieListPage ---
    //@FindBy(css = ".dds-tracking-tight.dds-text-lg.dds-font-semibold.dds-overflow-hidden.dds-whitespace-normal.dds-line-clamp-2.dds-text-primary.dds-my-0")
    @FindBy(xpath = "//body/main/section/div/div/div/div/div[3]")
    private List<WebElement> movieTitleElements;


    // Constructor
    public MoviesPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public void section() {
        wait.until(ExpectedConditions.elementToBeClickable(moviesTab)).click();
        logger.info("Navigated to the Movies section.");
    }

    public void openFilters() {
        wait.until(ExpectedConditions.elementToBeClickable(filtersButton)).click();

        // IMPORTANT: wait until filter tray is fully open
        wait.until(ExpectedConditions.visibilityOf(applyButton));
    }

    public List<String> getAllLanguages() {
        wait.until(ExpectedConditions.elementToBeClickable(languageMenu)).click();
        return extractCheckboxValues();
    }

    public List<String> getAllGenres() {
        wait.until(ExpectedConditions.elementToBeClickable(genreMenu)).click();
        return extractCheckboxValues();
    }

    private List<String> extractCheckboxValues() {
        Set<String> values = new HashSet<>();
        wait.until(ExpectedConditions.presenceOfElementLocated(checkboxText));
        List<WebElement> elements = driver.findElements(checkboxText);
        for (WebElement el : elements) {
            String text = el.getText().trim();
            if (!text.isEmpty()) {
                values.add(text);
            }
        }
        return new ArrayList<>(values);
    }

    // --- Methods from LocationPage ---
    public void selectLocation(String city) {
        logger.info("Initiating location selection for city: {}", city);
        locationHeader.click();
        wait.until(ExpectedConditions.visibilityOf(searchInput));
        searchInput.clear();
        searchInput.sendKeys(city);

        String robustXpath = String.format("//*[normalize-space()='%s']/ancestor::button[1]", city);
        WebElement firstResult = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(robustXpath)));
        firstResult.click();
        logger.info("Successfully selected location: {}", city);
    }

    public void printAvailableFilters() {
        List<String> languages = getAllLanguages();
        List<String> genres = getAllGenres();

        System.out.println("\n --- EXTRACTED LANGUAGES ---");
        if (languages.isEmpty()) {
            System.out.println("No languages found.");
        } else {
            languages.forEach(lang -> System.out.println("-> " + lang));
        }

        System.out.println("\n🎭 --- EXTRACTED GENRES ---");
        if (genres.isEmpty()) {
            System.out.println("No genres found.");
        } else {
            genres.forEach(genre -> System.out.println("-> " + genre));
        }
        System.out.println("-------------------------------\n");
    }

    public int getLanguageCount() {
        return getAllLanguages().size();
    }

    public int getGenreCount() {
        return getAllGenres().size();
    }

    public boolean areLanguagesUnique() {
        List<String> langs = getAllLanguages();
        return langs.stream().distinct().count() == langs.size();
    }

    public int printAndGetMovieCount() {
        List<String> titles = getMovieTitles();

        System.out.println("\n--- Movies Found for Selected Filters ---");
        if (titles.isEmpty()) {
            System.out.println("No movies found.");
        } else {
            titles.forEach(title -> System.out.println("🎬 " + title));
        }
        System.out.println("------------------------------------------\n");

        return titles.size();
    }

    public void applyCombinationFilters(String genre, String language, String format) {
        logger.info("Applying filters: Genre={}, Language={}, Format={}", genre, language, format);

        selectFilterOption("Genre", genre);
        selectFilterOption("Language", language);
        selectFilterOption("Format", format);

        applyFilters();
    }

    public String getFilterResultMessage() {
        By messageLocator = By.cssSelector(".dds-mb-4.dds-text-\\[24px\\].dds-font-semibold");
        List<WebElement> elements = driver.findElements(messageLocator);

        for (WebElement element : elements) {
            logger.info("ℹ️ Application Message: {}", element.getText().trim());
        }

        return "No results found";
    }

    public int getResultsCountAndPrint() {
        List<String> titles = getMovieTitles();

        logger.info("--- COMBINATION FILTER RESULTS ---");
        if (titles.isEmpty()) {
            logger.info("No movies match this combination.");
        } else {
            titles.forEach(title -> logger.info("Match Found: {}", title));
        }
        logger.info("--------------------------------------");

        return titles.size();
    }

    // --- Methods from LanguageBarPage ---
    public void selectLanguage(String language) {
        logger.info("Opening filter modal to select language: {}", language);
        wait.until(ExpectedConditions.elementToBeClickable(filterButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(languageTab)).click();

        WebElement langOption = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@class,'dds-bg-surface-secondary dds-flex dds-flex-grow dds-flex-col dds-h-full dds-overflow-y-auto dds-no-scrollbar')]//div[7]")));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", langOption);
        langOption.click();
        applyButton.click();

        // Replaced Thread.sleep with condition to wait until the URL updates or a filter is applied
        wait.until(ExpectedConditions.invisibilityOf(applyButton));
    }

    public void selectFilterOption(String category, String optionName) {
        logger.info("Selecting filter category: {} -> Option: {}", category, optionName);

        try {
            if (!filterButton.isDisplayed()) {
                wait.until(ExpectedConditions.elementToBeClickable(filterButton)).click();
            }
        } catch (Exception e) {
            wait.until(ExpectedConditions.elementToBeClickable(filterButton)).click();
        }

        String categoryXpath = "//span[normalize-space()='" + category + "']";
        WebElement categoryTab = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(categoryXpath)));

        wait.until(ExpectedConditions.elementToBeClickable(categoryTab)).click();

        String optionXpath = String.format("//span[normalize-space()='%s']/parent::div", optionName);
        WebElement optionContainer = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(optionXpath)));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", optionContainer);
        optionContainer.click();

        logger.info("Selected option: {}", optionName);
    }

    public void applyFilters() {
        // Ensure Apply button is visible
        wait.until(ExpectedConditions.visibilityOf(applyButton));

        // Scroll so Chrome can click it
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", applyButton);

        // Ensure clickable
        wait.until(ExpectedConditions.elementToBeClickable(applyButton));

        try {
            applyButton.click();
        } catch (ElementClickInterceptedException e) {
            // Fallback for overlays/animations
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", applyButton);
        }
    }

    // --- Methods from MovieListPage ---
    public List<String> getMovieTitles() {
        logger.info("Waiting for movie titles to become visible...");
        wait.until(ExpectedConditions.visibilityOfAllElements(movieTitleElements));

        List<String> titles = new ArrayList<>();
        System.out.println("List Of Movies: ");
        for (WebElement e : movieTitleElements) {
            String title = e.getText();
            titles.add(title);
            System.out.println(title);
        }
        return titles;
    }

    public List<String> printAndGetMovieTitles() {
        //   By movieTitleLocator = By.cssSelector(".dds-tracking-tight.dds-text-lg.dds-font-semibold.dds-text-primary");
        By movieTitleLocator = By.cssSelector("dds-grid dds-px-[12px] sm:dds-px-[0px] dds-gap-x-3 md:dds-gap-x-4 dds-grid-cols-2 dds-gap-y-4 sm:dds-grid-cols-3 md:dds-grid-cols-4 lg:dds-grid-cols-5 xl:dds-grid-cols-6 dds-justify-items-center ");
        List<String> titles = new ArrayList<>();

        try {
            logger.info("Waiting for movie titles to load...");
            wait.until(ExpectedConditions.visibilityOfElementLocated(movieTitleLocator));
            List<WebElement> elements = driver.findElements(movieTitleLocator);

            System.out.println("\n================ LIST OF MOVIES ================");
            for (WebElement e : elements) {
                String title = e.getText().trim();
                if (!title.isEmpty()) {
                    titles.add(title);
                    System.out.println("🎬 " + title);
                }
            }
            System.out.println("================================================\n");
            logger.info("Successfully extracted {} movie titles.", titles.size());

        } catch (Exception e) {
            logger.error("Could not find movie titles: " + e.getMessage());
            System.out.println("⚠️ No movies were found on the page.");
        }

        return titles;
    }

    // Unchecking
    @FindBy(xpath = "//div[normalize-space()='Clear filters']")
    private WebElement clearFiltersButton;

    public void clickClearFilters() {
        logger.info("Clicking the 'Clear filters' button.");

        // 1. Click the button safely
        wait.until(ExpectedConditions.elementToBeClickable(clearFiltersButton)).click();

        // 2. Instead of staleness, wait until the "Clear filters" button or active filter pills are no longer visible,
        // or just let the page process the action without forcing a hard condition if it closes instantly.
        try {
            wait.until(ExpectedConditions.invisibilityOfAllElements(activeFilterPills));
        } catch (Exception e) {
            logger.info("Clear filters button did not become invisible, proceeding with test.");
        }
    }

    public boolean areFiltersCleared() {
        List<String> activeFilters = getActiveFilterPills();
        return activeFilters.isEmpty();
    }

    public List<String> getActiveFilterPills() {
        By activeFilterLocator = By.cssSelector(".dds-badge-close");
        List<WebElement> elements = driver.findElements(activeFilterLocator);
        List<String> activeFilters = new ArrayList<>();

        for (WebElement e : elements) {
            activeFilters.add(e.getText().trim());
        }

        return activeFilters;
    }


}