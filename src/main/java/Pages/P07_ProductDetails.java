package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P07_ProductDetails {
    private final WebDriver driver;
    private final By productInformation = By.xpath("//div[@class='product-information']");
    private final By quantity = By.xpath("//input[@name='quantity']");
    private final By addToCartButton = By.xpath("//button[@type='button']");
    private final By viewCartButton = By.xpath("(//a[@href='/view_cart'])[2]");

    public P07_ProductDetails(WebDriver driver) {
        this.driver=driver;
    }

    public boolean productInformationVisibility(){
        return Utility.checkVisibility(driver,productInformation);
    }
    public P07_ProductDetails increaseQuantity(String quantityValue){
        Utility.clear(driver,quantity);
        Utility.sendData(driver,quantity,quantityValue);
        return this;
    }
    public P07_ProductDetails clickAddToCartButton(){
        Utility.clickingOnElement(driver,addToCartButton);
        return this;
    }
    public P08_ViewCartPage clickViewCartButton(){
        Utility.clickingOnElement(driver,viewCartButton);
        return new P08_ViewCartPage(driver);
    }
}
