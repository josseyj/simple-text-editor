package org.josseyjacob.experiments.texteditor;


import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
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
                {
                        TestData.builder()
                                .testCase("BACKSPACE should remove the last character of the current text")
                                .input(new String[][]{
                                        {"0", "APPEND", "Hey you"},
                                        {"1", "BACKSPACE"},
                                        {"2", "BACKSPACE"}
                                })
                                .expectedResult("Hey y")
                                .build()
                },
                {
                        TestData.builder()
                                .testCase("BACKSPACE should do nothing if there are no characters to delete")
                                .input(new String[][]{
                                        {"0", "APPEND", "!"},
                                        {"1", "BACKSPACE"},
                                        {"2", "BACKSPACE"}
                                })
                                .expectedResult("")
                                .build()
                },
                {
                        TestData.builder()
                                .testCase("UNDO should undo the previous APPEND or BACKSPACE operation")
                                .input(new String[][]{
                                        {"0", "APPEND", "Hey"},
                                        {"1", "APPEND", " there"},
                                        {"2", "APPEND", "!"},
                                        {"3", "UNDO"},
                                        {"4", "UNDO"},
                                })
                                .expectedResult("Hey")
                                .build()
                },
                {
                        TestData.builder()
                                .testCase("UNDO should do nothing if there are more UNDOs than APPEND and BACKSPACE operations")
                                .input(new String[][]{
                                        {"0", "APPEND", "Hey"},
                                        {"1", "UNDO"},
                                        {"2", "UNDO"}
                                })
                                .expectedResult("")
                                .build()
                },
                {
                        TestData.builder()
                                .testCase("REDO should redo the previous UNDO operation")
                                .input(new String[][]{
                                        {"0", "APPEND", "Hey"},
                                        {"1", "APPEND", " there"},
                                        {"2", "UNDO"},
                                        {"3", "REDO"}
                                })
                                .expectedResult("Hey there")
                                .build()
                },
                {
                        TestData.builder()
                                .testCase("REDO should do nothing when there are more REDOs than UNDOs")
                                .input(new String[][]{
                                        {"0", "APPEND", "Hey"},
                                        {"1", "UNDO"},
                                        {"2", "REDO"},
                                        {"3", "REDO"},
                                })
                                .expectedResult("Hey")
                                .build()
                },
                {
                        TestData.builder()
                                .testCase("REDO should only work immediately after an UNDO or REDO operation")
                                .input(new String[][]{
                                        {"0", "APPEND", "Hey"},
                                        {"1", "UNDO"},
                                        {"2", "APPEND", " there"},
                                        {"3", "REDO"},
                                })
                                .expectedResult(" there")
                                .build()
                },
                {
                        TestData.builder()
                                .testCase("input should be applied in chronological order according to the UNIX timestamp provided in the first array element")
                                .input(new String[][]{
                                        {"1548185072722", "APPEND", "ey"},
                                        {"1548185072721", "APPEND", "H"},
                                })
                                .expectedResult("Hey")
                                .build()
                },
                {
                        TestData.builder()
                                .testCase("6a. SELECT and BACKSPACE should remove the selected characters")
                                .input(new String[][]{
                                        {"1548185072721", "APPEND", "Hello"},
                                        {"1548185072722", "SELECT", "1", "3"},
                                        {"1548185072723", "BACKSPACE"},
                                })
                                .expectedResult("Hlo")
                                .build()
                },
                {
                        TestData.builder()
                                .testCase("6b. SELECT and APPEND should replace the selected characters with the appended characters")
                                .input(new String[][]{
                                        {"1548185072721", "APPEND", "Hello"},
                                        {"1548185072722", "SELECT", "2", "5"},
                                        {"1548185072723", "APPEND", "y there"},
                                })
                                .expectedResult("Hey there")
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
    @ToString(of = "testCase")
    private static class TestData {

        @NonNull
        private final String testCase;

        private final String[][] input;

        private final String expectedResult;

    }
}
