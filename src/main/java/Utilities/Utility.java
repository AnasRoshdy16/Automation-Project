package Utilities;

import com.assertthat.selenium_shutterbug.core.Capture;
import com.assertthat.selenium_shutterbug.core.Shutterbug;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Utility {
    private static final String SCREENSHOTS_PATH = "test-outputs/Screenshots/";

    public static void clickingOnElement(WebDriver driver, By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(locator));
        driver.findElement(locator).click();
    }

    public static void sendData(WebDriver driver, By locator, String data) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.findElement(locator).sendKeys(data);
    }

    public static String getText(WebDriver driver, By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator).getText();

    }

    public static WebDriverWait generalWait(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public static void scrolling(WebDriver driver, By locator) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView();", byToWebElement(driver, locator));
    }

    public static WebElement byToWebElement(WebDriver driver, By locator) {
        return driver.findElement(locator);
    }

    public static void selectingFromDropDown(WebDriver driver, By locator, String option) {
        new Select(byToWebElement(driver, locator)).selectByVisibleText(option);
    }

    public static String getTimeStamp() {
        return new SimpleDateFormat("yyyy-MM-dd-h-m-ssa").format(new Date());
    }

    public static void takeScreenShot(WebDriver driver, String screenshotName) {
        try {
            // Capture screenshot using TakesScreenshot
            File screenshotSrc = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // Save screenshot to a file if needed
            File screenshotFile = new File(SCREENSHOTS_PATH + screenshotName + "-" + getTimeStamp() + ".png");
            FileUtils.copyFile(screenshotSrc, screenshotFile);

            // Attach the screenshot to Allure
            Allure.addAttachment(screenshotName, Files.newInputStream(Path.of(screenshotFile.getPath())));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void takeFullscreenshot(WebDriver driver, By locator) {
        try {
            Shutterbug.shootPage(driver, Capture.FULL_SCROLL)
                    .highlight(byToWebElement(driver, locator)).save(SCREENSHOTS_PATH);
        } catch (Exception e) {
            LogsUtils.error(e.getMessage());
        }

    }

    public static int generateRandomnumber(int upperbound) {
        return new Random().nextInt(upperbound) + 1;
    }

    public static Set<Integer> generateUniquenumbers(int numberofProductsneeded, int totalNumberofProducts) {
        Set<Integer> generatedNumbers = new HashSet<>();
        while (generatedNumbers.size() < numberofProductsneeded) {
            int randomNumber = generateRandomnumber(totalNumberofProducts);
            generatedNumbers.add(randomNumber);
        }
        return generatedNumbers;
    }

    public static boolean verifyURL(WebDriver driver, String expectedURL) {
        try {
            generalWait(driver).until(ExpectedConditions.urlToBe(expectedURL));
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    public static void clear(WebDriver driver, By locator){
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.findElement(locator).clear();

    }

    public static boolean checkVisibility(WebDriver driver, By locator) {

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions
                        .visibilityOfElementLocated(locator));
        return Utility.byToWebElement(driver, locator).isDisplayed();
    }
    public static boolean CheckInvoiceIsDownloaded() {
        File Invoice = new File("test-outputs/Invoices/invoice.txt");


        // Check if the specified file

        // Exists or not

        return Invoice.exists();
    }

    public static void closeAdByRefreshing(WebDriver driver, By locator) {
        driver.navigate().refresh();
        try {
            clickingOnElement(driver, locator);
        } catch (Exception e) {
            LogsUtils.error(e.getMessage());
        }


    }
    public static String getProjectPath(){

     return System.getProperty("user.dir");
    }


}
