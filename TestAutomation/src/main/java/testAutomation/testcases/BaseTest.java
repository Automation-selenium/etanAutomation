package testAutomation.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import testAutomation.DriverManager.DriverFactory;
import testAutomation.DriverManager.DriverManager;
import testAutomation.ScreenShotListner.ScreenshotListener;
import testAutomation.recording.ScreenRecorder;
import testAutomation.recording.ScreenRecorderFactory;
import testAutomation.slack.SlackStatusListner;
import testAutomation.testdata.TestCaseNameManager;
import testAutomation.testdata.TestConfig;

import java.lang.reflect.Method;

@Listeners({ScreenshotListener.class, SlackStatusListner.class})
public class BaseTest {

    private ScreenRecorder screenRecorder;
    protected static String url;
    protected BaseTest currentPage;

    @BeforeSuite
    protected void suiteSetup(){
        WebDriver driver = DriverFactory.createInstance();
        DriverManager.setWebDriver(driver);
        this.url = TestConfig.APPLICATION_URL_PRODUCTION;
    }

    @BeforeMethod
    public void testSetup( Method method) {
        System.out.println(method.getName());
        TestCaseNameManager.setTestCaseName(method.getName());
        ScreenRecorder m = ScreenRecorderFactory.getScreenRecorderFactory().getScreenRecorder(ScreenRecorderFactory.MOVIE_TYPE.MP4);
        screenRecorder = m.startRecording();
        System.out.println("Value of Url is "+url);

    };

    @AfterMethod
    public void testTearDown()  {
        screenRecorder.stopRecording();
    }

    @AfterSuite
    public void suiteTearDown(){
        DriverManager.closeDriver();
    }
}
