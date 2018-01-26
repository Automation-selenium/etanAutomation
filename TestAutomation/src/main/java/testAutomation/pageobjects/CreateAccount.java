package testAutomation.pageobjects;

import org.openqa.selenium.WebDriver;
import testAutomation.DriverManager.DriverManager;
import testAutomation.UIElements.UIElement;
import testAutomation.utils.LocatorType;

public class CreateAccount extends BasePage {

    // all page locators goes in here ....
    private UIElement selectOpeningType = new UIElement("SELECT ACCOUNT OPENING TYPE DROPDOWN",
            LocatorType.Locator.XPATH, "//*[text()='Select Customer Type*']/preceding::div[@class='mat-select-trigger']");

    private UIElement selectCustomerType = new UIElement("SELECT CUSTOMER TYPE DROPDOWN",
            LocatorType.Locator.XPATH, "//*[text()='Select Customer Type*']/following::div[@class='mat-select-trigger']");

    private UIElement emailInfo = new UIElement("EMAIL ADDRESS",
            LocatorType.Locator.ID, "mat-input-0");

    private UIElement telephoneInfo = new UIElement("TELEPHONE TYPE",
            LocatorType.Locator.XPATH, "//*[text()='Telephone Info*']/following::div[@class='mat-select-trigger']");

    private UIElement telePhoneNumberTxtBox = new UIElement("TELEPHONE NUMBER",
            LocatorType.Locator.ID, "mat-input-1");

    private UIElement createAccountButton = new UIElement("CREATE ACCOUNT BUTTON",
            LocatorType.Locator.XPATH, "//*[@class='mat-raised-button mat-primary']");

    private UIElement openingTypeWalkIn = new UIElement("WALK IN",
            LocatorType.Locator.XPATH, "//*[contains(text(),'Walk In')]");

    private UIElement customerTypeIndividual = new UIElement("Individual Customer",
            LocatorType.Locator.XPATH, "//*[contains(text(),'Individual Customer')]");

    private UIElement telephoneInfoHome = new UIElement("Home Telephone Number",
            LocatorType.Locator.XPATH, "//span[contains(text(),'Home')]");

    private UIElement fName = new UIElement("Customer First Name",
            LocatorType.Locator.ID, "mat-input-2");

    private UIElement mName = new UIElement("Customer Middle Name",
            LocatorType.Locator.ID, "mat-input-3");

    private UIElement lName = new UIElement("Customer Last Name",
            LocatorType.Locator.ID, "mat-input-4");






    public boolean createAccount(String accOpeningType,
                                 String customerType,
                                 String custfirstName,
                                 String custmiddleName,
                                 String custlastName,
                                 String email,
                                 String telephoneInfoType,
                                 String phoneNumber) {
        if (!selectAccountOpening(accOpeningType))
            return false;
        if (!selectCustomerType(customerType))
            return false;
        if (!setFirstname(custfirstName))
            return false;
        if (!setMiddlename(custmiddleName))
            return false;
        if (!setLastname(custlastName))
            return false;
        if(!setEmailAddress(email))
            return false;
        if(!selectHomePhoneNumber(telephoneInfoType,phoneNumber))
            return false;
        if(!clickCreateAccountButton())
            return false;

        return true;
    }


    private boolean selectAccountOpening(String openingType) {
       // WebDriver driver = DriverManager.getDriver();
        if (openingType == "walk in") {
            if (selectOpeningType.click() == false)
                return false;
            if (openingTypeWalkIn.click() == false)
                return false;
        }

        return true;
    }

    private boolean selectCustomerType(String customerType) {
       // WebDriver driver = DriverManager.getDriver();
        if (customerType == "Individual Customer") {
            if (selectCustomerType.click() == false)
                return false;
            if (customerTypeIndividual.click() == false)
                return false;
        }

        return true;
    }


    private boolean setFirstname(String fname){
       if( fName.sendKeys(fname) ==false ){
           return false;
       }

       return true;
    }

    private boolean setMiddlename(String middlename){
        if( mName.sendKeys(middlename) ==false ){
            return false;
        }

        return true;
    }

    private boolean setLastname(String lname){
        if( lName.sendKeys(lname) ==false ){
            return false;
        }

        return true;
    }

    private boolean setEmailAddress(String emailId) {
        if(emailInfo.sendKeys(emailId)== false){
            return false;
        }

        return true;
    }

    private boolean selectHomePhoneNumber(String telephoneInfoType, String phNumber) {
       if(telephoneInfoType.equalsIgnoreCase("Home")){
           if(telephoneInfo.click()==false){
               return false;
           }

           if(telephoneInfoHome.click()== false){
               return false;
           }

           if(telePhoneNumberTxtBox.sendKeys(phNumber) == false){
               return false;
           }
       }

       return true;
    }

    private boolean clickCreateAccountButton(){
        if(createAccountButton.click()==false){
            return false;
        }

        return true;
    }


}
