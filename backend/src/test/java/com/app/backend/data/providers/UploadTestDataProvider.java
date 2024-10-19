package com.app.backend.data.providers;

import com.app.backend.model.Test;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class UploadTestDataProvider {
    @DataProvider(name = "uploadTestInputProvider")
    public Object[][] uploadTestData() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("src/test/resources/data/uploadTestData.json");
        List<Test> tests = objectMapper.readValue(file, new TypeReference<List<Test>>() {});
        Object[][] result = new Object[tests.size()][1];
        for (int i=0; i<tests.size(); i++){
            result[i][0]=tests.get(i);
        }

        return result;
    }
}
