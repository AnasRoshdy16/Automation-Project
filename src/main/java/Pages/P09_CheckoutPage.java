package Pages;

import Utilities.LogsUtils;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P09_CheckoutPage {
    private final WebDriver driver;
    private final By addressDetailsLocator = By.xpath("//div[@class='checkout-information']");
    private final By orderDetailsLocator = By.xpath("//div[@class='table-responsive cart_info']");
    private final By commentAreaLocator = By.xpath("//textarea[@class='form-control']");
    private final By placeOrderButton = By.xpath("//a[@href='/payment']");
    private final By deliveryAddressLocator = By.xpath("(//li[.='Cairo'])[1]");
    private final By billingAddressLocator = By.xpath("(//li[.='NotCairo'])[2]");

    public P09_CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean addressDetailsVisibility() {
        LogsUtils.info("Address Details are visible");
        return Utility.checkVisibility(driver, addressDetailsLocator);
    }

    public boolean orderDetailsVisibility() {
        LogsUtils.info("order Details are visible");
        return Utility.checkVisibility(driver, orderDetailsLocator);
    }

    public P09_CheckoutPage addingcomment(String Comment) {
        Utility.scrolling(driver, commentAreaLocator);
        Utility.sendData(driver, commentAreaLocator, Comment);
        return new P09_CheckoutPage(driver);
    }

    public String getDeliveryAddress() {
        return Utility.getText(driver, deliveryAddressLocator);

    }

    public String getBillingAddress() {
        return Utility.getText(driver, billingAddressLocator);

    }

    public Boolean ComparingDeliveryAddresses() {
        LogsUtils.info("Delivery Address in Checkout is: " + getDeliveryAddress() + " and equals to Cairo ");
        return getDeliveryAddress().equals("Cairo");
    }

    public Boolean ComparingBillingAddresses() {
        return getBillingAddress().equals("NotCairo");
    }

    public P10_PaymentPage placingOrder() {
        Utility.clickingOnElement(driver, placeOrderButton);
        return new P10_PaymentPage(driver);
    }
}
