package com.app.backend.pages;

import org.openqa.selenium.By;

import static com.app.backend.utility.WaitUtilities.waitElementToLoad;

public class StudentPage extends AuthorizedPage{
    protected By solutionsButton = By.id("solutions");
    protected By testResultsButton = By.id("results");

    public TestResultPage clickViewResults(){
        waitElementToLoad(testResultsButton);
        click(testResultsButton);
        return new TestResultPage();
    }

    public SolutionPage clickViewSolutions(){
        waitElementToLoad(solutionsButton);
        click(solutionsButton);
        return new SolutionPage();
    }
}
