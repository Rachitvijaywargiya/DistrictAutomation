package org.district.automation.test;

import org.district.automation.base.BaseClass;
import org.district.automation.pages.LoginPage;
import org.district.automation.utility.ConfigReader;
import org.district.automation.utility.ExcelUtil;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TC_26_LoginProfile_ValidTest extends BaseClass {
    @Test
    public void verifyLoginProfileForValidTest(){
        LoginPage loginPage = new LoginPage(driver);
        SoftAssert soft = new SoftAssert();
//        String countryIndia= ConfigReader.getString("country_India");
//        String validMobileNumber1=ConfigReader.getString("validMobileNumber1");
        String expectedHeadingMessage= ConfigReader.getString("mobilePopupHeadingAfterContinue");
        String countryIndia = ExcelUtil.getCellData("LoginData", 4, 1);
        String validMobileNumber1 = ExcelUtil.getCellData("LoginData", 4, 2);
       // String expectedHeadingMessage = ExcelUtil.getCellData("LoginData", 4, 3);

        loginPage.clickProfileBtn();
        log.info("Profile button is clicked");
        loginPage.clickDropdownMenu();
        log.info("Dropdown menu clicked");
        Assert.assertTrue(loginPage.isDropdownVisible());
        log.info("Country code dropdown is visible");
        loginPage.selectCountryFromDropDown(countryIndia);

        String currentSelectedCountry=loginPage.selectedCountryName();

        Assert.assertTrue(currentSelectedCountry.contains(countryIndia));
        log.info("Selected country: {}",currentSelectedCountry);
        loginPage.setPhoneNumber(validMobileNumber1);

        String inputBoxCurrentValue = loginPage.getInputBoxValue();

        log.debug("Entered phone number: {}", inputBoxCurrentValue);
        loginPage.clickContinueButton();
        log.info("Continue button is clicked");

        soft.assertTrue(loginPage.isEnteredOTPDisplayed());
        soft.assertTrue(loginPage.isSubHeadingDisplayed());
        String actualMessage = loginPage.getHeadingMessage();
        soft.assertEquals(actualMessage,expectedHeadingMessage);
        soft.assertAll();

        log.info("OTP popup window is displayed");
        log.debug("{} heading is displayed", actualMessage);
        log.info("Subheading message displayed");

        Assert.assertTrue(loginPage.isOtpInputFieldsVisible());
        log.info("Input fields of OTP is Displayed");
        Assert.assertFalse(loginPage.isContinueBtnDisable());
        log.info("Continue button is disabled");

    }
}

