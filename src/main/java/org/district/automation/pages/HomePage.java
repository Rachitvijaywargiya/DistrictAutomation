package org.district.automation.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    private WebDriver driver;
    @FindBy(xpath = "//div[contains(@class,'dds-text') and contains(text(),'Karnataka')]")
    private WebElement cityText;
    @FindBy(xpath = "//img[contains(@class,'cursor-pointer')]")
    private WebElement logo;

    @FindBy(xpath = "//button[@aria-label]")
    private WebElement citySelectorButton;

    @FindBy(xpath = "//input[@placeholder='Search city, area or locality']")
    private WebElement citySearchInput;

    @FindBy(xpath = "//span[contains(@class,'dds-text-secondary')]")
    private WebElement locationText;

    @FindBy(xpath = "//*[@id='master-header-extended']/div[2]/div/a[4]")
    private WebElement eventsTab;

    @FindBy(xpath = "//div[contains(text(),'Movies')]")
    private WebElement moviesTab;

    @FindBy(xpath = "//div[contains(text(),'Search for events')]")
    private WebElement searchBox;

    @FindBy(xpath = "//div[@class='dds-w-8 dds-h-8 dds-flex dds-items-center dds-justify-center']")
    private WebElement selectloc;

    @FindBy(xpath = "//input[@placeholder='Search city, area or locality']")
    private WebElement getCitySearchInput;

    @FindBy(xpath = "(//span[contains(text(),'Pune')])[1]")
    private WebElement puneLoc;

    @FindBy(xpath = "(//span[contains(text(),'Pune')])[1]")
    private WebElement getPuneLocAfterClick;

    @FindBy(xpath = "(//span[contains(text(),'Pune')])[2]")
    private WebElement getEntireAddressLocAfterClick;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isLogoDisplayed() {
        return logo.isDisplayed();
    }

    public boolean isCitySelectorDisplayed() {
        return citySelectorButton.isDisplayed();
    }

    public String getDisplayedLocation() {
        return locationText.getText();
    }

    public WebElement getLocationTextElement() {
        return locationText;
    }

//    public boolean isEventsTabEnabled() {
//        return eventsTab.isEnabled();
//    }

    public boolean isSearchBoxEnabled() {
        return searchBox.isDisplayed();
    }

    public void selectCity(String cityName) {

        citySelectorButton.click();
        citySearchInput.clear();
        citySearchInput.sendKeys(cityName);
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        citySearchInput.sendKeys(Keys.ARROW_DOWN);
        citySearchInput.sendKeys(Keys.ENTER);
    }

    public void clickEvents() {
        eventsTab.click();
    }

    public void clickMovies() {
        moviesTab.click();
    }

    public void clickLocation(){
        selectloc.click();
    }

    public void selectLocationcity(String city){
        getCitySearchInput.sendKeys(city);
    }

    public void selectPuneLoc(){
        puneLoc.click();
    }

    public String getLocAfterClick(){
        return getPuneLocAfterClick.getText();
    }

    public String getEntireLocAfterClick(){
        return getEntireAddressLocAfterClick.getText();
    }
}