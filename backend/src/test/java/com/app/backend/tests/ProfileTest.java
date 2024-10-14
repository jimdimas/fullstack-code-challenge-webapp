package com.app.backend.tests;

import com.app.backend.pages.ProfilePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProfileTest extends BaseTest{

    @Test
    public void testUserLogin() throws InterruptedException {
        driver.get(baseUrl+"/login");
        ProfilePage profilePage = loginPage.loginAction("jimdim","123456");
        Thread.sleep(500);
        Assert.assertTrue(profilePage.isRoleDisplayed());
    }
}
