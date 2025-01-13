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
        TextField.enterAdminWithRandomNumber(driver, id);  // Zamiast wpisywać "admin", teraz generujemy losowy login
        TextField.enterText(driver, "Password", password);
        TextField.enterText(driver, "Password", repeatedPassword);
        TextField.enterText(driver, "John", firstName);
        TextField.enterText(driver, "Doe", lastName);
        TextField.enterText(driver, "john.doe@example.com", email);
        TextField.enterText(driver, "123456789", phone);
        TextField.enterText(driver, "123 Main St", address1);
        TextField.enterText(driver, "123 Main St", address2);  // Wypełnienie pola adresowego// Wypełnienie pola adresowego
        TextField.enterText(driver, "Wwa", city);
        TextField.enterText(driver,"Mazowieckie", state);
        TextField.enterText(driver, "05-400", zip);
        TextField.enterText(driver, "Polska", country);
        SelectClass.selectOptionByValue(driver, favouriteCategory,"FISH");
        SelectClass.selectOptionByValue(driver, languageSelector, "english");
        CheckboxHelper.checkCheckbox(driver, enable_MyList);
        CheckboxHelper.checkCheckbox(driver, enable_MyBanner);
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
