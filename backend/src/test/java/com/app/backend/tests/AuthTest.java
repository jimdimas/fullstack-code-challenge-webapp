package com.app.backend.tests;

import com.app.backend.pages.*;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class AuthTest extends UnauthorizedBaseTest{

    private void setRegisterFormData(
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
        setRegisterFormData(
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
        setRegisterFormData(
                username,
                email,
                password,
                confirmPassword,
                school,
                registerPage
        );
        registerPage.clickSubmitButton();
        String actualResult = registerPage.getMessage();
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
        setRegisterFormData(
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

    @Test
    @Parameters({"Username","Password","Result"})
    public void invalidLogin(
            String username,
            String password,
            String expectedResult){
        LoginPage loginPage = homePage.showLoginPage();
        loginPage.loginAction(username,password);
        Assert.assertEquals(loginPage.getErrorMessage(),expectedResult);
    }

    @Test
    @Parameters({"Email","Result","Submit"})
    public void validateForgotPassword(
            String email,
            String expectedResult,
            Boolean submit
    ){
        ForgotPasswordPage forgotPasswordPage = homePage.showLoginPage().clickForgotPasswordLink();
        forgotPasswordPage.setEmail(email);
        if (submit) forgotPasswordPage.clickResetButton();
        Assert.assertEquals(forgotPasswordPage.getMessageText(),expectedResult);
    }

    @Test
    @Parameters({"URL","Password","ConfirmPassword","SampleToken","Submit","Result"})
    public void validateResetPassword(
            String url,
            String password,
            String confirmPassword,
            @Optional String sampleToken,
            Boolean submit,
            String result
    ){
        driver.get(url+"/resetPassword?token="+sampleToken);    //can get to this page only by clicking link in email
        ResetPasswordPage resetPasswordPage = new ResetPasswordPage();
        resetPasswordPage.setPassword(password);
        resetPasswordPage.setConfirmPassword(confirmPassword);
        if (submit) resetPasswordPage.clickSubmitButton();
        Assert.assertEquals(result,resetPasswordPage.getMessageText());
    }
}
