package testAutomation.testdata;

import org.openqa.selenium.WebDriver;

public class TestCaseNameManager {

    private static ThreadLocal<String> testCaseName = new ThreadLocal<String>();

    public static String getTestName(){
        return testCaseName.get();
    }

    public static void setTestCaseName(String name) {
        testCaseName.set(name);
    }

}