package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P05_DeleteAccountPage {
    private final WebDriver driver;
    private final By accountDeletedMessage = By.xpath("//b[.='Account Deleted!']");
    private final By continueButton = By.xpath("//a[@data-qa='continue-button']");

    public P05_DeleteAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean AccountDeletedMsgVisibility() {
        return Utility.checkVisibility(driver, accountDeletedMessage);
    }

    public P01_HomePage clickContinueButton() {
        Utility.clickingOnElement(driver, continueButton);
        return new P01_HomePage(driver);
    }

}
