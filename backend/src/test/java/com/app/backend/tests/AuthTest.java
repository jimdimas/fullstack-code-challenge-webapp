package com.app.backend.tests;

import com.app.backend.model.User;
import com.app.backend.pages.ProfilePage;
import com.app.backend.pages.RegisterPage;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static com.app.backend.utility.SwitchUtility.getAlertText;

public class AuthTest extends UnauthorizedBaseTest{

    @Test
    @Parameters({"Username","Email","Password","Result"})
    public void invalidRegister(
            String username,
            String email,
            String password,
            String expectedResult){
        RegisterPage registerPage = homePage.showRegisterPage();
        registerPage.setUsername(username);
        registerPage.setEmail(email);
        registerPage.setPassword(password);
        registerPage.clickSubmitButton();
        String actualResult = getAlertText();
        Assert.assertEquals(actualResult,expectedResult);
    }

    @Test
    @Parameters({"Username","Email","Password"})
    public void validRegister(
            String username,
            String email,
            String password
    ){
        RegisterPage registerPage = homePage.showRegisterPage();
        registerPage.setUsername(username);
        registerPage.setEmail(email);
        registerPage.setPassword(password);
        ProfilePage profilePage = registerPage.clickSubmitButton();
        Assert.assertTrue(profilePage.isAuthorized());
    }
}
