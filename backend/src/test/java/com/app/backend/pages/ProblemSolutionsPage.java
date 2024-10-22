package com.app.backend.pages;

import org.openqa.selenium.By;

import static com.app.backend.utility.JavascriptUtilities.clickJS;
import static com.app.backend.utility.WaitUtilities.waitElementToLoad;

public class ProblemSolutionsPage extends SupervisorPage{

    public ProfilePageSupervisor setStudentSolutionResult(
            String solution,
            String username,
            Boolean result){

        By locator = By.xpath("//tr[td[1][text()='"+solution+"'] and " +
                "td[3][text()='"+username+"']]/" +
                (result?"td[4]":"td[5]")); //get accept or reject link
        waitElementToLoad(locator);
        clickJS(locator);
        return new ProfilePageSupervisor();
    }
}
