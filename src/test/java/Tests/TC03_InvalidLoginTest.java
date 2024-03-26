package Tests;

import Listeners.IInvokedMethodListenerClass;
import Listeners.ITestResultListenerClass;
import Pages.P01_HomePage;
import Pages.P02_LoginPage;
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
public class TC03_InvalidLoginTest {
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
    public void InValidLoginTC() {

        Assert.assertTrue(new P01_HomePage(getDriver()).HomeButtonVisibility());
        new P01_HomePage(getDriver()).clickOnSignupButton();
        //Asserting signup text visibility
        Assert.assertTrue(new P02_LoginPage(getDriver()).LoginToYourAccountVisibility());
        LogsUtils.info("Filling out Credentials");
        //PreCondition-entering Invalid Email & Password (Try Using the account deleted in the valid TC)
        new P02_LoginPage(getDriver())
                .EnterLoginEmail(getJsonData("SignupInformation", "InvalidEmail"))
                .EnterPassword(getJsonData("SignupInformation", "InValidPassword"))
                .clickOnLoginButton();
        //Asserting that the user is Incorrect
        Assert.assertTrue(new P02_LoginPage(getDriver()).IncorrectUsernameOrPasswordVisibility());

    }


    @AfterMethod
    public void quit() {
        quitDriver();
    }
}

