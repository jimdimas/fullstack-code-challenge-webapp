package com.app.backend.tests;

import com.app.backend.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SolveTestTest extends BaseTest {

    @Test
    public void solveTest() throws InterruptedException {
        LoginPage loginPage = homePage.showLoginPage();
        ProfilePage profilePage = loginPage.loginAction("user","123456");
        TestPage testPage = profilePage.clickViewTests();

        SolveTestPage solveTestPage = testPage.clickTestByTitle("First Test");
        solveTestPage.chooseAnswerToQuestion("What is 1+1?","2");
        solveTestPage.chooseAnswerToQuestion("What is 1-1?","0");
        solveTestPage.chooseAnswerToQuestion("What is 2*2?","2");

        testPage = solveTestPage.clickSubmitButton();

        //perform two tests and see result updating in database
        
        solveTestPage = testPage.clickTestByTitle("First Test");
        Thread.sleep(500);  //wait for dom to load elements
        solveTestPage.chooseAnswerToQuestion("What is 1+1?","2");
        solveTestPage.chooseAnswerToQuestion("What is 1-1?","0");
        solveTestPage.chooseAnswerToQuestion("What is 2*2?","4");

        testPage = solveTestPage.clickSubmitButton();
        Assert.assertTrue(testPage.testTitleExists("First Test"));
    }
}
