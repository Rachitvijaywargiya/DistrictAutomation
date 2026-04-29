package org.district.automation.pages;

import org.district.automation.utility.WaitUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class LoginPage {
    private WebDriver driver;

    @FindBy(xpath = "//div[@class='dds-cursor-pointer' and @role='button']")
    private WebElement profileButton;

    @FindBy(xpath = "//label[contains(text(),'Enter your mobile number')]")
    private WebElement labelmessage;

    @FindBy(xpath = "(//div[contains(@class,'dds-relative')]/label/following-sibling::div)[1]")
    private WebElement subHeadingEle;

    @FindBy(css = "div[class ='dds-relative dds-text-center dds-px-5 dds-pt-5 dds-pb-3']")
    private WebElement phoneNoPopupEle;

    @FindBy(xpath = "//input[@inputmode='numeric']")
    private WebElement inputBoxPhoneNoEle;

    @FindBy(xpath = "//div[@id='select-container']")
    private WebElement dropDownEle;

    @FindBy(xpath = "//div[@class='dds-w-fit']")
    private List<WebElement> totalCountryListEle;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickProfileBtn() {
        profileButton.click();
    }

    public String messageDisplayed() {
        //Thread.sleep(3000);
        //WaitUtils.sleep(3000);
        WaitUtils.waitForElementToBeVisible(driver,labelmessage,3);
        return labelmessage.getText();
    }

    public boolean isSubHeadingDisplayed(){
        return subHeadingEle.isDisplayed();
    }

    public boolean isPhoneNoPopupDisplayed(){

        try {
            WaitUtils.waitForElementToBeVisible(driver,phoneNoPopupEle,5);
            return true;
        } catch (TimeoutException e) {
            return false;
        }

    }

    public String inputBoxValue(String number){
        inputBoxPhoneNoEle.sendKeys(number);
        String value = inputBoxPhoneNoEle.getDomAttribute("value");
        //System.out.println(value);
        return value;
    }

    public boolean checkNumbericInputValue(String number){
        String value = inputBoxValue(number);
        return value.matches("[0-9]*");
    }

    public void clearInputBoxData(){
        inputBoxPhoneNoEle.clear();
        inputBoxPhoneNoEle.click();
        inputBoxPhoneNoEle.sendKeys(Keys.CONTROL + "a");
        inputBoxPhoneNoEle.sendKeys(Keys.BACK_SPACE);

    }

    public boolean isDropdownVisible(){
        dropDownEle.click();
        return dropDownEle.isDisplayed();
    }

    public void displayCountriesOfDropDown(){
        List<WebElement> options = totalCountryListEle;
        for (WebElement option : options) {
            System.out.println(option.getText());
        }
    }

    public void countCountryOfDropDown(){
        List<WebElement> options = totalCountryListEle;
        System.out.println("Total countries: " + options.size());
    }

    public void selectCountryFromDropDown(String country){
        List<WebElement> options = totalCountryListEle;
        String ct = country.toLowerCase();
        for (WebElement option : options) {
            if(option.getText().toLowerCase().contains(ct)){
                option.click();
                break;
            }
        }
    }



}
