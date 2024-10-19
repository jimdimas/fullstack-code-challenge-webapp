package com.app.backend.tests;

import com.app.backend.pages.TestPage;
import com.app.backend.pages.UploadTestPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UploadTestTest extends BaseTest{

    @Test
    public void uploadTest(){
        UploadTestPage uploadTestPage = profilePageSupervisor.clickUploadTest();

        uploadTestPage.setTestTitle("Sample Test");
        uploadTestPage.setQuestionContent("What is 10+10?");
        String answers[] = {"10","1","20"};
        uploadTestPage.setAnswers(answers);
        uploadTestPage.setCorrectAnswer(2);
        uploadTestPage.addQuestion();

        uploadTestPage.setQuestionContent("What is 10*10?");
        answers = new String[]{"10", "100", "20"};
        uploadTestPage.setAnswers(answers);
        uploadTestPage.setCorrectAnswer(1);
        uploadTestPage.addQuestion();

        uploadTestPage.setQuestionContent("What is 10^(-1)?");
        answers = new String[]{"10", "1", "0.1"};
        uploadTestPage.setAnswers(answers);
        uploadTestPage.setCorrectAnswer(2);
        uploadTestPage.addQuestion();

        uploadTestPage.setTestPoints(100);
        profilePageSupervisor = uploadTestPage.submitTest();

        TestPage testPage = profilePageSupervisor.clickViewTests();
        Assert.assertTrue(testPage.testTitleExists("Sample Test"));
    }
}
