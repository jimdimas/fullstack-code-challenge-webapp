package com.app.backend.tests;

import com.app.backend.pages.LoginPage;
import com.app.backend.pages.ProfilePageSupervisor;
import com.app.backend.pages.TestPage;
import com.app.backend.pages.UploadTestPage;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class UploadTestTest extends BaseTest{

    @Test
    @Parameters({"Username","Password"})
    public void uploadTest(String username,String password){
        LoginPage loginPage = homePage.showLoginPage();
        ProfilePageSupervisor profile = loginPage.loginActionSupervisor(username,password);
        UploadTestPage uploadTestPage = profile.clickUploadTest();

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
        profile = uploadTestPage.submitTest();

        TestPage testPage = profile.clickViewTests();
        Assert.assertTrue(testPage.testTitleExists("Sample Test"));
    }
}
