package org.district.automation.test;

import org.district.automation.base.BaseClass;
import org.district.automation.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TC_24_LoginProfileInvalidTest extends BaseClass {
    @Test
    public void verifyLoginProfileForInvalidTest() {
        LoginPage loginPage = new LoginPage(driver);
        SoftAssert soft = new SoftAssert();
        loginPage.clickProfileBtn();
        log.info("Profile button is clicked");

        loginPage.clickDropdownMenu();
        log.info("Dropdown menu clicked");
        Assert.assertTrue(loginPage.isDropdownVisible());
        log.info("Country code dropdown is visible");
        loginPage.selectCountryFromDropDown("India");
        Assert.assertTrue(loginPage.selectedCountryName().contains("India"));
        log.info("Selected country: India");
        loginPage.setPhoneNumber("94301548445");
        log.info("Entered phone number: {}", "94301548445");
        loginPage.clickContinueButton();
        log.error("login error: {}", loginPage.captureErrorMessage());


        loginPage.clickDropdownMenu();
        log.info("Dropdown menu clicked");
        Assert.assertTrue(loginPage.isDropdownVisible());
        log.info("Country code dropdown is visible");
        loginPage.selectCountryFromDropDown("Algeria");
        Assert.assertTrue(loginPage.selectedCountryName().contains("Algeria"));
        log.info("Selected country: Algeria");
        loginPage.clearInputBoxData();
        log.info("cleared input box data");
        loginPage.setPhoneNumber("67544788934");
        log.info("Entered phone number: {}", "67544788934");
        loginPage.clickContinueButton();
        log.error("login error: {}", loginPage.captureErrorMessage());
    }
}
