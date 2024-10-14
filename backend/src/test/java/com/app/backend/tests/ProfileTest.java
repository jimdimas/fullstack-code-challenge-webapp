package com.app.backend.tests;

import com.app.backend.pages.HomePage;
import com.app.backend.pages.ProfilePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProfileTest extends BaseTest{
    private ProfilePage profilePage;
    @Test(priority = 1)
    public void testUserLogin() throws InterruptedException {
        driver.get(baseUrl+"/login");
        profilePage = loginPage.loginAction("jimdim","123456");
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
