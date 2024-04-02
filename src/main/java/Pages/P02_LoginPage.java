package Pages;

import Utilities.LogsUtils;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P02_LoginPage {
    private final WebDriver driver;

    private By NewSignUpText = By.xpath("//h2[.='New User Signup!']");
    private By Name = By.xpath("//input[@data-qa='signup-name']");
    private By Email = By.xpath("//input[@data-qa='signup-email']");
    private By LoginEmail = By.xpath("//input[@data-qa='login-email']");
    private By Password = By.xpath("//input[@data-qa='login-password']");
    private By SignupButton = By.xpath("//button[@data-qa='signup-button']");
    private By LoginButton = By.xpath("//button[@data-qa='login-button']");

    private By LoginToYourAccountText = By.xpath("//h2[.='Login to your account']");
    private By IncorrectUsernameOrPasswordText = By.xpath("//p[.='Your email or password is incorrect!']");

    public P02_LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean NewSignupVisibility() {
        return Utility.checkVisibility(driver, NewSignUpText);
    }

    public boolean LoginToYourAccountVisibility() {
        return Utility.checkVisibility(driver, LoginToYourAccountText);
    }

    public boolean IncorrectUsernameOrPasswordVisibility() {
        return Utility.checkVisibility(driver, IncorrectUsernameOrPasswordText);
    }

    public P02_LoginPage Entername(String Nametext) {
        Utility.sendData(driver, Name, Nametext);
        LogsUtils.info("Username is added");
        return this;
    }

    public P02_LoginPage Enteremail(String emailtext) {
        Utility.sendData(driver, Email, emailtext);
        LogsUtils.info("email is added");
        return this;
    }

    public P02_LoginPage EnterLoginEmail(String LoginEmailtext) {
        Utility.sendData(driver, LoginEmail, LoginEmailtext);
        LogsUtils.info("email is added");
        return this;
    }

    public P02_LoginPage EnterPassword(String Passwordtext) {
        Utility.sendData(driver, Password, Passwordtext);
        LogsUtils.info("password is added");
        return this;
    }

    public P03_SignupPage clickOnSignupButton() {
        Utility.clickingOnElement(driver, SignupButton);
        return new P03_SignupPage(driver);
    }

    public P01_HomePage clickOnLoginButton() {
        Utility.clickingOnElement(driver, LoginButton);
        return new P01_HomePage(driver);
    }

    public P03_SignupPage signUpInLoginPageSteps() {
        Utility.sendData(driver, Name, "StaticName");
        Utility.sendData(driver, Email, "Static@gmail14.com");
        Utility.clickingOnElement(driver, SignupButton);
        return new P03_SignupPage(driver);
    }
    public P03_SignupPage loginSteps(String LoginEmailtext,String PasswordText) {
        Utility.sendData(driver, LoginEmail, LoginEmailtext);
        Utility.sendData(driver, Password, PasswordText);
        Utility.clickingOnElement(driver, LoginButton);
        return new P03_SignupPage(driver);
    }

}
