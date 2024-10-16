package com.app.backend.utility;

import com.app.backend.pages.BasePage;
import org.openqa.selenium.WebDriver;

public class BaseUtility {

    public static WebDriver driver;

    public static void setUtilityDriver(){
        driver = BasePage.driver;
    }
}
