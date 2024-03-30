package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P01_HomePage {
    private final By homeButton = By.xpath("//a[.=' Home']");
    private final By signupOrLoginButton = By.xpath("//a[@href='/login']");
    private final By loggedInMessage = By.xpath("//a[text()=' Logged in as ']");
    private final By deleteAccountButton = By.xpath("//a[@href='/delete_account']");
    private final By logoutButton = By.xpath("//a[@href='/logout']");
    private final By productsButton = By.xpath("//a[@href='/products']");
    private final By categoryProductsText = By.xpath("//div[@class='panel-group category-products']");
    private final By womenCategoryButton = By.xpath("//a[@href='#Women']");
    private final By dressInCategoryButton = By.xpath("//a[@href='/category_products/1']");

    private WebDriver driver;

    public P01_HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean HomeButtonVisibility() {
        return Utility.checkVisibility(driver, homeButton);
    }

    public boolean CategoryVisibility() {
        return Utility.checkVisibility(driver, categoryProductsText);
    }

    public P01_HomePage clickOnWomenCategoryButton() {
        Utility.clickingOnElement(driver, womenCategoryButton);
        Utility.closeAdByRefreshing(driver, womenCategoryButton);
        return this;
    }

    public P12_CategoryProductsPage clickOnDressInWomenCategoryButton() {
        Utility.clickingOnElement(driver, dressInCategoryButton);
        Utility.closeAdByRefreshing(driver, dressInCategoryButton);
        return new P12_CategoryProductsPage(driver);
    }

    public P02_LoginPage clickOnSignupButton() {
        Utility.clickingOnElement(driver, signupOrLoginButton);
        return new P02_LoginPage(driver);
    }


    public boolean loggedInVisibility() {
        return Utility.checkVisibility(driver, loggedInMessage);
    }

    public P05_DeleteAccountPage clickDeleteAccount() {
        Utility.clickingOnElement(driver, deleteAccountButton);
        return new P05_DeleteAccountPage(driver);
    }

    public P02_LoginPage clickOnLogoutButton() {
        Utility.clickingOnElement(driver, logoutButton);
        return new P02_LoginPage(driver);
    }

    public P06_ProductsPage clickOnProductsButton() {
        Utility.clickingOnElement(driver, productsButton);
        Utility.closeAdByRefreshing(driver, productsButton);
        return new P06_ProductsPage(driver);
    }

}
