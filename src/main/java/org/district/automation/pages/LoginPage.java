package org.district.automation.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.district.automation.utility.WaitUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class LoginPage {
    private WebDriver driver;
    protected static final Logger log = LogManager.getLogger(LoginPage.class);
    private JavascriptExecutor js;

    @FindBy(xpath = "//div[@class='dds-cursor-pointer' and @role='button']")
    private WebElement profileButton;
    @FindBy(xpath = "//div[contains(@class,'dds-relative dds-text-center')]/label")
    public WebElement labelMessage;
    @FindBy(xpath = "(//div[contains(@class,'dds-relative')]/label/following-sibling::div)[1]")
    private WebElement subHeadingEle;
    @FindBy(css = "div[class ='dds-relative dds-text-center dds-px-5 dds-pt-5 dds-pb-3']")
    private WebElement phoneNoPopupEle;
    @FindBy(xpath = "//input[@inputmode='numeric']")
    private WebElement inputBoxPhoneNoEle;
    @FindBy(xpath = "//div[@id='select-container']")
    private WebElement dropDownEle;
    @FindBy(xpath = "//div[@class='dds-w-fit']")
    public List<WebElement> totalCountryListEle;
    @FindBy(xpath = "//div[@id='select-container']//img")
    private WebElement countryImgEle;
    @FindBy(xpath = "//button[contains(text(),'Continue')]")
    private WebElement continueBtnEle;
    @FindBy(xpath = "//div[@class='dds-flex dds-justify-center dds-items-center dds-mb-2.5 dds-gap-2 dds-text-base']/following-sibling::p")
    private WebElement errorMessage;
    @FindBy(css="div[class ='dds-relative'] label")
    private WebElement headingMessageEle;
    @FindBy(xpath = "//div[contains(@class,'dds-gap-[3%]')]")
    private WebElement otpFieldsEle;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    public void clickProfileBtn() {
        profileButton.click();
    }

    public String messageDisplayed() {
        return WaitUtils.waitForElementToBeVisible(driver,labelMessage,4).getText();
    }

    public boolean isSubHeadingDisplayed(){
        return subHeadingEle.isDisplayed();
    }

    public boolean isPhoneNoPopupDisplayed(){

        try {
            return WaitUtils.waitForElementToBeVisible(driver,phoneNoPopupEle,5).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }

    }

    public void setInputBoxValue(String number){
        inputBoxPhoneNoEle.sendKeys(number);
    }

    public String getInputBoxValue(){
        return inputBoxPhoneNoEle.getDomAttribute("value");
    }

    public void setPhoneNumber(String number){
        inputBoxPhoneNoEle.sendKeys(number);
    }

    public boolean checkNumericInputValue(){
        String value = getInputBoxValue();
        return value.matches("[0-9]*");
    }

    public void clearInputBoxData(){
        inputBoxPhoneNoEle.clear();
        inputBoxPhoneNoEle.click();
        inputBoxPhoneNoEle.sendKeys(Keys.CONTROL + "a");
        inputBoxPhoneNoEle.sendKeys(Keys.BACK_SPACE);

    }

    public boolean isDropdownVisible(){
        return dropDownEle.isDisplayed();
    }

    public void clickDropdownMenu(){dropDownEle.click();}

    public void displayCountriesOfDropDown(){
        List<WebElement> options = totalCountryListEle;
        for (WebElement option : options) {
            log.debug(option.getText());
        }
    }

    public void countCountryOfDropDown(){
        List<WebElement> options = totalCountryListEle;
        log.info("Total countries: {}", options.size());
    }

    public void selectCountryFromDropDown(String country){
        String ct = country.toLowerCase();

        for (WebElement option : totalCountryListEle) {
            if (option.getText().toLowerCase().contains(ct)) {
                js.executeScript("arguments[0].scrollIntoView(true);", option);
                WaitUtils.waitForElementToBeClickable(driver,option,10);
                option.click();
                break;
            }
        }

    }

    public String selectedCountryName(){
        return countryImgEle.getDomAttribute("alt");
    }

    public void clickContinueButton(){
        WaitUtils.clickAndWaitForVisibility(driver,continueBtnEle,headingMessageEle,30);
        WaitUtils.sleep(300);
    }

    public String captureErrorMessage(){
        return errorMessage.getText();
    }

    public boolean isEnteredOTPDisplayed(){
        return headingMessageEle.isDisplayed();
    }

    public String getHeadingMessage(){
        return headingMessageEle.getText();
    }

    public boolean isOtpInputFieldsVisible(){
        return otpFieldsEle.isDisplayed();
    }

    public boolean isContinueBtnDisable() {
        try {
            return !continueBtnEle.isEnabled();
        } catch (NoSuchElementException e) {
            return false;
        }
    }




}
