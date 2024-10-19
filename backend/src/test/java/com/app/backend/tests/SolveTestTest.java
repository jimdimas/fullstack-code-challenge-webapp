package com.app.backend.tests;

import com.app.backend.data.classes.TestSolution;
import com.app.backend.data.providers.TestSolutionDataProvider;
import com.app.backend.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SolveTestTest extends BaseTest {

    @Test(dataProviderClass = TestSolutionDataProvider.class,dataProvider = "solveTestInputProvider")
    public void solveTest(TestSolution testSolution){
        TestPage testPage = profilePage.clickViewTests();

        SolveTestPage solveTestPage = testPage.clickTestByTitle(testSolution.getTestTitle());
        for (int i=0; i<testSolution.getAnswers().length; i++){
            solveTestPage.chooseAnswerToQuestion(testSolution.getAnswers()[i][0],
                    testSolution.getAnswers()[i][1]);
        }

        testPage = solveTestPage.clickSubmitButton();
        TestResultPage testResultPage = testPage.clickViewResults();
        Assert.assertEquals(
                testResultPage.checkTestResult(testSolution.getTestTitle()),testSolution.getExpectedResult(),
                "Test : "+ testSolution.getType() + "did not pass");
        System.out.println("Test: "+ testSolution.getType()+" passed");
    }
}
