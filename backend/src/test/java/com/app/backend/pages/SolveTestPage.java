package com.app.backend.pages;

import com.app.backend.model.Test;
import org.openqa.selenium.By;

import static com.app.backend.utility.JavascriptUtilities.clickJS;
import static com.app.backend.utility.JavascriptUtilities.scrollToElementJS;

public class SolveTestPage extends AuthorizedPage{

    private By submitButton = By.xpath("//button[@type='submit']");
    public void chooseAnswerToQuestion(String question,String answer){
        By answerRadioBox = By.xpath("//input[@name='" + question + "' and @value='" + answer +"']");
        scrollToElementJS(answerRadioBox);
        clickJS(answerRadioBox);
    }

    public TestPage clickSubmitButton(){
        click(submitButton);
        return new TestPage();
    }
}
