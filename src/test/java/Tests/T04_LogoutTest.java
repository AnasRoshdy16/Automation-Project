package Tests;


import Listeners.IInvokedMethodListenerClass;
import Listeners.ITestResultListenerClass;
import Pages.P01_HomePage;
import Pages.P02_LoginPage;
import Pages.P05_DeleteAccountPage;
import Utilities.LogsUtils;
import Utilities.Utility;
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
public class T04_LogoutTest {

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
    public void LogoutTC(){
        new P01_HomePage(getDriver()).clickOnSignupButton();
        //Asserting signup text visibility
        Assert.assertTrue(new P02_LoginPage(getDriver()).LoginToYourAccountVisibility());
        LogsUtils.info("Filling out Credentials");
        //PreCondition-entering Valid Pre-Registered Email & Password
        new P02_LoginPage(getDriver())
                .EnterLoginEmail(getJsonData("SignupInformation", "ValidEmail"))
                .EnterPassword(getJsonData("SignupInformation", "ValidPassword"))
                .clickOnLoginButton();
        //Asserting that the user is successfully Logged
        Assert.assertTrue(new P01_HomePage(getDriver()).loggedInVisibility());
        LogsUtils.info("user is logged in");
        //Click logout
        new P01_HomePage(getDriver()).clickOnLogoutButton();
        // verify url
        Utility.verifyURL(getDriver(),"https://automationexercise.com/login");


    }
    @AfterMethod
    public void quit(){
        quitDriver();
    }
}
