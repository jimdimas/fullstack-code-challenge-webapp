package com.app.backend.pages;

import org.openqa.selenium.By;

import static com.app.backend.utility.WaitUtilities.waitElementToLoad;

public class SolveProblemPage extends StudentPage {

    private final By contentTextarea = By.id("content");
    private final By submitButton = By.xpath("//button[text()='Submit Solution']");

    public void setAnswer(String content){
        waitElementToLoad(contentTextarea);
        set(contentTextarea,content);
    }

    public ProfilePage clickSubmitButton(){
        waitElementToLoad(submitButton);
        click(submitButton);
        return new ProfilePage();
    }
}
