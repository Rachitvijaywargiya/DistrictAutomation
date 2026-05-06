package org.district.automation.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.district.automation.utility.JsUtils;
import org.district.automation.utility.WaitUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class SportsPage {

    private WebDriver driver;
    private static final Logger log = LogManager.getLogger(SportsPage.class);

    @FindBy(xpath = "//a[contains(text(),'Events')]")
    private WebElement eventsTab;

    @FindBy(xpath = "//span[contains(normalize-space(), 'Filters')]")
    private WebElement filterButton;

    @FindBy(xpath = "//span[contains(text(),'Genre')]")
    private WebElement genreOption;

    @FindBy(xpath = "//span[normalize-space()='Sports']")
    private WebElement sportsCheckbox;

    @FindBy(xpath = "//span[normalize-space()='Sports']/preceding-sibling::input")
    private WebElement sportsInput;

    @FindBy(xpath = "//span[contains(text(),'Apply Filters')]")
    private WebElement applyFiltersButton;

    @FindBy(xpath = "//div[text()='Clear filters']")
    private WebElement clearFilters;

    @FindBy(xpath = "//label[contains(text(),'Price : Low to High')]")
    private WebElement lowToHigh;

    @FindBy(xpath = "//div[@id='filters-content']/following-sibling::div[contains(@class,'dds-grid')]")
    private WebElement areEventsDisplayed;

    @FindBy(xpath = "//div[@id='filters-content']/following-sibling::div[contains(@class,'dds-grid')]")
    private List<WebElement> eventCount;

    @FindBy(xpath = "//div[@class='dds-relative dds-w-full']/div/a/div/div/div/span[1]")
    private List<WebElement> dateandtime;

    @FindBy(xpath = "//div[@class='dds-relative dds-w-full']/div/a/div/div/div/h5")
    private List<WebElement> title;

    @FindBy(id = "page-content")
    private WebElement pageContentContainer;

    @FindBy(xpath = "//div[normalize-space()='Genre']")
    private WebElement genreTab;

    @FindBy(xpath = "//button[.//span[normalize-space()='This Weekend']]")
    private WebElement thisWeekendButton;

    @FindBy(xpath = "//span[contains(@class,'dds-text-quaternary')]")
    private List<WebElement> eventDates;

    @FindBy(xpath = "//h5[contains(@class,'dds-font-semibold')]")
    private List<WebElement> eventTitles;

    @FindBy(xpath = "//span[contains(text(),'₹')]")
    private List<WebElement> eventPrices;

    @FindBy(xpath = "//div[normalize-space()='Sort By']")
    private WebElement sortByTab;

    @FindBy(xpath = "//label[contains(normalize-space(),'Price : Low to High')]")
    private WebElement priceLowToHigh;

    @FindBy(xpath = "//div[contains(@class,'checkbox-container')]")
    private List<WebElement> genreOptions;

    @FindBy(xpath = "//div[contains(@style,'border-radius:') and contains(@class,'dds-bg-surface-secondary')]")
    private WebElement menu;

    public SportsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickEventsTab() {
        WaitUtils.waitForElementToBeClickable(driver, eventsTab, 15);
        JsUtils.scrollIntoView(driver, eventsTab);
        JsUtils.clickUsingJs(driver,eventsTab);
    }

    public void openFilters() {
        WaitUtils.waitForElementToBeClickable(driver, filterButton, 15);
        JsUtils.scrollIntoView(driver,filterButton);
        filterButton.click();
    }

    public void selectGenreSports() {
        WaitUtils.waitForElementToBeClick(driver, genreOption, 15).click();
        WaitUtils.waitForElementToBeClickable(driver, sportsCheckbox, 15);
        JsUtils.scrollIntoView(driver,sportsCheckbox);
        sportsCheckbox.click();
    }

    public void selectGenre() {
        WaitUtils.waitForElementToBeClick(driver, genreOption, 15).click();
    }

    public void applyFilters() {
        WaitUtils.waitForElementToBeClickable(driver, applyFiltersButton, 15);
        applyFiltersButton.click();
    }

    public void clearAll() {
        WaitUtils.waitForElementToBeClickable(driver, clearFilters, 15);
        clearFilters.click();
    }

    public boolean isSportsCheckboxSelected() {
        return sportsInput.isSelected();

    }

    public void lowHigh() {
        lowToHigh.click();
    }

    public boolean displayed() {
        return areEventsDisplayed.isDisplayed();
    }
    public int endToEnd(){
        return eventCount.size();
    }

    public void scrollToAllEvents() throws InterruptedException {
        By allEvents = By.xpath("//span[normalize-space()='All events']");
        for (int i = 0; i < 20; i++) {
            if (!driver.findElements(allEvents).isEmpty()) {
                JsUtils.scrollIntoViewCenter(driver, driver.findElement(allEvents));
                return;
            }
            JsUtils.scrollDown(driver, pageContentContainer, 600);
            Thread.sleep(1200);
        }
    }

    public void selectSportsGenre() {
        WaitUtils.waitForElementToBeClick(driver, genreTab, 15).click();
        JsUtils.scrollIntoViewCenter(driver,sportsCheckbox);
        JsUtils.clickUsingJs(driver,sportsCheckbox);
    }

    public void clickThisWeekend() {
        WaitUtils.waitForElementToBeClickable(driver, thisWeekendButton, 15);
        thisWeekendButton.click();
    }

    public List<String> getAllEventDates() {
        List<String> dates = new ArrayList<>();
        for (WebElement date : eventDates) {
            try {
                dates.add(date.getText().toLowerCase());
            } catch (StaleElementReferenceException ignored) {
            }
        }
        return dates;
    }

    public List<String> getAllEventNames() {
        List<String> names = new ArrayList<>();
        for (WebElement title : eventTitles) {
            try {
                names.add(title.getText());
            } catch (StaleElementReferenceException ignored) {
            }
        }
        return names;
    }

    public List<Integer> getDisplayedPrices() {
        List<Integer> prices = new ArrayList<>();
        for (WebElement price : eventPrices) {
            try {
                String value = price.getText().replaceAll("[^0-9]", "");
                if (!value.isEmpty()) {
                    prices.add(Integer.parseInt(value));
                }
            } catch (StaleElementReferenceException ignored) {
            }
        }
        return prices;
    }

    public void applyLowToHighSort() {
        WaitUtils.waitForElementToBeClick(driver, filterButton, 15).click();
        JsUtils.scrollIntoViewCenter(driver,sortByTab);
        JsUtils.clickUsingJs(driver,sortByTab);
        JsUtils.scrollIntoViewCenter(driver,priceLowToHigh);
        JsUtils.clickUsingJs(driver,priceLowToHigh);
    }

    public void selectFirstNGenreFilters(int n) {
        WaitUtils.waitForElementToBeClick(driver, genreTab, 15).click();
        int count = 0;
        for (WebElement option : genreOptions) {
            if (count == n) break;
            option.click();
            count++;
        }
    }

    public void clearAllFilters() {
        WaitUtils.waitForElementToBeClick(driver, clearFilters, 15).click();
    }

    public boolean areAnyGenreFiltersSelected() {
        for (WebElement option : genreOptions) {
            if (option.getAttribute("class").toLowerCase().contains("checked")) {
                return true;
            }
        }
        return false;
    }

    public void printAllWeekendSportsEvents() {
        List<String> names = getAllEventNames();
        List<String> dates = getAllEventDates();
        List<Integer> prices = getDisplayedPrices();

        int size = Math.min(names.size(), Math.min(dates.size(), prices.size()));

        for (int i = 0; i < size; i++) {
            log.info(
                    "Event " + (i + 1) +
                            " | Name: " + names.get(i) +
                            " | Date: " + dates.get(i) +
                            " | Price: ₹" + prices.get(i)
            );
        }
    }

    public boolean isMenuVisible(){
        return menu.isDisplayed();
    }

    public boolean isFilterPanelVisible() {
        By filterLoc = By.xpath("//span[text()='Filters']");
        WebElement filterPanel = WaitUtils.waitForElementToBeVisible(driver,filterLoc,10);
        return filterPanel.isDisplayed();
    }
}
