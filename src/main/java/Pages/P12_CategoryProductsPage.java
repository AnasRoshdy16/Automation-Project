package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P12_CategoryProductsPage {
    private final WebDriver driver;
    private final By womenDressProductsText = By.xpath("//h2[@class='title text-center']");
    private final By menCategoryButton = By.xpath("//a[@href='#Men']");
    private final By tshirtsInCategoryButton = By.xpath("//a[@href='/category_products/3']");


    public P12_CategoryProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean womenDressProductsTitleVisibility() {
        return Utility.checkVisibility(driver, womenDressProductsText);
    }

    public P12_CategoryProductsPage clickOnMenCategoryButton() {
        Utility.scrolling(driver, menCategoryButton);
        Utility.clickingOnElement(driver, menCategoryButton);
        return this;
    }

    public P12_CategoryProductsPage clickOnTshirtInCategoryButton() {
        Utility.scrolling(driver, tshirtsInCategoryButton);
        Utility.clickingOnElement(driver, tshirtsInCategoryButton);
        return this;
    }

    public boolean VerifyCategoryPageUrl() {
        return Utility.verifyURL(driver, "https://automationexercise.com/category_products/3");
    }


}
