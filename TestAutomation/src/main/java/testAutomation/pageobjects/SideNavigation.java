package testAutomation.pageobjects;

import org.openqa.selenium.WebDriver;
import testAutomation.DriverManager.DriverManager;
import testAutomation.UIElements.UIElement;
import testAutomation.UIElements.UIElements;
import testAutomation.utils.LocatorType;

import java.util.List;

public class SideNavigation extends BasePage {

    // All Page Locators goes in here
    private UIElement myAccountLink = new UIElement("My Account Link",
            LocatorType.Locator.XPATH, "//a[text()='Accounts']");




    public CreateAccount clickOnMyAccount() {
        WebDriver driver = DriverManager.getDriver();
        if (myAccountLink.click()) {
            CreateAccount page = new CreateAccount();
            return page;
        }
        return null;
    }


}
