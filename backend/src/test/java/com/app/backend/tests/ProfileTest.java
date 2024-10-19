package com.app.backend.tests;

import com.app.backend.pages.HomePage;
import com.app.backend.pages.LoginPage;
import com.app.backend.pages.ProfilePage;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ProfileTest extends BaseTest{
    private ProfilePage profilePage;
    @Test(priority = 1)
    @Parameters({"Username","Password"})
    public void testUserLogin(String username,String password) {
        LoginPage loginPage = homePage.showLoginPage();
        profilePage = loginPage.loginAction(username,password);
        Assert.assertTrue(profilePage.isAuthorized());
    }

    @Test(priority = 2)
    public void testLogout() {
        HomePage homePage = profilePage.logoutAction();
        Assert.assertTrue(homePage.isNotAuthorized());
    }
}
