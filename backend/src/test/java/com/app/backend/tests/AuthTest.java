package com.app.backend.tests;

import com.app.backend.pages.ProfilePage;
import com.app.backend.pages.RegisterPage;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static com.app.backend.utility.SwitchUtility.getAlertText;

public class AuthTest extends UnauthorizedBaseTest{

    private void setFormData(
        String username,
        String email,
        String password,
        String confirmPassword,
        String school,
        RegisterPage registerPage
    ){
        registerPage = homePage.showRegisterPage();
        registerPage.setUsername(username);
        registerPage.setPassword(password);
        registerPage.setConfirmPassword(confirmPassword);
        registerPage.setEmail(email);
        registerPage.setSchool(school);
    }
    @Test
    @Parameters({"Username","Email","Password","ConfirmPassword","School","Result"})
    public void dataValidationRegister(
            String username,
            String email,
            String password,
            String confirmPassword,
            String school,
            String expectedResult
    ){
        RegisterPage registerPage = homePage.showRegisterPage();
        setFormData(
            username,
            email,
            password,
            confirmPassword,
            school,
            registerPage);
        Assert.assertTrue(registerPage.getMessage().contains(expectedResult));
    }

    @Test
    @Parameters({"Username","Email","Password","ConfirmPassword","School","Result"})
    public void invalidRegister(
            String username,
            String email,
            String password,
            String confirmPassword,
            String school,
            String expectedResult){
        RegisterPage registerPage = homePage.showRegisterPage();
        setFormData(
                username,
                email,
                password,
                confirmPassword,
                school,
                registerPage
        );
        registerPage.clickSubmitButton();
        String actualResult = getAlertText();
        Assert.assertEquals(actualResult,expectedResult);
    }

    @Test
    @Parameters({"Username","Email","Password","ConfirmPassword","School","Result"})
    public void validRegister(
            String username,
            String email,
            String password,
            String confirmPassword,
            String school,
            String expectedResult
    ){
        RegisterPage registerPage = homePage.showRegisterPage();
        setFormData(
                username,
                email,
                password,
                confirmPassword,
                school,
                registerPage
        );
        ProfilePage profilePage = registerPage.clickSubmitButton();
        Assert.assertTrue(profilePage.isAuthorized());
    }
}
