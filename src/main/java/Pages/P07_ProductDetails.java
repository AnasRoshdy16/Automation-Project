package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P07_ProductDetails {
    private final WebDriver driver;
    private final By productInformation = By.xpath("//div[@class='product-information']");

    public P07_ProductDetails(WebDriver driver) {
        this.driver=driver;
    }

    public boolean productInformationVisibility(){
        return Utility.checkVisibility(driver,productInformation);
    }
}
