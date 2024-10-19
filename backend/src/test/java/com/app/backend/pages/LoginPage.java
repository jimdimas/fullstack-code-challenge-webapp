package com.app.backend.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.app.backend.utility.WaitUtilities.waitElementToLoad;


public class LoginPage extends NonAuthorizedPage{

    private By usernameField = By.id("username");
    private By passwordField = By.id("password");
    private By loginButton = By.id("submit");

    public void setUsername(String username){
        waitElementToLoad(usernameField);
        set(usernameField,username);
    }

    public void setPassword(String password){
        waitElementToLoad(passwordField);
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

    public ProfilePageSupervisor loginActionSupervisor(String username, String password){
        setUsername(username);
        setPassword(password);
        click(loginButton);
        return new ProfilePageSupervisor();
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
