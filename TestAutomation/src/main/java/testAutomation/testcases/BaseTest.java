package testAutomation.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import testAutomation.DriverManager.DriverFactory;
import testAutomation.DriverManager.DriverManager;
import testAutomation.ScreenShotListner.ScreenshotListener;
import testAutomation.pageobjects.Dashboard;
import testAutomation.pageobjects.CreateAccount;
import testAutomation.recording.MonteScreenRecorder;
import testAutomation.recording.RecorderNotStartedExcetpion;
import testAutomation.recording.RecorderNotStoppedException;
import testAutomation.slack.SlackStatusListner;
import testAutomation.testdata.TestCaseNameManager;
import testAutomation.testdata.TestConfig;

import java.lang.reflect.Method;

@Listeners({ScreenshotListener.class, SlackStatusListner.class})
public class BaseTest {

    private MonteScreenRecorder screenRecorder;
    protected static String url;
    protected BaseTest currentPage;

    @BeforeSuite
    protected void suiteSetup(){
        WebDriver driver = DriverFactory.createInstance();
        DriverManager.setWebDriver(driver);
        this.url = TestConfig.APPLICATION_URL_PRODUCTION;
    }

    @BeforeMethod
    public void testSetup( Method method) throws RecorderNotStartedExcetpion {
        System.out.println(method.getName());
        TestCaseNameManager.setTestCaseName(method.getName());
        MonteScreenRecorder m = new MonteScreenRecorder();
        screenRecorder = m.start();
        System.out.println("Value of Url is "+url);


        //OverviewPage overviewPage = createaccpage.sigIn(TestData.USER_NAME, TestData.PASSWORD);
       // Assert.assertNotEquals(overviewPage, null);

    };

    @AfterMethod
    public void testTearDown() throws RecorderNotStoppedException {
        screenRecorder.stop();
    }

    @AfterSuite
    public void suiteTearDown(){
        DriverManager.closeDriver();
    }
}
