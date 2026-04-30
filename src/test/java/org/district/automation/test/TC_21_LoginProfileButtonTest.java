package org.district.automation.test;

import org.district.automation.base.BaseClass;
import org.district.automation.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TC_21_LoginProfileButtonTest extends BaseClass {
    @Test
    public void verifyLogoWithPhoneWindow() {
        //Assert.fail();
        LoginPage loginPage = new LoginPage(driver);
        SoftAssert soft = new SoftAssert();

        soft.assertFalse(loginPage.isPhoneNoPopupDisplayed());
        loginPage.clickProfileBtn();
        String actualMessage = loginPage.messageDisplayed();

        soft.assertTrue(loginPage.isPhoneNoPopupDisplayed());
        soft.assertEquals(actualMessage,"Enter your mobile number");
        soft.assertAll();

        Assert.assertTrue(loginPage.isSubHeadingDisplayed());
    }
}
