package testAutomation.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import testAutomation.pageobjects.CreateAccount;
import testAutomation.pageobjects.Dashboard;
import testAutomation.testdata.TestConfig;
import testAutomation.testdata.TestData;

import java.time.format.TextStyle;

public class CreateAccountFunctionality extends BaseTest{

    @Test(description =  "FL2-AccountCreation:- To verify that user can Create Individual Customer Account",
            priority =1)
    public void testAccountCreation(){
        Dashboard dashboard = new Dashboard();
        CreateAccount createaccpage = dashboard.getCreateAccountPage(TestConfig.APPLICATION_URL_PRODUCTION);
        Assert.assertNotEquals(createaccpage, null);
        // create account
        Assert.assertEquals(createaccpage.createAccount(
                TestData.ACCOUNT_OPENING_TYPE_WALKIN,
                TestData.CUSTOMER_TYPE_INDIVIDUAL,
                TestData.CUST_FIRST_NAME,
                TestData.CUST_MIDDLE_NAME,
                TestData.CUST_LAST_NAME,
                TestData.EMAILID,
                TestData.PHONETYPE_HOME,
                TestData.PHONENUMBER

        ),false);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Assert.assertNotEquals(createaccpage, null);


    }





}
