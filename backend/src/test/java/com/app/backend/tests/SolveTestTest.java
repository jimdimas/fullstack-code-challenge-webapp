package com.app.backend.tests;

import com.app.backend.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SolveTestTest extends BaseTest {

    @Test
    public void solveTest() throws InterruptedException {
        LoginPage loginPage = homePage.showLoginPage();
        ProfilePage profilePage = loginPage.loginAction("user","123456");
        Thread.sleep(500);
        Assert.assertTrue(profilePage.isAuthorized());
        TestPage testPage = profilePage.clickViewTests();
        SolveTestPage solveTestPage = testPage.clickTestByTitle("First Test");
        solveTestPage.chooseAnswerToQuestion("What is 1+1?","1");
        solveTestPage.chooseAnswerToQuestion("What is 1-1?","1");
        solveTestPage.chooseAnswerToQuestion("What is 2*2?","1");
        solveTestPage.clickSubmitButton();
        Assert.assertTrue(testPage.isCurrentPage());
    }
}
