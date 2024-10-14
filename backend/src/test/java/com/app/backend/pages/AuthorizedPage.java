package com.app.backend.pages;

import org.openqa.selenium.By;

public class AuthorizedPage extends BasePage{

    protected By logoutButton = By.id("logout");
    protected By profileButton = By.id("profile");
    protected By problemsButton = By.id("problems");

    public Boolean isAuthorized(){
        return find(profileButton).isDisplayed();
    }

    public HomePage logoutAction(){
        click(logoutButton);
        return new HomePage();
    }
}
