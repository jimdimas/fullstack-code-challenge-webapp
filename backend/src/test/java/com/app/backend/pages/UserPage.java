package com.app.backend.pages;

import org.openqa.selenium.By;

import static com.app.backend.utility.WaitUtilities.waitElementToLoad;

public class UserPage extends AuthorizedPage{

    protected By testsButton = By.id("tests");
    protected By problemsButton = By.id("problems");
    protected By solutionsButton = By.id("solutions");


    public TestPage clickViewTests(){
        waitElementToLoad(testsButton);
        click(testsButton);
        return new TestPage();
    }
}
