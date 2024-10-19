package com.app.backend.data.classes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class TestSolution {
    private String type;
    private String testTitle;
    private String[][] answers;
    private String expectedResult;
}
