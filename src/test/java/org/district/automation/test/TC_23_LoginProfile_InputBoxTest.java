package org.district.automation.test;

import org.district.automation.base.BaseClass;
import org.district.automation.pages.LoginPage;
import org.district.automation.utility.ConfigReader;
import org.district.automation.utility.ExcelUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_23_LoginProfile_InputBoxTest extends BaseClass {

    @Test
    public void verifyPhoneWindowInputBox() {
        LoginPage loginPage = new LoginPage(driver);
        String mobileNumber = ExcelUtil.getCellData("LoginData", 1, 2);

        loginPage.clickProfileBtn();
        log.info("Profile button is clicked");
        log.info("Login with mobile number popup window is now displayed");
        log.debug("Testing the functionality of number Input box");

        loginPage.setInputBoxValue(mobileNumber);
        log.info("Setting Input box value as {}" , mobileNumber);

        String inputBoxCurrentValue = loginPage.getInputBoxValue();

        Assert.assertNotEquals(inputBoxCurrentValue,mobileNumber);
        log.info("Input box current value {}",inputBoxCurrentValue);
        log.info("Current Input box value doesn't matched with user input");

        Assert.assertTrue(loginPage.checkNumericInputValue());
        log.info("Input box taking only Numeric value");
    }
}
