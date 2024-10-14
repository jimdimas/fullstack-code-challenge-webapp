package com.app.backend.pages;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LoginPage extends NonAuthorizedPage{

    private By usernameField = By.id("username");
    private By passwordField = By.id("password");
    private By loginButton = By.id("submit");

    public void setUsername(String username){
        set(usernameField,username);
    }

    public void setPassword(String password){
        set(passwordField,password);
    }

    public ProfilePage clickLoginButton(){
        click(loginButton);
        return new ProfilePage();
    }

    public ProfilePage loginAction(String username,String password){
        setUsername(username);
        setPassword(password);
        return clickLoginButton();
    }

    public String getErrorMessage(){
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(100));
        if (wait.until(ExpectedConditions.alertIsPresent())==null){
            return "";
        } else {
            return "Invalid Credentials";
        }
    }
}
