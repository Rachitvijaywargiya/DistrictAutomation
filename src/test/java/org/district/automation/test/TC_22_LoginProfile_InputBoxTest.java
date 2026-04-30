package org.district.automation.test;

import org.district.automation.base.BaseClass;
import org.district.automation.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_22_LoginProfile_InputBoxTest extends BaseClass {
    @Test
    public void verifyPhoneWindowInputBox() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickProfileBtn();
        loginPage.setInputBoxValue("12ab45@#");
        String inputBoxCurrentValue = loginPage.getInputBoxValue();
        Assert.assertNotEquals(inputBoxCurrentValue,"12ab45@#");
        //loginPage.clearInputBoxData();
        Assert.assertTrue(loginPage.checkNumbericInputValue());
    }
}
