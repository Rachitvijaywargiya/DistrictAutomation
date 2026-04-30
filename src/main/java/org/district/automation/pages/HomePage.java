package org.district.automation.pages;
import org.district.automation.utility.WaitUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage {
    private WebDriver driver;
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
    @FindBy(xpath = "//div[@class='dds-flex dds-flex-col dds-gap-[1px] dds-items-baseline']/span[text()='Pune']")
    private WebElement getPuneLocAfterClick;
    @FindBy(xpath = "//span[@style='color: var(--color-text-secondary); text-align: left;']")
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
    public boolean isSearchBoxPresent() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            return wait.until(
                    ExpectedConditions.refreshed(
                            ExpectedConditions.visibilityOf(searchBox)
                    )
            ).isDisplayed();
        } catch (Exception e) {
            return false;
        }
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
    public String getTheNameOfSelectedCity(){
        System.out.println(getCitySearchInput.getAttribute("value"));
        return getCitySearchInput.getAttribute("value");
//        return getCitySearchInput.getText();
    }
    public String getLocAfterClick(){
        return getPuneLocAfterClick.getText();
    }
    public String getEntireLocAfterClick(){
//        WaitUtils.sleep(3000);
        WaitUtils.waitForElementToBeClickable(driver, getEntireAddressLocAfterClick, 10);
        return getEntireAddressLocAfterClick.getText();
    }
}