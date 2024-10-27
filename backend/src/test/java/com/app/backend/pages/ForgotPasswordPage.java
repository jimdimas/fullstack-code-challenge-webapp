package com.app.backend.pages;

import org.openqa.selenium.By;

import static com.app.backend.utility.WaitUtilities.waitElementToLoad;

public class ForgotPasswordPage extends NonAuthorizedPage{

    By emailTextbox = By.id("email");
    By resetButton = By.xpath("//button[@type='submit']");
    By messageText = By.id("message");

    public void setEmail(String email){
        waitElementToLoad(emailTextbox);
        set(emailTextbox,email);
    }

    public void clickResetButton(){
        waitElementToLoad(resetButton);
        click(resetButton);
    }

    public String getMessageText(){
        waitElementToLoad(messageText);
        return find(messageText).getText();
    }
}
