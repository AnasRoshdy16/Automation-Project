package Tests;

import Listeners.IInvokedMethodListenerClass;
import Listeners.ITestResultListenerClass;
import Pages.*;
import Utilities.LogsUtils;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

import static DriverFactory.DriverFactory.*;
import static Utilities.DataUtils.getJsonData;
import static Utilities.DataUtils.getPropertyValue;

@Listeners({IInvokedMethodListenerClass.class, ITestResultListenerClass.class})
public class TC01_RegistrationTest {
    @BeforeMethod
    public void setup() throws IOException {
        setupDriver(getPropertyValue("environment", "Browser"));
        LogsUtils.info(" Edge Driver is opened ");
        getDriver().get(getPropertyValue("environment", "HOME_URL"));
        LogsUtils.info(" Page is redirected to the URL ");
        getDriver().manage().
                timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void RegisterUserTC() {
        Assert.assertTrue(new P01_HomePage(getDriver()).HomeButtonVisibility());
        new P01_HomePage(getDriver()).clickOnSignupButton();
        //Asserting signup text visibility
        Assert.assertTrue(new P02_LoginPage(getDriver()).NewSignupVisibility());
        LogsUtils.info("Filling out Credentials");
        //Initial SignUp
        new P02_LoginPage(getDriver())
                .Entername(getJsonData("SignupInformation", "Name"))
                .Enteremail(getJsonData("SignupInformation", "TrialEmail"))
                .clickOnSignupButton();
        //Assert in Full Registration Page
        Assert.assertTrue(new P03_SignupPage(getDriver()).AccountInformationTextVisibility());
        LogsUtils.info("Assertion on Account Info Text");
        new P03_SignupPage(getDriver()).signUpSteps();
        //Account is Created and redirected to the Account Created Page
        Assert.assertTrue(new P04_AccountCreatedPage(getDriver()).AccountCreatedTextVisibility());
        new P04_AccountCreatedPage(getDriver()).clickContinueButton();
        //Assertion of visibility of user is logged in
        Assert.assertTrue(new P01_HomePage(getDriver()).loggedInVisibility());
        //Position Is in Home Page with user signed logo Available
        LogsUtils.info("Deleting the account started");
        new P01_HomePage(getDriver()).clickDeleteAccount();
        Assert.assertTrue(new P05_DeleteAccountPage(getDriver()).AccountDeletedMsgVisibility());
        new P05_DeleteAccountPage(getDriver()).clickContinueButton();
        LogsUtils.info("The Account is Deleted Successfully");
        //The Account Is Deleted
    }


    @AfterMethod
    public void quit() {
        quitDriver();
    }
}

