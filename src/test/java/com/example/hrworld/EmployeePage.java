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
        Kontrolki.enterAdminWithRandomNumber(driver, id);  // Zamiast wpisywać "admin", teraz generujemy losowy login
        Kontrolki.enterText(driver, "Password", password);
        Kontrolki.enterText(driver, "Password", repeatedPassword);
        Kontrolki.enterText(driver, "John", firstName);
        Kontrolki.enterText(driver, "Doe", lastName);
        Kontrolki.enterText(driver, "john.doe@example.com", email);
        Kontrolki.enterText(driver, "123456789", phone);
        Kontrolki.enterText(driver, "123 Main St", address1);
        Kontrolki.enterText(driver, "123 Main St", address2);  // Wypełnienie pola adresowego// Wypełnienie pola adresowego
        Kontrolki.enterText(driver, "Wwa", city);
        Kontrolki.enterText(driver,"Mazowieckie", state);
        Kontrolki.enterText(driver, "05-400", zip);
        Kontrolki.enterText(driver, "Polska", country);
        Kontrolki.selectOptionByValue(driver, favouriteCategory,"FISH");
        Kontrolki.selectOptionByValue(driver, languageSelector, "english");
        Kontrolki.checkCheckbox(driver, enable_MyList);
        Kontrolki.checkCheckbox(driver, enable_MyBanner);
        // Kliknięcie przycisku "Create Account"
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        WebElement submitBtn = driver.findElement(submitButton);
        submitBtn.click();
    }
}
