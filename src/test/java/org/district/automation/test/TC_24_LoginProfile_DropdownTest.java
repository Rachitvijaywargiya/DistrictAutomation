package org.district.automation.test;

import org.district.automation.base.BaseClass;
import org.district.automation.pages.LoginPage;
import org.district.automation.utility.ConfigReader;
import org.district.automation.utility.ExcelUtil;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TC_24_LoginProfile_DropdownTest extends BaseClass {
    @Test
    public void verifyDropdownOfCommonLoginProfile(){
        LoginPage loginPage = new LoginPage(driver);
        SoftAssert soft = new SoftAssert();
//        String country= ConfigReader.getString("country");
        String country = ExcelUtil.getCellData("LoginData", 2, 1);

        loginPage.clickProfileBtn();
        log.info("Profile button is clicked");
        loginPage.clickDropdownMenu();
        log.info("Dropdown menu clicked");
        Assert.assertTrue(loginPage.isDropdownVisible());
        log.info("Country code dropdown is visible");

        log.debug("Displaying all countries List");
        loginPage.displayCountriesOfDropDown();
        loginPage.countCountryOfDropDown();
        loginPage.selectCountryFromDropDown(country);

        String selectedCountry = loginPage.selectedCountryName();

        Assert.assertTrue(selectedCountry.contains(country));
        log.info("Selected country: {}",selectedCountry);
    }
}
