package com.app.backend.pages;

import org.openqa.selenium.By;

import static com.app.backend.utility.WaitUtilities.waitElementToLoad;

public class SupervisorPage extends AuthorizedPage{
    protected By uploadProblemButton = By.id("uploadProblem");
    protected By uploadTestButton = By.id("uploadTest");

    public UploadTestPage clickUploadTest(){
        waitElementToLoad(uploadTestButton);
        click(uploadTestButton);
        return new UploadTestPage();
    }
}
