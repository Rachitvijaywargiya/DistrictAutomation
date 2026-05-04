package org.district.automation.pages;

import org.district.automation.utility.WaitUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class SportsPage {

    private WebDriver driver;
    private JavascriptExecutor js;

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

    @FindBy(xpath = "//*[text()='Clear filters']")
    private WebElement clearFilters;

    @FindBy(xpath = "//label[contains(text(),'Price : Low to High')]")
    private WebElement lowToHigh;

    @FindBy(xpath = "//*[contains(@class,'dds-grid dds-gap-x-3 md:dds-gap-x-4 dds-grid-cols-1 dds-gap-y-8 md:dds-grid-cols-2 lg:dds-grid-cols-3 xl:dds-grid-cols-4')]")
    private WebElement areEventsDisplayed;

    @FindBy(xpath = "//div[contains(@class,'dds-grid dds-gap-x-3 md:dds-gap-x-4 dds-grid-cols-1 dds-gap-y-8')]")
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

    public SportsPage(WebDriver driver) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    public void clickEventsTab() {
        WaitUtils.waitForElementToBeClickable(driver, eventsTab, 15);
        js.executeScript("arguments[0].scrollIntoView(true);", eventsTab);
        js.executeScript("arguments[0].click()", eventsTab);

    }

    public void openFilters() {
        WaitUtils.waitForElementToBeClickable(driver, filterButton, 15);
        js.executeScript("arguments[0].scrollIntoView(true);", filterButton);
        filterButton.click();
    }

    public void selectGenreSports() {
        WaitUtils.waitForElementToBeClickable(driver, genreOption, 15);
        genreOption.click();
        WaitUtils.waitForElementToBeClickable(driver, sportsCheckbox, 15);
        js.executeScript("arguments[0].scrollIntoView(true);", sportsCheckbox);
        sportsCheckbox.click();
    }

    public void selectGenre() {
        WaitUtils.waitForElementToBeClickable(driver, genreOption, 15);
        genreOption.click();

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
                js.executeScript(
                        "arguments[0].scrollIntoView({block:'center'});",
                        driver.findElement(allEvents)
                );
                return;
            }
            js.executeScript("arguments[0].scrollTop += 600;", pageContentContainer);
            Thread.sleep(1200);
        }
    }

    public void selectSportsGenre() {
        WaitUtils.waitForElementToBeClickable(driver, genreTab, 15);
        genreTab.click();
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", sportsCheckbox);
        js.executeScript("arguments[0].click();", sportsCheckbox);
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

        WaitUtils.waitForElementToBeClickable(driver, filterButton, 15);
        filterButton.click();

        js.executeScript("arguments[0].scrollIntoView({block:'center'});", sortByTab);
        js.executeScript("arguments[0].click();", sortByTab);

        js.executeScript("arguments[0].scrollIntoView({block:'center'});", priceLowToHigh);
        js.executeScript("arguments[0].click();", priceLowToHigh);
    }

    public void selectFirstNGenreFilters(int n) {
        WaitUtils.waitForElementToBeClickable(driver, genreTab, 15);
        genreTab.click();
        int count = 0;
        for (WebElement option : genreOptions) {
            if (count == n) break;
            option.click();
            count++;
        }
    }

    public void clearAllFilters() {
        WaitUtils.waitForElementToBeClickable(driver, clearFilters, 15);
        clearFilters.click();
    }

    public boolean areAnyGenreFiltersSelected() {
        for (WebElement option : genreOptions) {
            if (option.getAttribute("class").toLowerCase().contains("checked")) {
                return true;
            }
        }
        return false;
    }



}
