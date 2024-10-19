package com.app.backend.pages;

import org.openqa.selenium.By;

import static com.app.backend.utility.WaitUtilities.waitElementToLoad;

public class ProblemPage extends AuthorizedPage{

    public Boolean checkProblemExists(String question){
        By locator = By.xpath("//tr/td[text()='"+question+"']");
        waitElementToLoad(locator);
        return true;
    }
}
