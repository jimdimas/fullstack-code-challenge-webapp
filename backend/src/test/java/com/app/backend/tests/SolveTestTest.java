package com.app.backend.tests;

import com.app.backend.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SolveTestTest extends BaseTest {
    private TestResultPage testResultPage;

    @Test(priority = 1)
    public void solveTestNotPass(){
        LoginPage loginPage = homePage.showLoginPage();
        ProfilePage profilePage = loginPage.loginAction("user","123456");
        TestPage testPage = profilePage.clickViewTests();

        SolveTestPage solveTestPage = testPage.clickTestByTitle("First Test");
        solveTestPage.chooseAnswerToQuestion("What is 1+1?","0");
        solveTestPage.chooseAnswerToQuestion("What is 1-1?","1");
        solveTestPage.chooseAnswerToQuestion("What is 2*2?","2");

        testPage = solveTestPage.clickSubmitButton();
        testResultPage = testPage.clickViewResults();
        Assert.assertEquals(testResultPage.checkTestResult("First Test"),"");
    }

    @Test(priority = 2)
    public void solveTestPass() {
        TestPage testPage = testResultPage.clickViewTests();

        SolveTestPage solveTestPage = testPage.clickTestByTitle("First Test");
        solveTestPage.chooseAnswerToQuestion("What is 1+1?","2");
        solveTestPage.chooseAnswerToQuestion("What is 1-1?","0");
        solveTestPage.chooseAnswerToQuestion("What is 2*2?","2");

        testPage = solveTestPage.clickSubmitButton();
        TestResultPage testResultPage = testPage.clickViewResults();
        Assert.assertEquals(testResultPage.checkTestResult("First Test"),"66%");
    }
    @Test(priority = 3)
    public void solveTestImprove(){
        TestPage testPage = testResultPage.clickViewTests();

        SolveTestPage solveTestPage = testPage.clickTestByTitle("First Test");
        solveTestPage.chooseAnswerToQuestion("What is 1+1?","2");
        solveTestPage.chooseAnswerToQuestion("What is 1-1?","0");
        solveTestPage.chooseAnswerToQuestion("What is 2*2?","4");

        testPage = solveTestPage.clickSubmitButton();
        TestResultPage testResultPage = testPage.clickViewResults();
        Assert.assertEquals(testResultPage.checkTestResult("First Test"),"100%");
    }
}
