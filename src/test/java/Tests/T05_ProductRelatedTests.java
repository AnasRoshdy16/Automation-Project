package Tests;

import Listeners.IInvokedMethodListenerClass;
import Listeners.ITestResultListenerClass;
import Pages.P01_HomePage;
import Pages.P06_ProductsPage;
import Pages.P07_ProductDetails;
import Pages.P08_ViewCartPage;
import Utilities.LogsUtils;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

import static DriverFactory.DriverFactory.*;
import static Utilities.DataUtils.getPropertyValue;


@Listeners({IInvokedMethodListenerClass.class, ITestResultListenerClass.class})
public class T05_ProductRelatedTests {
    @BeforeMethod
    public void setup() throws IOException {
        setupDriver(getPropertyValue("environment", "Browser"));
        LogsUtils.info(" Edge Driver is opened ");
        getDriver().get(getPropertyValue("environment", "HOME_URL"));
        LogsUtils.info(" Page is redirected to the URL ");
        getDriver().manage().
                timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test(priority = 1)
    public void VerifyProductDetailsTC() {
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

    @Test(priority = 2)
    public void SearchProductTC() {
        // Click on 'Products' button
        new P01_HomePage(getDriver()).clickOnProductsButton();
        // Verify user is navigated to ALL PRODUCTS page successfully
        Assert.assertTrue(new P06_ProductsPage(getDriver()).allProductsVisibility());
        // Enter product name in search input and click search button
        new P06_ProductsPage(getDriver())
                .sendDataToSearchField("Tshirt")
                .clickOnSearchButton();
        // Verify 'SEARCHED PRODUCTS' is visible
        Assert.assertTrue(new P06_ProductsPage(getDriver()).searchedProductsTextVisibility());
        // Verify all the products related to search are visible
        Assert.assertTrue(new P06_ProductsPage(getDriver()).searchedProductsVisibility());
    }

    @Test(priority = 3)
    public void AddProductsToCartTC() {
        // Click on 'Products' button
        new P01_HomePage(getDriver()).clickOnProductsButton()
                // Adding First two products in the cart
                .addingFirstProductToCart()
                .addingSecondProductToCart()
                // Clicking on cart button to view cart page
                .clickViewCartButton();
        // Asserting visibility of product one attributes and Comparing Total Prices
        Assert.assertTrue(new P08_ViewCartPage(getDriver()).product1Visibility());
        Assert.assertTrue(new P08_ViewCartPage(getDriver()).product2Visibility());
        Assert.assertTrue(new P08_ViewCartPage(getDriver()).product1PriceVisibility());
        Assert.assertTrue(new P08_ViewCartPage(getDriver()).product1QuantityVisibility());
        Assert.assertTrue(new P08_ViewCartPage(getDriver()).product1TotalVisibility());
        Assert.assertTrue(new P08_ViewCartPage(getDriver()).comparingProduct1TotalPrices());


    }

    @AfterMethod
    public void quit() {
        quitDriver();
    }
}
