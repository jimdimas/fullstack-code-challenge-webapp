package com.app.backend.pages;

import org.openqa.selenium.By;

import static com.app.backend.utility.JavascriptUtilities.clickJS;

public class UploadTestPage extends SupervisorPage{

    private By testTitle = By.id("title");
    private By questionContent = By.id("content");
    private By answer0 = By.id("textbox-0");
    private By answer1 = By.id("textbox-1");
    private By answer2 = By.id("textbox-2");
    private By correctAnswer0 = By.id("correctAnswer0");
    private By correctAnswer1 = By.id("correctAnswer1");
    private By correctAnswer2 = By.id("correctAnswer2");
    private By addQuestionButton = By.id("addQuestion");
    private By testPoints = By.id("points");
    private By submitTestButton = By.id("submit");

    public void setTestTitle(String title){
        set(testTitle,title);
    }

    public void setQuestionContent(String content){
        set(questionContent,content);
    }
    public void setAnswers(String[] answers){
        set(answer0,answers[0]);
        set(answer1,answers[1]);
        set(answer2,answers[2]);
    }

    public void setCorrectAnswer(int index){
        if (index==0){
            clickJS(correctAnswer0);
        } else if (index==1){
            clickJS(correctAnswer1);
        } else if (index==2){
            clickJS(correctAnswer2);
        }
    }

    public void addQuestion(){
        click(addQuestionButton);
    }

    public void setTestPoints(Integer points){
        set(testPoints,points.toString());
    }

    public ProfilePageSupervisor submitTest(){
        click(submitTestButton);
        return new ProfilePageSupervisor();
    }
}
