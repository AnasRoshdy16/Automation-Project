package Tests;
import Listeners.IInvokedMethodListenerClass;
import Listeners.ITestResultListenerClass;
import Pages.*;
import Utilities.LogsUtils;
import Utilities.Utility;
import io.qameta.allure.testng.TestInstanceParameter;
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
public class T05_ProductVerfication {
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
    public void VerifyProductDetailsTC(){
        // Click on 'Products' button
        new P01_HomePage(getDriver()).clickOnProductsButton();
        // Verify user is navigated to ALL PRODUCTS page successfully
        Assert.assertTrue(new P06_ProductsPage(getDriver()).allProductsVisibility());
        // Click on 'View Product' of first product
        new P06_ProductsPage(getDriver())
                .clickOnViewProduct();
        // Verify that product details are visible
        Assert.assertTrue(new P07_ProductDetails(getDriver()).productInformationVisibility());
    }
    @AfterMethod
    public void quit(){
        quitDriver();
    }
}
