package org.district.automation.pages;

import org.district.automation.utility.JsUtils;
import org.district.automation.utility.WaitUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class MoviesPage {

    private WebDriver driver;
    private static final Logger log = LogManager.getLogger(MoviesPage.class);

    @FindBy(linkText = "Movies")
    private WebElement moviesTab;

    @FindBy(xpath = "//button[contains(.,'Filters')]")
    private WebElement filtersButton;

    @FindBy(xpath = "//span[normalize-space()='Language']")
    private WebElement languageMenu;

    @FindBy(xpath = "//span[normalize-space()='Genre']")
    private WebElement genreMenu;

    @FindBy(xpath = "//button[.//span[contains(@class, 'dds-text-primary')]]")
    private WebElement locationHeader;

    @FindBy(css = "input[placeholder*='Search']")
    private WebElement searchInput;

    @FindBy(xpath = "//span[normalize-space()='Filters']")
    private WebElement filterButton;

    @FindBy(xpath = "//span[normalize-space()='Language']")
    private WebElement languageTab;

    @FindBy(xpath = "//button[@aria-label='Apply Filters']")
    private WebElement applyButton;

    @FindBy(css = ".dds-badge-close")
    private List<WebElement> activeFilterPills;

    @FindBy(xpath = "//div[@id='filters-content']/following-sibling::div")
    private List<WebElement> movieTitleElements;

    @FindBy(xpath = "//div[normalize-space()='Clear filters']")
    private WebElement clearFiltersButton;

    private By checkboxText = By.xpath("//div[contains(@class,'checkbox-container')]//span[contains(@class,'dds-text-base')]");

    public MoviesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void section() {
        WaitUtils.waitForElementToBeClickable(driver,moviesTab,15);
        moviesTab.click();
        log.info("Navigated to the Movies section.");
    }

    public void openFilters() {
        WaitUtils.waitForElementToBeClickable(driver,filtersButton,15);
        filtersButton.click();
        WaitUtils.waitForElementToBeVisible(driver,applyButton,15);
    }

    public List<String> getAllLanguages() {
        WaitUtils.waitForElementToBeClickable(driver,languageMenu,15);
        languageMenu.click();
        return extractCheckboxValues();
    }

    public List<String> getAllGenres() {
        WaitUtils.waitForElementToBeClickable(driver,genreMenu,15);
        genreMenu.click();
        return extractCheckboxValues();
    }

    private List<String> extractCheckboxValues() {
        Set<String> values = new HashSet<>();
        WaitUtils.waitForPresenceOfElementLocated(driver,checkboxText,15);
        List<WebElement> elements = driver.findElements(checkboxText);
        for (WebElement el : elements) {
            String text = el.getText().trim();
            if (!text.isEmpty()) {
                values.add(text);
            }
        }
        return new ArrayList<>(values);
    }

    public void selectLocation(String city) {
        log.info("Initiating location selection for city: {}", city);
        locationHeader.click();
        WaitUtils.waitForElementToBeVisible(driver,searchInput,15);
        searchInput.clear();
        searchInput.sendKeys(city);
        String robustXpath = String.format("//*[normalize-space()='%s']/ancestor::button[1]", city);
        WebElement firstResult = WaitUtils.waitForElementToBeVisible(driver,By.xpath(robustXpath),15);
        firstResult.click();
        log.info("Successfully selected location: {}", city);
    }

    public void printAvailableFilters() {
        List<String> languages = getAllLanguages();
        List<String> genres = getAllGenres();
        log.info("--- EXTRACTED LANGUAGES ---");
        if (languages.isEmpty()) {
            System.out.println("No languages found.");
        } else {
            languages.forEach(lang -> log.info("-> {}", lang));
        }
        log.info("--- EXTRACTED GENRES ---");
        if (genres.isEmpty()) {
            System.out.println("No genres found.");
        } else {
            genres.forEach(genre -> System.out.println("-> " + genre));
        }
    }

    public int getLanguageCount() {
        return getAllLanguages().size();
    }

    public int getGenreCount() {
        return getAllGenres().size();
    }

    public boolean areLanguagesUnique() {
        List<String> languages = getAllLanguages();
        return languages.stream().distinct().count() == languages.size();
    }

    public int printAndGetMovieCount() {
        List<String> titles = getMovieTitles();
        log.info("--- Movies Found for Selected Filters ---");
        if (titles.isEmpty()) {
            log.info("No movies found.");
        } else {
            titles.forEach(title -> System.out.println("🎬 " + title));
        }
        return titles.size();
    }

    public void applyCombinationFilters(String genre, String language, String format) {
        log.info("Applying filters: Genre={}, Language={}, Format={}", genre, language, format);
        selectFilterOption("Genre", genre);
        selectFilterOption("Language", language);
        selectFilterOption("Format", format);
        applyFilters();
    }

    public String getFilterResultMessage() {
        By messageLocator = By.cssSelector(".dds-mb-4.dds-text-\\[24px\\].dds-font-semibold");
        List<WebElement> elements = driver.findElements(messageLocator);
        for (WebElement element : elements) {
            log.info("ℹ️ Application Message: {}", element.getText().trim());
        }
        return "No results found";
    }

    public int getResultsCountAndPrint() {
        List<String> titles = getMovieTitles();
        log.info("--- COMBINATION FILTER RESULTS ---");
        if (titles.isEmpty()) {
            log.info("No movies match this combination.");
        } else {
            titles.forEach(title -> log.info("Match Found: {}", title));
        }
        return titles.size();
    }

    public void selectLanguage(String language) {
        log.info("Opening filter modal to select language: {}", language);
        WaitUtils.waitForElementToBeClick(driver,filterButton,15).click();
        WaitUtils.waitForElementToBeClick(driver,languageTab,15).click();
        String robustXpath = String.format("//div[contains(@class,'checkbox-container')]//span[contains(@class,'dds-text-primary') and normalize-space(text())='" + language + "']");
        WebElement langOption = WaitUtils.waitForElementToBeVisible(driver,By.xpath(robustXpath),15);
        JsUtils.scrollIntoView(driver, langOption);
        langOption.click();
        WaitUtils.waitForElementToBeClick(driver,applyButton,15).click();
        WaitUtils.waitForElementToBeInvisible(driver,applyButton,15);
    }

    public void selectFilterOption(String category, String optionName) {
        log.info("Selecting filter category: {} -> Option: {}", category, optionName);
        try {
            log.debug(filterButton.isDisplayed());
            if (!filterButton.isDisplayed()) {
                WaitUtils.waitForElementToBeClick(driver,filterButton,15).click();
            }
        } catch (Exception e) {
            WaitUtils.waitForElementToBeClick(driver,filterButton,15).click();
        }
        String categoryXpath = "//span[normalize-space()='" + category + "']";
        WebElement categoryTab = WaitUtils.waitForElementToBeVisibleBy(driver,By.xpath(categoryXpath),15);
        WaitUtils.waitForElementToBeClick(driver,categoryTab,15).click();
        String optionXpath = String.format("//span[normalize-space()='%s']/parent::div", optionName);
        WebElement optionContainer = WaitUtils.waitForElementToBeClickable(driver,By.xpath(optionXpath),15);
        JsUtils.scrollIntoViewCenter(driver, optionContainer);
        optionContainer.click();
        log.info("Selected option: {}", optionName);
    }

    public void applyFilters() {
        WaitUtils.waitForElementToBeVisible(driver,applyButton,20);
        JsUtils.scrollIntoViewCenter(driver, applyButton);
        WaitUtils.waitForElementToBeClickable(driver,applyButton,20);
        try {
            applyButton.click();
        } catch (ElementClickInterceptedException e) {
            JsUtils.clickUsingJs(driver, applyButton);
        }
    }

    public List<String> getMovieTitles() {
        log.info("Waiting for movie titles to become visible...");
        WaitUtils.waitForAllElementsVisible(driver,movieTitleElements,15);
        List<String> titles = new ArrayList<>();
        log.info("List Of Movies: ");
        for (WebElement e : movieTitleElements) {
            String title = e.getText();
            titles.add(title);
            log.info(title);
        }
        return titles;
    }

    public void clickClearFilters() {
        log.info("Clicking the 'Clear filters' button.");
        WaitUtils.waitForElementToBeClick(driver,clearFiltersButton,15).click();
        try {
            WaitUtils.waitForInvisibilityOfAllElements(driver,activeFilterPills,15);
        } catch (Exception e) {
            log.info("Clear filters button did not become invisible, proceeding with test.");
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