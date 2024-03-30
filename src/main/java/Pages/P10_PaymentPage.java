package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P10_PaymentPage {
    private final WebDriver driver;
    private final By nameOnCardLocator = By.xpath("//input[@class='form-control']");
    private final By cardNumberLocator = By.xpath("//input[@name='card_number']");
    private final By cvcLocator = By.xpath("//input[@name='cvc']");
    private final By expirationMonthLocator = By.xpath("//input[@name='expiry_month']");
    private final By expirationYearLocator = By.xpath("//input[@name='expiry_year']");
    private final By payAndConfirmOrderButton = By.xpath("//button[@data-qa='pay-button']");


    public P10_PaymentPage(WebDriver driver) {
        this.driver = driver;
    }

    public P10_PaymentPage FillingPaymentDetails() {
        Utility.sendData(driver, nameOnCardLocator, "StaticName");
        Utility.sendData(driver, cardNumberLocator, "4435123412341233");
        Utility.sendData(driver, cvcLocator, "211");
        Utility.sendData(driver, expirationMonthLocator, "02");
        Utility.sendData(driver, expirationYearLocator, "2025");
        return this;
    }

    public P11_OrderPlacedPage ClickPayAndConfirm() {
        Utility.clickingOnElement(driver, payAndConfirmOrderButton);
        return new P11_OrderPlacedPage(driver);
    }


}
