package org.district.automation.pages;
import org.district.automation.utility.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    private WebDriver driver;
    @FindBy(css = "img[alt='app-store'][style='filter:invert(1)']")
    private WebElement logo;
    @FindBy(xpath = "//input[@placeholder='Search city, area or locality']")
    private WebElement citySearchInput;
    @FindBy(xpath = "//span[contains(@class,'dds-text-primary')]/following-sibling::span[contains(@style,'text-align: left;')]")
    private WebElement locationText;
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
    public String getDisplayedLocation() {
        return locationText.getText();
    }
    public boolean isSearchBoxPresent() {
        try {
            return WaitUtils.waitForRefreshedAndVisible(driver,searchBox,15).isDisplayed();
        } catch (Exception e) {
            return false;
        }
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
//    public String getTheNameOfSelectedCity(){
//        return getCitySearchInput.getAttribute("value");
//    }
    public String getLocAfterClick(){
        return getPuneLocAfterClick.getText();
    }
    public String getEntireLocAfterClick(){
        WaitUtils.waitForElementToBeClickable(driver, getEntireAddressLocAfterClick, 15);
        return getEntireAddressLocAfterClick.getText();
    }
}