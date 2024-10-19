package com.app.backend.data.providers;

import com.app.backend.model.Problem;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class UploadProblemDataProvider {
    @DataProvider(name="uploadProblemInputProvider")
    public Object[][] uploadProblemData() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("src/test/resources/data/uploadProblemData.json");
        List<Problem> problems = objectMapper.readValue(file, new TypeReference<List<Problem>>() {});
        Object[][] result = new Object[problems.size()][1];
        for (int i=0; i<problems.size(); i++){
            result[i][0]=problems.get(i);
        }

        return result;
    }
}
