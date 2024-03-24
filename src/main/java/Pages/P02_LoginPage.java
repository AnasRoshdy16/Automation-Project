package Pages;

import Utilities.LogsUtils;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P02_LoginPage {
    private final WebDriver driver;

    private By NewSignUpText = By.xpath("//h2[.='New User Signup!']");
    private By Name = By.xpath("//input[@name='name']");
    private By Email = By.xpath("//input[@name='email']");
    private By SignupButton = By.xpath("//button[@data-qa='signup-button']");

    public P02_LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean NewSignupVisibility() {
        return Utility.checkVisibility(driver, NewSignUpText);
    }

    public P02_LoginPage Entername(String Nametext) {
        Utility.sendData(driver, Name, Nametext);
        LogsUtils.info("Username is added");
        return this;
    }

    public P02_LoginPage Enteremail(String emailtext) {
        Utility.sendData(driver, Name, emailtext);
        LogsUtils.info("email is added");
        return this;
    }

    public P03_SignupPage clickOnSignupButton() {
        Utility.clickingOnElement(driver, SignupButton);
        return new P03_SignupPage(driver);
    }

}
