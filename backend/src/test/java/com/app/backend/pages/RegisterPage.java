package com.app.backend.pages;

import org.openqa.selenium.By;

import static com.app.backend.utility.WaitUtilities.waitElementToLoad;

public class RegisterPage extends NonAuthorizedPage{

    By usernameTextbox = By.id("username");
    By emailTextbox = By.id("email");
    By passwordTextbox = By.id("password");
    By confirmPasswordTextbox = By.id("confirmPassword");
    By schoolTextbox = By.id("school");
    By submitButton = By.id("submit");
    By messageTextbox = By.id("message");

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

    public void setConfirmPassword(String confirmPassword){
        waitElementToLoad(confirmPasswordTextbox);
        set(confirmPasswordTextbox,confirmPassword);
    }

    public void setSchool(String school){
        waitElementToLoad(schoolTextbox);
        set(schoolTextbox,school);
    }
    public ProfilePage clickSubmitButton(){
        waitElementToLoad(submitButton);
        click(submitButton);
        return new ProfilePage();
    }

    public String getMessage(){
        waitElementToLoad(messageTextbox);
        return find(messageTextbox).getText();
    }
}
