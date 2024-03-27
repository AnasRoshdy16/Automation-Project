package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P03_SignupPage {
    private final WebDriver driver;

    private final By EnterAccountInformationText = By.xpath("//b[.='Enter Account Information']");
    private final By TitleButton = By.id("id_gender1");
    private final By PasswordLane = By.id("password");
    private final By DaysDropdown = By.id("days");
    private final By MonthsDropdown = By.id("months");
    private final By YearsDropdown = By.id("years");
    private final By NewsletterCheckBox = By.id("newsletter");
    private final By SpecialOffersCheckBox = By.id("optin");
    private final By FirstName = By.id("first_name");
    private final By LastName = By.id("last_name");
    private final By Company = By.id("company");
    private final By Address1 = By.id("address1");
    private final By Address2 = By.id("address2");
    private final By CountryDropdown = By.id("country");
    private final By State = By.id("state");
    private final By City = By.id("city");
    private final By Zipcode = By.id("zipcode");
    private final By MobileNumber = By.id("mobile_number");
    private final By CreateAccountButton = By.xpath("//button[@data-qa='create-account']");


    public P03_SignupPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean AccountInformationTextVisibility() {
        return Utility.checkVisibility(driver, EnterAccountInformationText);
    }

    public P03_SignupPage clickTitleButton() {
        Utility.clickingOnElement(driver, TitleButton);
        return this;
    }

    public P03_SignupPage EnterPassword(String PasswordText) {
        Utility.sendData(driver, PasswordLane, PasswordText);
        return this;
    }

    public P03_SignupPage SelectingDayDropdown(String DayOption) {
        Utility.sendData(driver, DaysDropdown, DayOption);
        return this;
    }

    public P03_SignupPage SelectingMonthDropDown(String MonthOption) {
        Utility.sendData(driver, MonthsDropdown, MonthOption);
        return this;
    }

    public P03_SignupPage SelectingYearDropdown(String YearOption) {
        Utility.sendData(driver, YearsDropdown, YearOption);
        return this;
    }

    public P03_SignupPage ClickNewsLetterCheckbox() {
        Utility.scrolling(driver, NewsletterCheckBox);
        Utility.clickingOnElement(driver, NewsletterCheckBox);
        return this;
    }

    public P03_SignupPage ClickSpecialOffersCheckbox() {
        Utility.scrolling(driver, SpecialOffersCheckBox);
        Utility.clickingOnElement(driver, SpecialOffersCheckBox);
        return this;
    }

    public P03_SignupPage EnterFirstName(String FirstnameText) {
        Utility.sendData(driver, FirstName, FirstnameText);
        return this;
    }

    public P03_SignupPage EnterLastName(String LastnameText) {
        Utility.sendData(driver, LastName, LastnameText);
        return this;
    }

    public P03_SignupPage EnterCompanyName(String CompanynameText) {
        Utility.sendData(driver, Company, CompanynameText);
        return this;
    }

    public P03_SignupPage EnterAddress1Name(String Address1nameText) {
        Utility.sendData(driver, Address1, Address1nameText);
        return this;
    }

    public P03_SignupPage EnterAddress2Name(String Address2nameText) {
        Utility.sendData(driver, Address2, Address2nameText);
        return this;
    }

    public P03_SignupPage SelectingCountryDropdown(String CountryOption) {
        Utility.sendData(driver, CountryDropdown, CountryOption);
        return this;
    }

    public P03_SignupPage EnterStateName(String StateText) {
        Utility.sendData(driver, State, StateText);
        return this;
    }

    public P03_SignupPage EnterCityName(String CityText) {
        Utility.sendData(driver, City, CityText);
        return this;
    }

    public P03_SignupPage EnterZipCode(String ZipcodeText) {
        Utility.sendData(driver, Zipcode, ZipcodeText);
        return this;
    }

    public P03_SignupPage EnterMobileNumber(String MobileNumberText) {
        Utility.sendData(driver, MobileNumber, MobileNumberText);
        return this;
    }

    public P04_AccountCreatedPage clickCreateAccountButton() {
        Utility.scrolling(driver, CreateAccountButton);
        Utility.clickingOnElement(driver, CreateAccountButton);
        return new P04_AccountCreatedPage(driver);
    }

    public P03_SignupPage signUpSteps() {
        Utility.clickingOnElement(driver, TitleButton);
        Utility.sendData(driver, PasswordLane, "Test1234");
        Utility.sendData(driver, DaysDropdown, "2");
        Utility.sendData(driver, MonthsDropdown, "February");
        Utility.sendData(driver, YearsDropdown, "1994");
        Utility.scrolling(driver, NewsletterCheckBox);
        Utility.clickingOnElement(driver, NewsletterCheckBox);
        Utility.scrolling(driver, SpecialOffersCheckBox);
        Utility.clickingOnElement(driver, SpecialOffersCheckBox);
        Utility.sendData(driver, FirstName, "Ammar");
        Utility.sendData(driver, LastName, "Elfeky");
        Utility.sendData(driver, Company, "Jobless");
        Utility.sendData(driver, Address1, "Cairo");
        Utility.sendData(driver, Address2, "NotCairo");
        Utility.sendData(driver, CountryDropdown, "Canada");
        Utility.sendData(driver, State, "IDK");
        Utility.sendData(driver, City, "Giza");
        Utility.sendData(driver, Zipcode, "12345");
        Utility.sendData(driver, MobileNumber, "01001212111");
        Utility.scrolling(driver, CreateAccountButton);
        Utility.clickingOnElement(driver, CreateAccountButton);
        return this;
    }

}
