package com.app.backend.pages;

import org.openqa.selenium.By;

import static com.app.backend.utility.WaitUtilities.waitElementToLoad;

public class SolutionPage extends StudentPage{

    public Boolean solutionExists(String question){
        By locator = By.xpath("//tr[td[text()='"+question+"']]");
        return waitElementToLoad(locator); //if element doesnt exist , test will fail after waitElementToLoad
    }
}
