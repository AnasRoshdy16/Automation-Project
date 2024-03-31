package Tests;

import Listeners.IInvokedMethodListenerClass;
import Listeners.ITestResultListenerClass;
import Pages.P01_HomePage;
import Pages.P02_LoginPage;
import Pages.P09_CheckoutPage;
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
public class T06_CheckoutRelatedTests {
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
    public void LoginBeforeCheckoutTC() {

        new P01_HomePage(getDriver()).clickOnSignupButton();
        new P02_LoginPage(getDriver())
                .EnterLoginEmail(getJsonData("SignupInformation", "ValidEmail"))
                .EnterPassword(getJsonData("SignupInformation", "ValidPassword"))
                .clickOnLoginButton();
        new P01_HomePage(getDriver()).clickOnProductsButton()
                // Adding First two products in the cart
                .addingFirstProductToCart()
                .addingSecondProductToCart()
                // Clicking on cart button to view cart page
                .clickViewCartButton()
                //Clicking the proceed to Checkout Button
                .clickProceedToCheckout();
        //Assertion of Address and Order Details
        Assert.assertTrue(new P09_CheckoutPage(getDriver()).addressDetailsVisibility());
        Assert.assertTrue(new P09_CheckoutPage(getDriver()).orderDetailsVisibility());
        //Submitting a comment to be added in the invoice
        new P09_CheckoutPage(getDriver()).addingcomment("The order has been placed successfully")
                //Clicking place order to go to the payment page
                .placingOrder()
                //Filling card information
                .FillingPaymentDetails()
                .ClickPayAndConfirm()
                //Assertion visibility of order confirmation message
                .orderConfirmedVisibility();

    }

    @Test
    public void VerifyAddressDetailsInCheckoutTC() {

        new P01_HomePage(getDriver()).clickOnSignupButton();
        new P02_LoginPage(getDriver())
                .EnterLoginEmail(getJsonData("SignupInformation", "ValidEmail"))
                .EnterPassword(getJsonData("SignupInformation", "ValidPassword"))
                .clickOnLoginButton();
        new P01_HomePage(getDriver()).clickOnProductsButton()
                // Adding First two products in the cart
                .addingFirstProductToCart()
                .addingSecondProductToCart()
                // Clicking on cart button to view cart page
                .clickViewCartButton()
                //Clicking the proceed to Checkout Button
                .clickProceedToCheckout();
        //Comparing Addresses
        Assert.assertTrue(new P09_CheckoutPage(getDriver()).ComparingDeliveryAddresses());
        Assert.assertTrue(new P09_CheckoutPage(getDriver()).ComparingBillingAddresses());
    }


    @AfterMethod
    public void quit() {
        quitDriver();
    }
}
