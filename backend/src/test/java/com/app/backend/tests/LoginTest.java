package com.app.backend.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest{

    @Test
    public void testLoginError(){
        driver.get(baseUrl+"/login");
        loginPage.setUsername("random_name");
        loginPage.setPassword("u4hwrr");
        var ProfilePage = loginPage.clickLoginButton();
        String errorMessage = loginPage.getErrorMessage();
        Assert.assertEquals(errorMessage,"Invalid Credentials");
    }
}
