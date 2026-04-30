package org.district.automation.test;

import org.district.automation.base.BaseClass;
import org.district.automation.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TC_23_LoginProfileDropdownTest extends BaseClass {
    @Test
    public void verifyDropdownOfCommonLoginProfile(){
        LoginPage loginPage = new LoginPage(driver);
        SoftAssert soft = new SoftAssert();
        loginPage.clickProfileBtn();
        log.info("Profile button is clicked");
        loginPage.clickDropdownMenu();
        log.info("Dropdown menu clicked");
        Assert.assertTrue(loginPage.isDropdownVisible());
        log.info("Country code dropdown is visible");
        log.debug("Displaying all countries List");
        loginPage.displayCountriesOfDropDown();
        loginPage.countCountryOfDropDown();
        loginPage.selectCountryFromDropDown("Algeria");
        Assert.assertTrue(loginPage.selectedCountryName().contains("Algeria"));
        log.info("Selected country: Algeria");
    }
}
