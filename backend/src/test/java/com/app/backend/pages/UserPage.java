package com.app.backend.pages;

import org.openqa.selenium.By;

public class UserPage extends AuthorizedPage{

    protected By testsButton = By.id("tests");
    protected By problemsButton = By.id("problems");
    protected By solutionsButton = By.id("solutions");


    public TestPage clickViewTests(){
        click(testsButton);
        return new TestPage();
    }
}
