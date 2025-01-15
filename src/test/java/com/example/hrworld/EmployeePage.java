package com.example.hrworld;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EmployeePage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Zlokalizowanie elementów typu WebElement
    private By id = By.name("username");
    private By password = By.name("password");
    private By repeatedPassword = By.name("repeatedPassword");
    private By firstName = By.name("account.firstName");
    private By lastName = By.name("account.lastName");
    private By email = By.name("account.email");
    private By phone = By.name("account.phone");
    private By address1 = By.name("account.address1");
    private By address2 = By.name("account.address2");
    private By city = By.name("account.city");  // Nowe pole adresowe
    private By state = By.name("account.state");  // Nowe pole adresowe
    private By zip = By.name("account.zip");
    private By country = By.name("account.country");// Nowe pole adresowe
    // Nowe pole adresowe
    private By favouriteCategory = By.name("account.favouriteCategoryId");
    private By languageSelector = By.name("account.languagePreference");
    private By submitButton = By.name("newAccount");
    By enable_MyList = By.cssSelector("input[type='checkbox'][name='account.listOption']");
    By enable_MyBanner = By.cssSelector("input[type='checkbox'][name='account.bannerOption']");


    public void enterUserData(WebDriver driver) throws InterruptedException {
        // Wypełnienie danych użytkownika
        BasePage basePage = new BasePage(driver);
        basePage.enterAdminWithRandomNumber(id);  // Zamiast wpisywać "admin", teraz generujemy losowy login
        basePage.enterText("Password", password);
        basePage.enterText("Password", repeatedPassword);
        basePage.enterText("John", firstName);
        basePage.enterText("Doe", lastName);
        basePage.enterText("john.doe@example.com", email);
        basePage.enterText("123456789", phone);
        basePage.enterText("123 Main St", address1);
        basePage.enterText("123 Main St", address2);  // Wypełnienie pola adresowego// Wypełnienie pola adresowego
        basePage.enterText("Wwa", city);
        basePage.enterText("Mazowieckie", state);
        basePage.enterText("05-400", zip);
        basePage.enterText("Polska", country);
        basePage.selectOptionByValue(favouriteCategory,"FISH");
        basePage.selectOptionByValue(languageSelector, "english");
        basePage.checkCheckbox(enable_MyList);
        basePage.checkCheckbox(enable_MyBanner);
        // Kliknięcie przycisku "Create Account"
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
       basePage.click(submitButton);
    }
}
