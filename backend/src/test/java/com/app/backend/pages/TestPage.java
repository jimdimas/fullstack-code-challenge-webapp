package com.app.backend.pages;


import org.openqa.selenium.By;

import static com.app.backend.utility.JavascriptUtilities.*;

public class TestPage extends AuthorizedPage{

    public SolveTestPage clickTestByTitle(String title){
        By testLink = By.xpath("//tr[td[text()='" + title + "']]/td[3]/a");
        scrollToElementJS(testLink);
        clickJS(testLink);
        return new SolveTestPage();
    }

    public Boolean isCurrentPage(){
        return driver.getCurrentUrl().equals(baseUrl+"/test");
    }
}
