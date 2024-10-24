package com.app.backend.pages;

import org.openqa.selenium.By;

import static com.app.backend.utility.WaitUtilities.waitElementToLoad;

public class AuthorizedPage extends BasePage{

    protected By logoutButton = By.id("logout");
    protected By profileButton = By.id("profile");
    protected By problemsButton = By.id("problems");
    protected By testsButton = By.id("tests");


    public Boolean isAuthorized(){
        waitElementToLoad(profileButton);
        return find(profileButton).isDisplayed();
    }

    public TestPage clickViewTests(){
        waitElementToLoad(testsButton);
        click(testsButton);
        return new TestPage();
    }

    public ProblemPage clickViewProblems(){
        waitElementToLoad(problemsButton);
        click(problemsButton);
        return new ProblemPage();
    }
    public HomePage logoutAction(){
        click(logoutButton);
        return new HomePage();
    }
}
