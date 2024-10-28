package com.app.backend.pages;

import org.openqa.selenium.By;

import static com.app.backend.utility.WaitUtilities.waitElementToLoad;

public class ResetPasswordPage extends NonAuthorizedPage{
    private By passwordTextbox = By.id("password");
    private By confirmPasswordTextbox = By.id("confirmPassword");
    private By submitButton = By.xpath("//button[@type='submit']");
    private By message = By.xpath("//*[@id=\"root\"]/div/div/div[2]");

    public void setPassword(String password){
        waitElementToLoad(passwordTextbox);
        set(passwordTextbox,password);
    }

    public void setConfirmPassword(String confirmPassword){
        waitElementToLoad(confirmPasswordTextbox);
        set(confirmPasswordTextbox,confirmPassword);
    }

    public void clickSubmitButton(){
        waitElementToLoad(submitButton);
        click(submitButton);
    }

    public String getMessageText(){
        waitElementToLoad(message);
        return find(message).getText();
    }
}
