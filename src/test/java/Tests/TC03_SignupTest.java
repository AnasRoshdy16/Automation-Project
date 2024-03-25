package Tests;

import Listeners.IInvokedMethodListenerClass;
import Listeners.ITestResultListenerClass;
import Pages.P01_HomePage;
import Pages.P02_LoginPage;
import Pages.P03_SignupPage;
import Pages.P04_AccountCreatedPage;
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
public class TC03_SignupTest {
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
    public void SignupTC() throws IOException {

        new P01_HomePage(getDriver()).clickOnSignupButton();
        //Asserting signup text visibility
        Assert.assertTrue(new P02_LoginPage(getDriver()).NewSignupVisibility());
        LogsUtils.info("Filling out Credentials");
        new P02_LoginPage(getDriver())
                .Entername(getJsonData("SignupInformation", "Name"))
                .Enteremail(getJsonData("SignupInformation", "Email"))
                .clickOnSignupButton();
        LogsUtils.info("Partial Signup");
        Assert.assertTrue(new P03_SignupPage(getDriver()).AccountInformationTextVisibility());
        LogsUtils.info("Assertion on Account Info Text");
        new P03_SignupPage(getDriver())
                .clickTitleButton()
                .EnterPassword("Test1234")
                .SelectingDayDropdown("2")
                .SelectingMonthDropDown("February")
                .SelectingYearDropdown("1994")
                .ClickNewsLetterCheckbox()
                .ClickSpecialOffersCheckbox()
                .EnterFirstName("Omar")
                .EnterLastName("Mohamed")
                .EnterCompanyName("Selenium")
                .EnterAddress1Name("Nasr City,Abbas")
                .EnterAddress2Name("Makram ebed")
                .SelectingCountryDropdown("Canada")
                .EnterStateName("Ontario")
                .EnterCityName("Toronto")
                .EnterZipCode("12311")
                .EnterMobileNumber("01132055998")
                .clickCreateAccountButton();
        //Assertion of visibility on Account created
        LogsUtils.info("Assertion on AccountCreation");
        Assert.assertTrue(new P04_AccountCreatedPage(getDriver()).AccountCreatedTextVisibility());
    }


    @AfterMethod
    public void quit() {
        quitDriver();
    }
}

