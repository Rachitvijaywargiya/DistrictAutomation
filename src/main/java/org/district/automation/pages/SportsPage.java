package org.district.automation.pages;

import org.district.automation.utility.WaitUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class SportsPage {

    private WebDriver driver;
    private JavascriptExecutor js;

    @FindBy(xpath = "//a[contains(text(),'Events')]")
    private WebElement eventsTab;

    @FindBy(xpath = "//span[text()='Filters']")
    private WebElement filterButton;

    @FindBy(xpath = "//span[contains(text(),'Genre')]")
    private WebElement genreOption;

    @FindBy(xpath = "//span[normalize-space()='Sports']")
    private WebElement sportsCheckbox;

    @FindBy(xpath = "//span[contains(text(),'Apply Filters')]")
    private WebElement applyFiltersButton;

    public SportsPage(WebDriver driver) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    public void clickEventsTab() {
        WaitUtils.waitForElementToBeClickable(driver,eventsTab,15);
        eventsTab.click();
    }

    public void openFilters() {
        WaitUtils.waitForElementToBeClickable(driver,filterButton,15);
        js.executeScript("arguments[0].scrollIntoView(true);", filterButton);
        filterButton.click();
    }

    public void selectGenreSports() {
        WaitUtils.waitForElementToBeClickable(driver,genreOption,15);
        genreOption.click();
        WaitUtils.waitForElementToBeClickable(driver,sportsCheckbox,15);
        js.executeScript("arguments[0].scrollIntoView(true);", sportsCheckbox);
        sportsCheckbox.click();
    }

    public void applyFilters() {
        WaitUtils.waitForElementToBeClickable(driver,applyFiltersButton,15);
        applyFiltersButton.click();
    }
}
