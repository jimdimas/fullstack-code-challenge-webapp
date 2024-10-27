package com.app.backend.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.app.backend.utility.JavascriptUtilities.clickJS;
import static com.app.backend.utility.WaitUtilities.waitElementToLoad;


public class LoginPage extends NonAuthorizedPage{

    private By usernameField = By.id("username");
    private By passwordField = By.id("password");
    private By loginButton = By.id("submit");
    private By forgotPasswordLink = By.xpath("//*[@id=\"root\"]/div/div/div/form/div/p/a");
    private By messageText = By.id("message");

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
        waitElementToLoad(messageText);
        return find(messageText).getText();
    }

    public ForgotPasswordPage clickForgotPasswordLink(){
        waitElementToLoad(forgotPasswordLink);
        clickJS(forgotPasswordLink);
        return new ForgotPasswordPage();
    }
}
