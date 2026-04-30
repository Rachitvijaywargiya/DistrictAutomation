package org.district.automation.test;

import org.district.automation.base.BaseClass;
import org.district.automation.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TC_25_LoginProfileValidTest extends BaseClass {
    @Test
    public void verifyLoginProfileForValidTest(){
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
        loginPage.setPhoneNumber("9430154833");
        log.info("Entered phone number: {}", "9430154833");
        loginPage.clickContinueButton();
        //log.error("login error: {}", loginPage.captureErrorMessage());
        String actualMessage = loginPage.messageDisplayed();
        soft.assertTrue(loginPage.isPhoneNoPopupDisplayed());
        soft.assertEquals(actualMessage,"Enter OTP");
        soft.assertAll();
        Assert.assertTrue(loginPage.isSubHeadingDisplayed());

    }
}
