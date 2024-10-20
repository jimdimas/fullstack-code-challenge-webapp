package com.app.backend.data.providers;

import com.app.backend.data.classes.TestSolution;
import com.app.backend.model.Solution;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class SolveProblemDataProvider {

    @DataProvider(name = "solveProblemInputProvider")
    public Object[][] solveProblemData() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("src/test/resources/data/solveProblemData.json");
        List<Solution> solutions = objectMapper.readValue(file, new TypeReference<List<Solution>>() {});
        Object[][] result = new Object[solutions.size()][1];
        for (int i=0; i<solutions.size(); i++){
            result[i][0]=solutions.get(i);
        }

        return result;
    }
}
