package Tests;

import Listeners.IInvokedMethodListenerClass;
import Listeners.ITestResultListenerClass;
import Pages.*;
import Utilities.LogsUtils;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.time.Duration;

import static DriverFactory.DriverFactory.*;
import static Utilities.DataUtils.getJsonData;
import static Utilities.DataUtils.getPropertyValue;

@Listeners({IInvokedMethodListenerClass.class, ITestResultListenerClass.class})
public class T03_SignupTest {
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

        //Pre-conditions
        new P01_HomePage(getDriver()).clickOnSignupButton()
                .signUpInLoginPageSteps();

        //The actual test case starts here
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
                .EnterCompanyName("Jobless")
                .EnterAddress1Name("Cairo")
                .EnterAddress2Name("NotCairo")
                .SelectingCountryDropdown("Egypt")
                .EnterStateName("IDK")
                .EnterCityName("Giza")
                .EnterZipCode("12345")
                .EnterMobileNumber("01001212111")
                .clickCreateAccountButton();

        //Assertion of visibility on Account created
        Assert.assertTrue(new P04_AccountCreatedPage(getDriver()).AccountCreatedTextVisibility());

        new P04_AccountCreatedPage(getDriver()).clickContinueButton();
        //Assertion of visibility of user is logged in
        Assert.assertTrue(new P01_HomePage(getDriver()).loggedInVisibility());

    }

    @AfterMethod
    public void quit() {
        quitDriver();
    }
}

