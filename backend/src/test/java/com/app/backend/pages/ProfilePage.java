package com.app.backend.pages;

import org.openqa.selenium.By;

public class ProfilePage extends BasePage{
    private By roleField = By.id("role");

    public Boolean isRoleDisplayed(){
        return find(roleField).isDisplayed();
    }
}
