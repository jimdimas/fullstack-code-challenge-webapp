package com.app.backend.data.classes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestSolution {
    private String type;
    private String testTitle;
    private String[] questions;
    private String[] answers;
    private String expectedResult;
}
