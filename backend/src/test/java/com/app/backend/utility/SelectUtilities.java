package com.app.backend.utility;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class SelectUtilities extends BaseUtility{

    private static Select findDropdown(By locator){
        return new Select(driver.findElement(locator));
    }

    public static void selectByVisibleText(By locator,String text){
        findDropdown(locator).selectByVisibleText(text);
    }
}
