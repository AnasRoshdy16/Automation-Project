package Tests;
import Listeners.IInvokedMethodListenerClass;
import Listeners.ITestResultListenerClass;
import Pages.P01_HomePage;
import Pages.P02_LoginPage;
import Pages.P05_DeleteAccountPage;
import Pages.P06_ProductsPage;
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
public class T06_ProductSearch {
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
    public void SearchProductTC(){
        // Click on 'Products' button
        new P01_HomePage(getDriver()).clickOnProductsButton();
        // Verify user is navigated to ALL PRODUCTS page successfully
        Assert.assertTrue(new P06_ProductsPage(getDriver()).allProductsVisibility());
        // Enter product name in search input and click search button
        new P06_ProductsPage(getDriver())
                .sendDataToSearchField("men Tshirt")
                .clickOnSearchButton();
        // Verify 'SEARCHED PRODUCTS' is visible
        Assert.assertTrue(new P06_ProductsPage(getDriver()).searchedProductsVisibility());
        // Verify all the products related to search are visible
        Assert.assertTrue(new P06_ProductsPage(getDriver()).menTshirtProductsVisibility());
    }
    @AfterMethod
    public void quit(){
        quitDriver();
    }
}
