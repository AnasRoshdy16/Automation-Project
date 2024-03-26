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
public class T02_LoginTest {
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
    public void SignupTC() {

        new P01_HomePage(getDriver()).clickOnSignupButton();
        //Asserting signup text visibility
        Assert.assertTrue(new P02_LoginPage(getDriver()).NewSignupVisibility());
        LogsUtils.info("Filling out Credentials");
        new P02_LoginPage(getDriver())
                .Entername(getJsonData("SignupInformation", "Name"))
                .Enteremail(getJsonData("SignupInformation", "Email"))
                .clickOnSignupButton();
    }


    @AfterMethod
    public void quit() {
        quitDriver();
    }
}

