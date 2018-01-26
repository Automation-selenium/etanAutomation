package testAutomation.testdata;

public class TestConfig {

    public static String OUTPUT_DIRECTORY = "C:\\Automation";
    public static String SLACK_URL = "https://hooks.slack.com/services/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";
    public static String APPLICATION_URL_PRODUCTION = "http://10.0.0.8:3100/bos/dashboard";

    //public static String BROWSER = "firefox";
    public static String BROWSER = "chrome";
    //public static String BROWSER = "chrome headless";
    //public static String BROWSER = "firefox headless";
    public static boolean ENABLE_SLACK = true;
    public static long DEFAULT_WAIT = 30;
    public static String CHROMEDRIVERPATH = "\\src\\test\\Drivers\\chromedriver\\chromedriver.exe";
    public static String FIREFOXDRIVERPATH = "\\src\\test\\Drivers\\firefoxdriver\\geckodriver.exe";


}
