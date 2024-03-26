package Tests;

import Pages.P01_HomePage;
import Pages.P03_SignupPage;
import Pages.P04_AccountCreatedPage;
import Pages.P05_DeleteAccountPage;
import Utilities.LogsUtils;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.time.Duration;

import static DriverFactory.DriverFactory.*;
import static Utilities.DataUtils.getPropertyValue;

public class T04_DeleteAccountTest {
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
    public void deleteAccountTC() {
        //Pre-conditions
        new P01_HomePage(getDriver())
                .clickOnSignupButton()
                .signUpInLoginPageSteps()
                .signUpSteps();
        new P04_AccountCreatedPage(getDriver()).clickContinueButton();

        //The actual test case start here
        LogsUtils.info("Deleting the account started");
        new P01_HomePage(getDriver()).clickDeleteAccount();
        Assert.assertTrue(new P05_DeleteAccountPage(getDriver()).AccountDeletedMsgVisibility());
        new P05_DeleteAccountPage(getDriver()).clickContinueButton();


    }
    @AfterMethod
    public void quit() {
        quitDriver();
    }
}

