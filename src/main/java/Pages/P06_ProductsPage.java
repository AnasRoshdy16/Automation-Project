package Pages;

import Utilities.LogsUtils;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class P06_ProductsPage {

    private static List<WebElement> allProducts;
    private final WebDriver driver;
    private final By allProductsText = By.xpath("//h2[.='All Products']");
    private final By allProductsLocator = By.xpath("//section//div[@class='col-sm-4']");
    private final By searchedProductsText = By.xpath("//h2[.='Searched Products']");
    private final By productsSearch = By.xpath("//input[@name='search']");
    private final By searchButton = By.xpath("//button[@id='submit_search']");
    private final By menTshirtTextInProduct = By.xpath("(//p[.='Men Tshirt'])[1]");
    private final By viewProductMenTshirt = By.xpath("//a[@href='/product_details/2']");
    private final By addToCartProduct1Button = By.xpath("(//a[@data-product-id='1'])[1]");
    private final By addToCartProduct2Button = By.xpath("(//a[@data-product-id='2'])[1]");
    private final By continueShoppingButton = By.xpath("//button[@class='btn btn-success close-modal btn-block']");
    private final By cartButton = By.xpath("(//a[@href='/view_cart'])[1]");


    public P06_ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean allProductsVisibility() {
        return Utility.checkVisibility(driver, allProductsText);
    }

    public P06_ProductsPage sendDataToSearchField(String productName) {
        Utility.sendData(driver, productsSearch, productName);
        return this;
    }

    public P06_ProductsPage clickOnSearchButton() {
        Utility.clickingOnElement(driver, searchButton);
        return this;
    }

    public P06_ProductsPage addingFirstProductToCart() {
        Utility.scrolling(driver, addToCartProduct1Button);
        Utility.clickingOnElement(driver, addToCartProduct1Button);
        Utility.clickingOnElement(driver, continueShoppingButton);
        return this;
    }

    public P06_ProductsPage addingSecondProductToCart() {
        Utility.scrolling(driver, addToCartProduct2Button);
        Utility.clickingOnElement(driver, addToCartProduct2Button);
        Utility.clickingOnElement(driver, continueShoppingButton);
        return this;
    }

    public P08_ViewCartPage clickViewCartButton() {
        Utility.clickingOnElement(driver, cartButton);
        return new P08_ViewCartPage(driver);
    }

    public P07_ProductDetails clickOnViewProductOnMenTshirt() {
        Utility.scrolling(driver, viewProductMenTshirt);
        Utility.clickingOnElement(driver, viewProductMenTshirt);
        return new P07_ProductDetails(driver);
    }

    public boolean searchedProductsTextVisibility() {
        return Utility.checkVisibility(driver, searchedProductsText);
    }

    public boolean searchedProductsVisibility() {
        allProducts = driver.findElements(allProductsLocator);
        for (int i = 1; i <= allProducts.size(); i++) {
            LogsUtils.info("Visibility of item number:" + i);
            By allProductsLocator = By.xpath("(//section//div[@class='col-sm-4'])[" + i + "]");
            boolean visible = Utility.checkVisibility(driver, allProductsLocator);

            if (!visible) {
                return false;
            }
        }
        return true;
    }
}