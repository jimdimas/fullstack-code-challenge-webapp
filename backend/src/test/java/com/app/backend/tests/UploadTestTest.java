package com.app.backend.tests;

import com.app.backend.data.providers.UploadTestDataProvider;
import com.app.backend.model.Question;
import com.app.backend.pages.TestPage;
import com.app.backend.pages.UploadTestPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UploadTestTest extends BaseTest{

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
        Assert.assertTrue(testPage.testTitleExists("Sample Test"));
    }
}
