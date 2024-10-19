package com.app.backend.data.providers;

import com.app.backend.data.classes.TestSolution;
import org.testng.annotations.DataProvider;

public class TestSolutionDataProvider {

    @DataProvider(name = "solveTestInputProvider")
    public Object[][] solveTestData(){
        String[][] failedAnswers = { {
            "What is 1+1?","0"
        },{
            "What is 1-1?","1"
        },{
            "What is 2*2?","2"
        }
        };
        String[][] successAnswers = { {
                "What is 1+1?","2"
        },{
                "What is 1-1?","0"
        },{
                "What is 2*2?","2"
        }
        };
        String[][] improvedAnswers = { {
                "What is 1+1?","2"
        },{
                "What is 1-1?","0"
        },{
                "What is 2*2?","4"
        }
        };

        return new Object[][]{ {
                TestSolution.builder()
                        .type("Failed Test")
                        .testTitle("First Test")
                        .answers(failedAnswers)
                        .expectedResult("")
                        .build()
        }, {
                TestSolution.builder()
                        .type("Success Test")
                        .testTitle("First Test")
                        .answers(successAnswers)
                        .expectedResult("66%")
                        .build()
        },{
                TestSolution.builder()
                        .type("Improved Test")
                        .testTitle("First Test")
                        .answers(improvedAnswers)
                        .expectedResult("100%")
                        .build()
        }};
    }
}
