package com.app.backend.pages;

import org.openqa.selenium.By;

import static com.app.backend.utility.SwitchUtility.getAlertText;
import static com.app.backend.utility.WaitUtilities.waitElementToLoad;

public class RegisterPage extends NonAuthorizedPage{

    By usernameTextbox = By.id("username");
    By emailTextbox = By.id("email");
    By passwordTextbox = By.id("password");
    By submitButton = By.id("submit");

    public void setUsername(String username){
        waitElementToLoad(usernameTextbox);
        set(usernameTextbox,username);
    }

    public void setEmail(String email){
        waitElementToLoad(emailTextbox);
        set(emailTextbox,email);
    }

    public void setPassword(String password){
        waitElementToLoad(passwordTextbox);
        set(passwordTextbox,password);
    }

    public void clickSubmitButton(){
        waitElementToLoad(submitButton);
        click(submitButton);
    }
}
