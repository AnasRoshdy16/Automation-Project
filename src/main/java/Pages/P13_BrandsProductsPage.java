package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P13_BrandsProductsPage {
    private final WebDriver driver;
    private final By HnMText = By.xpath("//h2[.='Brand - H&M Products']");
    private final By babyHugText = By.xpath("//h2[.='Brand - Babyhug Products']");
    private final By babyHugBrand = By.xpath("//a[@href='/brand_products/Babyhug']");


    public P13_BrandsProductsPage(WebDriver driver) {
        this.driver=driver;
    }
    public boolean HnMTextVisibility(){
        return Utility.checkVisibility(driver,HnMText);
    }
    public P13_BrandsProductsPage clickOnBabyHugBrand(){
        Utility.clickingOnElement(driver,babyHugBrand);
        return this;
    }
    public boolean babyHugTextVisibility(){
        return Utility.checkVisibility(driver,babyHugText);
    }


}
