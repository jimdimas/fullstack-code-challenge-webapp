package com.app.backend.pages;

import org.openqa.selenium.By;

import static com.app.backend.utility.JavascriptUtilities.scrollToElementJS;
import static com.app.backend.utility.WaitUtilities.waitElementToLoad;

public class TestResultPage extends StudentPage {

    public String checkTestResult(String title){
        By testResult= By.xpath("//tr[td[text()='" + title + "']]/td[3]");
        if (!waitElementToLoad(testResult)) return "";
        scrollToElementJS(testResult);
        return find(testResult).getText();
    }
}
