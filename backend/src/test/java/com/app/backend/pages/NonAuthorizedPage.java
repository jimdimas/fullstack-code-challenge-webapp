package com.app.backend.pages;

import org.openqa.selenium.By;

public class NonAuthorizedPage extends BasePage{

    protected By loginButton = By.id("login");
    protected By registerButton = By.id("register");

    public LoginPage showLoginPage(){
        click(loginButton);
        return new LoginPage();
    }

    public Boolean isNotAuthorized(){
        return find(loginButton).isDisplayed();
    }
}
