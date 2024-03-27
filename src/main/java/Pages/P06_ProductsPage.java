package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P06_ProductsPage {

    private final WebDriver driver;
    private final By allProductsText = By.xpath("//h2[.='All Products']");
    private final By searchedProductsText = By.xpath("//h2[.='Searched Products']");
    private final By productsSearch = By.xpath("//input[@name='search']");
    private final By searchButton = By.xpath("//button[@id='submit_search']");
    private final By menTshirtTextInProduct = By.xpath("//div[contains(@class,'productinfo')]/p[.='Men Tshirt']");


    public P06_ProductsPage(WebDriver driver) {
        this.driver=driver;
    }

    public boolean allProductsVisibility(){
         return Utility.checkVisibility(driver, allProductsText);
    }
    public P06_ProductsPage sendDataToSearchField(String productName){
        Utility.sendData(driver,productsSearch,productName);
        return this;
    }
    public P06_ProductsPage clickOnSearchButton(){
        Utility.clickingOnElement(driver,searchButton);
        return this;
    }
    public boolean searchedProductsVisibility(){
        return Utility.checkVisibility(driver, searchedProductsText);
    }
    public boolean menTshirtProductsVisibility(){
        return Utility.checkVisibility(driver, menTshirtTextInProduct);
    }


}
