package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P01_HomePage {
    private final By homeButton = By.xpath("//a[.=' Home']");
    private final By signupOrLoginButton = By.xpath("//a[@href='/login']");
    private final By loggedInMessage = By.xpath("//a[text()=' Logged in as ']");
    private final By deleteAccountButton = By.xpath("//div[2]/div/ul/li[5]/a");
    private WebDriver driver;

    public P01_HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean HomeButtonVisibility() {
        return Utility.checkVisibility(driver, homeButton);
    }

    public P02_LoginPage clickOnSignupButton() {
        Utility.clickingOnElement(driver, signupOrLoginButton);
        return new P02_LoginPage(driver);
    }
    public boolean loggedInVisibility() {
        return Utility.checkVisibility(driver, loggedInMessage);
    }
    public P05_DeleteAccountPage clickDeleteAccount(){
        Utility.clickingOnElement(driver,deleteAccountButton);
        return new P05_DeleteAccountPage(driver);
    }

}
