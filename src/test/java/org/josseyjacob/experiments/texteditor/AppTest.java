package org.josseyjacob.experiments.texteditor;


import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @DataProvider
    protected Object[][] testData() {
        return new Object[][]{
                {
                        TestData.builder()
                                .testCase("APPEND should append the provided string to the current text")
                                .input(new String[][]{
                                        {"0", "APPEND", "Hey"},
                                        {"1", "APPEND", " there"},
                                        {"2", "APPEND", "!"}
                                })
                                .expectedResult("Hey there!")
                                .build()
                },
                {
                        TestData.builder()
                                .testCase("BACKSPACE should remove the last character of the current text")
                                .input(new String[][]{
                                        {"0", "APPEND", "Hey you"},
                                        {"1", "BACKSPACE"}
                                })
                                .expectedResult("Hey yo")
                                .build()
                },
        };
    }

    /**
     */
    @Test(dataProvider = "testData")
    public void testProcess(TestData testData) {
        //given
        System.out.println(testData.getTestCase());
        App app = new App();

        //when
        String result = app.process(testData.getInput());

        //then
        Assert.assertEquals(result, testData.getExpectedResult());
    }

    @Builder
    @Getter
    private static class TestData {

        @NonNull
        private final String testCase;

        private final String[][] input;

        private final String expectedResult;

    }
}
