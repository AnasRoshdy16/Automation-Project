package Tests;

import Listeners.IInvokedMethodListenerClass;
import Listeners.ITestResultListenerClass;
import Pages.*;
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
        new P09_CheckoutPage(getDriver()).addingComment("The order has been placed successfully")
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
    @Test
    public void DownloadInvoiceAfterPurchaseOrderTC(){
        //Login
        new P01_HomePage(getDriver())
                .clickOnSignupButton()
                .loginSteps(getJsonData("SignupInformation", "ValidEmail"),
                        getJsonData("SignupInformation", "ValidPassword"));
        new P01_HomePage(getDriver()).clickOnProductsButton()
                // Adding First product in the cart
                .addingFirstProductToCart()
                // Clicking on cart button to view cart page
                .clickViewCartButton()
                //Clicking the proceed to Checkout Button
                .clickProceedToCheckout();
        // Adding a comment and payment details
        new P09_CheckoutPage(getDriver()).addingComment("I need a blue T-shirt")
                .placingOrder()
                .FillingPaymentDetails()
                .ClickPayAndConfirm();
        Assert.assertTrue(new P11_OrderPlacedPage(getDriver()).orderConfirmedVisibility());
        // Download the invoice
        new P11_OrderPlacedPage(getDriver())
                .clickDownloadInvoice()
                .clickContinue();

        Assert.assertTrue(Utility.CheckInvoiceIsDownloaded());

    }



    @AfterMethod
    public void quit() {
        quitDriver();
    }
}
