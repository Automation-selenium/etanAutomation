package testAutomation.DriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import testAutomation.testdata.TestConfig;

public class DriverFactory {

    public static WebDriver createInstance() {
        String homeDirectory = System.getProperty("user.dir");
        WebDriver driver = null;
        if(TestConfig.BROWSER.equalsIgnoreCase("chrome")  ){
            System.out.println("Current User Directory "+homeDirectory);
            System.setProperty("webdriver.chrome.driver", homeDirectory+TestConfig.CHROMEDRIVERPATH);
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            driver = new ChromeDriver(options);
        }

        else if(TestConfig.BROWSER.equalsIgnoreCase("chrome headless")){
            System.out.println("Current User Directory "+homeDirectory);
            System.setProperty("webdriver.chrome.driver", homeDirectory+TestConfig.CHROMEDRIVERPATH);
            ChromeOptions options = new ChromeOptions();
            // options.addArguments("--start-maximized");
            options.addArguments("--headless");
            driver = new ChromeDriver(options);
        }


        else if(TestConfig.BROWSER.equalsIgnoreCase("firefox headless")){
            FirefoxBinary firefoxBinary = new FirefoxBinary();
            firefoxBinary.addCommandLineOptions("--headless");
            System.setProperty("webdriver.gecko.driver", homeDirectory+TestConfig.FIREFOXDRIVERPATH);
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.setBinary(firefoxBinary);
             driver = new FirefoxDriver(firefoxOptions);
        }
        else if(TestConfig.BROWSER.equalsIgnoreCase("firefox")){
            System.setProperty("webdriver.gecko.driver", homeDirectory+TestConfig.FIREFOXDRIVERPATH);
            driver = new FirefoxDriver();
        }
        return driver;
    }
}
