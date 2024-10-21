package com.app.backend.tests;

import com.app.backend.data.providers.SolveProblemDataProvider;
import com.app.backend.data.providers.UploadProblemDataProvider;
import com.app.backend.model.Problem;
import com.app.backend.model.Solution;
import com.app.backend.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProblemTest extends BaseTest{

    @Test(dataProviderClass = UploadProblemDataProvider.class,dataProvider = "uploadProblemInputProvider")
    public void uploadProblem(Problem problem) {
        UploadProblemPage uploadProblemPage = profilePageSupervisor.clickUploadProblem();

        uploadProblemPage.setQuestion(problem.getQuestion());
        uploadProblemPage.setDifficulty(problem.getDifficulty());
        uploadProblemPage.setPoints(problem.getPoints().toString());
        profilePageSupervisor = uploadProblemPage.clickSubmit();

        ProblemPage problemPage = profilePageSupervisor.clickViewProblems();
        Assert.assertTrue(problemPage.checkProblemExists(problem.getQuestion()));
    }

    @Test(dataProviderClass = SolveProblemDataProvider.class,dataProvider = "solveProblemInputProvider")
    public void solveProblem(Solution solution){
        ProblemPage problemPage = profilePage.clickViewProblems();
        SolveProblemPage solveProblemPage = problemPage.clickProblemByQuestion(solution.getForProblem().getQuestion());

        solveProblemPage.setAnswer(solution.getContent());
        profilePage = solveProblemPage.clickSubmitButton();
        Assert.assertTrue(profilePage.isAuthorized());
    }
}
