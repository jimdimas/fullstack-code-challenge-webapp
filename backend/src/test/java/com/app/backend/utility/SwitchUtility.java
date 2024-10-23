package com.app.backend.utility;

import org.openqa.selenium.WebDriver;

public class SwitchUtility extends BaseUtility{

    private static WebDriver.TargetLocator switchToAlert(){
        return driver.switchTo();
    }

    public static String getAlertText(){
        return switchToAlert().alert().getText();
    }

    public static void acceptAlert(){
        switchToAlert().alert().accept();
    }
}
