package com.app.backend.pages;

import org.openqa.selenium.By;

import static com.app.backend.utility.SelectUtilities.selectByVisibleText;
import static com.app.backend.utility.WaitUtilities.waitElementToLoad;

public class UploadProblemPage extends SupervisorPage {
    private final By questionTextarea = By.id("question");
    private final By difficultyDropdown = By.id("difficulty");
    private final By pointsNum = By.id("points");
    private final By submitButton = By.xpath("//button[@type='submit']");

    public void setQuestion(String text){
        waitElementToLoad(questionTextarea);
        set(questionTextarea,text);
    }

    public void setDifficulty(String difficulty){
        waitElementToLoad(difficultyDropdown);
        selectByVisibleText(difficultyDropdown,difficulty);
    }

    public void setPoints(String points){
        waitElementToLoad(pointsNum);
        set(pointsNum,points);
    }

    public ProfilePageSupervisor clickSubmit(){
        waitElementToLoad(submitButton);
        click(submitButton);
        return new ProfilePageSupervisor();
    }
}
