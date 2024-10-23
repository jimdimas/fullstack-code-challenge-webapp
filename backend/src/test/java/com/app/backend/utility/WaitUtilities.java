package com.app.backend.utility;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtilities extends BaseUtility{

    public static Boolean waitElementToLoad(By locator){
        try {
            FluentWait fluentWait = new FluentWait(driver)
                    .withTimeout(Duration.ofSeconds(5))
                    .pollingEvery(Duration.ofMillis(500))
                    .ignoring(NoSuchElementException.class,
                            StaleElementReferenceException.class);
            fluentWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (TimeoutException e){
            return false;
        }
    }

    public static void waitAlertToLoad(){
        try {
            FluentWait fluentWait = new FluentWait(driver)
                    .withTimeout(Duration.ofSeconds(5))
                    .pollingEvery(Duration.ofMillis(500))
                    .ignoring(NoSuchElementException.class,
                            StaleElementReferenceException.class);
            fluentWait.until(ExpectedConditions.alertIsPresent());
        } catch (Exception e){

        }
    }
}
