package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P04_AccountCreatedPage {
    private final WebDriver driver;
    private final By AccountCreatedText = By.xpath("//b[.='Account Created!']");
    private final By continueButton = By.xpath("//a[@data-qa='continue-button']");

    public P04_AccountCreatedPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean AccountCreatedTextVisibility() {
        return Utility.checkVisibility(driver, AccountCreatedText);
    }

    public P01_HomePage clickContinueButton() {
        Utility.clickingOnElement(driver, continueButton);
        Utility.closeAdByRefreshing(driver, continueButton);
        return new P01_HomePage(driver);
    }
}
