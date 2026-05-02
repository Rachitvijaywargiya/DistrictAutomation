package org.district.automation.test;

import org.district.automation.base.BaseClass;
import org.district.automation.pages.LoginPage;
import org.district.automation.utility.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TC_21_LoginProfile_ButtonTest extends BaseClass {
    @Test
    public void verifyLogoWithPhoneWindow() {
        //Assert.fail();
        LoginPage loginPage = new LoginPage(driver);
        SoftAssert soft = new SoftAssert();
        String expectedHeadingMessage= ConfigReader.getString("mobilePopupMsgHeading");

        soft.assertFalse(loginPage.isPhoneNoPopupDisplayed());
        log.info("Login with mobile number popup window is not displayed till now");
        loginPage.clickProfileBtn();
        log.info("Dropdown menu clicked");

        String actualMessage = loginPage.messageDisplayed();

        soft.assertTrue(loginPage.isPhoneNoPopupDisplayed());
        soft.assertEquals(actualMessage,expectedHeadingMessage);
        soft.assertAll();

        log.info("Login with mobile number popup window is now displayed");
        log.debug("{} heading is displayed", actualMessage);

        Assert.assertTrue(loginPage.isSubHeadingDisplayed());
        log.info("Subheading message displayed");
    }
}
