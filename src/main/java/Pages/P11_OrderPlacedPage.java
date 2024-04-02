package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P11_OrderPlacedPage {
    private final WebDriver driver;
    private final By orderConfirmedText = By.xpath("//p[@style]");
    private final By downloadInvoice = By.xpath("//a[.='Download Invoice']");
    private final By continueButton = By.xpath("//a[@data-qa='continue-button']");


    public P11_OrderPlacedPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean orderConfirmedVisibility() {
        return Utility.checkVisibility(driver, orderConfirmedText);
    }
    public P11_OrderPlacedPage clickDownloadInvoice(){
        Utility.scrolling(driver,downloadInvoice);
        Utility.clickingOnElement(driver,downloadInvoice);

        return this;
    }
    public P01_HomePage clickContinue(){
        Utility.clickingOnElement(driver,continueButton);
        return new P01_HomePage(driver);
    }
}
