package com.app.backend.tests;

import com.app.backend.pages.HomePage;
import com.app.backend.pages.LoginPage;
import com.app.backend.pages.ProfilePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProfileTest extends BaseTest{
    private ProfilePage profilePage;
    @Test(priority = 1)
    public void testUserLogin() throws InterruptedException {
        LoginPage loginPage = homePage.showLoginPage();
        profilePage = loginPage.loginAction("user","123456");
        Thread.sleep(500);
        Assert.assertTrue(profilePage.isAuthorized());
    }

    @Test(priority = 2)
    public void testLogout() throws InterruptedException {
        HomePage homePage = profilePage.logoutAction();
        Thread.sleep(500);
        Assert.assertTrue(homePage.isNotAuthorized());
    }
}
