package com.app.backend.data.providers;

import com.app.backend.data.classes.TestSolution;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class TestSolutionDataProvider {

    @DataProvider(name = "solveTestInputProvider")
    public Object[][] solveTestData() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("src/test/resources/data/solveTestData.json");
        List<TestSolution> testSolutions = objectMapper.readValue(file, new TypeReference<List<TestSolution>>() {});
        Object[][] result = new Object[testSolutions.size()][1];
        for (int i=0; i<testSolutions.size(); i++){
            result[i][0]=testSolutions.get(i);
        }

        return result;
    }
}
