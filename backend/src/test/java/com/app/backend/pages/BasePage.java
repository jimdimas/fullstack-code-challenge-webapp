package com.app.backend.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.app.backend.utility.WaitUtilities.waitElementToLoad;

public class BasePage {

    public static WebDriver driver;
    protected String baseUrl="http://localhost:3000";

    public void setDriver(WebDriver _driver){
        driver=_driver;
    }

    protected WebElement find(By locator){
        return driver.findElement(locator);
    }

    protected void set(By locator,String text){
        waitElementToLoad(locator);
        find(locator).clear();
        find(locator).sendKeys(text);
    }

    protected void click(By locator){
        find(locator).click();
    }
}
