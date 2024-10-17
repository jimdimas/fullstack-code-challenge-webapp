package com.app.backend.pages;

import org.openqa.selenium.By;

import static com.app.backend.utility.WaitUtilities.waitElementToLoad;

public class UserPage extends AuthorizedPage{

    protected By testsButton = By.id("tests");
    protected By problemsButton = By.id("problems");
    protected By solutionsButton = By.id("solutions");
    protected By testResultsButton = By.id("results");


    public TestPage clickViewTests(){
        waitElementToLoad(testsButton);
        click(testsButton);
        return new TestPage();
    }

    public TestResultPage clickViewResults(){
        waitElementToLoad(testResultsButton);
        click(testResultsButton);
        return new TestResultPage();
    }
}
