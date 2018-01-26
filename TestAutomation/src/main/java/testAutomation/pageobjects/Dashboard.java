package testAutomation.pageobjects;

import org.openqa.selenium.WebDriver;
import testAutomation.DriverManager.DriverManager;

public class Dashboard extends BasePage{


    public CreateAccount getCreateAccountPage(String url){
        WebDriver driver = DriverManager.getDriver();
        driver.navigate().to(url);
        SideNavigation sideNav = new SideNavigation();
        return sideNav.clickOnMyAccount();
    }
}
