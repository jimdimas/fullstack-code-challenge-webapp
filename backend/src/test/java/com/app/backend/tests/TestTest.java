package com.app.backend.tests;

import com.app.backend.data.classes.TestSolution;
import com.app.backend.data.providers.TestSolutionDataProvider;
import com.app.backend.data.providers.UploadTestDataProvider;
import com.app.backend.model.Question;
import com.app.backend.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestTest extends AuthorizedBaseTest {

    @Test(dataProviderClass = TestSolutionDataProvider.class,dataProvider = "solveTestInputProvider")
    public void solveTest(TestSolution testSolution){
        TestPage testPage = profilePage.clickViewTests();

        SolveTestPage solveTestPage = testPage.clickTestByTitle(testSolution.getTestTitle());
        for (int i=0; i<testSolution.getAnswers().length; i++){
            solveTestPage.chooseAnswerToQuestion(testSolution.getQuestions()[i],
                    testSolution.getAnswers()[i]);
        }

        testPage = solveTestPage.clickSubmitButton();
        TestResultPage testResultPage = testPage.clickViewResults();
        Assert.assertEquals(
                testResultPage.checkTestResult(testSolution.getTestTitle()),testSolution.getExpectedResult(),
                "Test : "+ testSolution.getType() + "did not pass");
        System.out.println("Test: "+ testSolution.getType()+" passed");
    }

    @Test(dataProviderClass = UploadTestDataProvider.class,dataProvider = "uploadTestInputProvider")
    public void uploadTest(com.app.backend.model.Test test){
        UploadTestPage uploadTestPage = profilePageSupervisor.clickUploadTest();

        uploadTestPage.setTestTitle(test.getTitle());

        for (int i=0; i<test.getQuestions().size(); i++){
            Question tempQuestion = test.getQuestions().get(i);
            uploadTestPage.addQuestion(
                    tempQuestion.getContent(),
                    tempQuestion.getAnswers(),
                    tempQuestion.getCorrectAnswer());
        }

        uploadTestPage.setTestPoints(100);
        profilePageSupervisor = uploadTestPage.submitTest();

        TestPage testPage = profilePageSupervisor.clickViewTests();
        Assert.assertTrue(testPage.testTitleExists(test.getTitle()));
    }
}
