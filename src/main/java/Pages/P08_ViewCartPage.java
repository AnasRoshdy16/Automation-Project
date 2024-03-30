package Pages;

import Utilities.LogsUtils;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P08_ViewCartPage {
    public static Float price1;
    private final WebDriver driver;
    private final By product1InCart = By.xpath("//a[@href='/product_details/1']");
    private final By product2InCart = By.xpath("//a[@href='/product_details/2']");
    private final By product1PriceLocator = By.xpath("//td[@class='cart_price']//p[.='Rs. 500']");
    private final By product1Quantity = By.xpath("(//button[@class='disabled'])[1]");
    private final By product1TotalLocator = By.xpath("//td[@class='cart_total']//p[.='Rs. 500']");
    private final By proceedToCheckoutButton = By.xpath("//a[@class='btn btn-default check_out']");
    public Float price1Total;

    public P08_ViewCartPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean product1Visibility() {
        return Utility.checkVisibility(driver, product1InCart);
    }

    public boolean product1PriceVisibility() {
        return Utility.checkVisibility(driver, product1PriceLocator);
    }

    public boolean product1QuantityVisibility() {
        return Utility.checkVisibility(driver, product1Quantity);
    }

    public boolean product1TotalVisibility() {
        return Utility.checkVisibility(driver, product1TotalLocator);
    }

    public boolean product2Visibility() {
        return Utility.checkVisibility(driver, product2InCart);
    }

    public Float getProduct1Price() {
        String priceText = Utility.getText(driver, product1PriceLocator);
        price1 = Float.parseFloat(priceText.replace("Rs. ", ""));
        return price1;

    }

    public Integer getProduct1Quantity() {
        String Product1QuantityText = Utility.getText(driver, product1Quantity);
        return Integer.valueOf(Product1QuantityText);
    }

    public String getProduct1TotalPrice() {
        String price1TotalText = Utility.getText(driver, product1TotalLocator);
        price1Total = Float.parseFloat(price1TotalText.replace("Rs. ", ""));
        return String.valueOf(price1Total);
    }

    public String calculateProduct1TotalPrice() {
        Float Product1TotalPrice = getProduct1Price() * getProduct1Quantity();
        return String.valueOf(Product1TotalPrice);
    }

    public boolean comparingProduct1TotalPrices() {
        LogsUtils.info("Comparing the product total price with the calculated total");
        return getProduct1TotalPrice().equals(calculateProduct1TotalPrice());
    }

    public String getProductQuantity() {
        return Utility.getText(driver, product1Quantity);
    }

    public P09_CheckoutPage clickProceedToCheckout() {
        Utility.clickingOnElement(driver, proceedToCheckoutButton);
        return new P09_CheckoutPage(driver);
    }


}
