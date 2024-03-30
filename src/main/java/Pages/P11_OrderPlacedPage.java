package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P11_OrderPlacedPage {
    private final WebDriver driver;
    private final By orderConfirmedText =
            By.xpath("//p[@style]");

    public P11_OrderPlacedPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean orderConfirmedVisibility() {
        return Utility.checkVisibility(driver, orderConfirmedText);
    }
}
