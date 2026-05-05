package org.district.automation.test;

import org.district.automation.base.BaseClass;
import org.district.automation.pages.LoginPage;
import org.district.automation.utility.ConfigReader;
import org.district.automation.utility.ExcelUtil;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TC_25_LoginProfile_InvalidTest extends BaseClass {
    @Test
    public void verifyLoginProfileForInvalidTest() {
        LoginPage loginPage = new LoginPage(driver);
        SoftAssert soft = new SoftAssert();
        String countryIndia = ExcelUtil.getCellData("LoginData", 3, 1);
        String inValidMobileNumber1 = ExcelUtil.getCellData("LoginData", 3, 2);

        String otherCountry = ExcelUtil.getCellData("LoginData", 2, 1);
        String inValidMobileNumber2 = ExcelUtil.getCellData("LoginData", 3, 2);

        loginPage.clickProfileBtn();
        log.info("Profile button is clicked");

        //Test Case For Country India
        loginPage.clickDropdownMenu();
        log.info("Dropdown menu clicked");
        Assert.assertTrue(loginPage.isDropdownVisible());
        log.info("Country code dropdown is visible");
        loginPage.selectCountryFromDropDown(countryIndia);

        String currentSelectedCountry=loginPage.selectedCountryName();

        Assert.assertTrue(currentSelectedCountry.contains(countryIndia));
        log.info("Selected country: {}",currentSelectedCountry);
        loginPage.setPhoneNumber(inValidMobileNumber1);

        String inputBoxCurrentValue = loginPage.getInputBoxValue();

        log.info("Entered phone number: {}", inputBoxCurrentValue);
        loginPage.clickContinueButton();
        log.error("login error: {}", loginPage.captureErrorMessage());

        //Test Case For Other Countries
        loginPage.clickDropdownMenu();
        log.info("Dropdown menu clicked");
        Assert.assertTrue(loginPage.isDropdownVisible());
        log.info("Country code dropdown is visible");
        loginPage.selectCountryFromDropDown(otherCountry);

        currentSelectedCountry=loginPage.selectedCountryName();

        Assert.assertTrue(currentSelectedCountry.contains(otherCountry));
        log.info("Selected country: {}",currentSelectedCountry);
        loginPage.clearInputBoxData();
        log.info("cleared input box data");
        loginPage.setPhoneNumber(inValidMobileNumber2);

        inputBoxCurrentValue = loginPage.getInputBoxValue();

        log.info("Entered phone number: {}", inputBoxCurrentValue);
        loginPage.clickContinueButton();
        log.error("login error: {}", loginPage.captureErrorMessage());
    }
}
