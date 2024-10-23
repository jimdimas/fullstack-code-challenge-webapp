package com.app.backend.utility;

import org.openqa.selenium.WebDriver;

import static com.app.backend.utility.WaitUtilities.waitAlertToLoad;

public class SwitchUtility extends BaseUtility{

    private static WebDriver.TargetLocator switchToAlert(){
        return driver.switchTo();
    }

    public static String getAlertText(){
        waitAlertToLoad();
        return switchToAlert().alert().getText();
    }

    public static void acceptAlert(){
        waitAlertToLoad();
        switchToAlert().alert().accept();
    }
}
