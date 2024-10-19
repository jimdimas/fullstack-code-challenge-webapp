package com.app.backend.tests;

import com.app.backend.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest{

    @Test
    @Parameters({"Username","Password"})
    public void testLoginError(String username,String password){
        LoginPage loginPage = homePage.showLoginPage();
        loginPage.loginAction(username,password);
        String errorMessage = loginPage.getErrorMessage();
        Assert.assertEquals(errorMessage,"Invalid Credentials");
    }
}
