package com.example.hrworld;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;

public class EmployeePage extends BasePage {

    public EmployeePage(WebDriver driver) {
        super(driver, new WebDriverWait(driver, Duration.ofSeconds(10)));  // Wywołanie konstruktora klasy nadrzędnej z dwoma argumentami
    }


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
    private By country = By.name("account.country");  // Nowe pole adresowe
    private By favouriteCategory = By.name("account.favouriteCategoryId");
    private By languageSelector = By.name("account.languagePreference");
    private By submitButton = By.name("newAccount");
    private By enable_MyList = By.cssSelector("input[type='checkbox'][name='account.listOption']");
    private By enable_MyBanner = By.cssSelector("input[type='checkbox'][name='account.bannerOption']");
    private By signInLink = By.linkText("Sign In");
    private By registrationLink = By.linkText("Register Now!");

    public void clickSignIn() {
        WebElement signIn = driver.findElement(signInLink);
        signIn.click();
    }

    public void clickRegistration() {
        WebElement registration = driver.findElement(registrationLink);
        registration.click();
    }

    public void enterAdminWithRandomNumber(By locator) {
        Random rand = new Random();
        int randomNumber = rand.nextInt(900) + 100;  // Generowanie liczby od 100 do 999
        String username = "admin" + randomNumber;  // Tworzenie ciągu "adminXXX"
        enterText(username, locator);  // Wpisanie tekstu w pole
    }

    public void enterUserData(User user) {
        // Wypełnienie danych użytkownika
        enterAdminWithRandomNumber(id);
        enterText(user.getPasswordValue(), password);  // Lokalizator pola hasła
        enterText(user.getRepeatedPasswordValue(), repeatedPassword);

        // Wypełnienie danych konta
        Account account = user.getAccount();
        enterText(account.getName(), firstName);
        enterText(account.getLastname(), lastName);
        enterText(account.getMail(), email);
        enterText(account.getTelephone(), phone);
        enterText(account.getAddress1(), address1);
        enterText(account.getAddress2(), address2);  // Wypełnienie pola adresowego
        enterText(account.getTown(), city);
        enterText(account.getState(), state);
        enterText(account.getCityCode(), zip);
        enterText(account.getCountry(), country);

        // Wypełnienie danych profilu
        Profile profile = user.getProfile();
        selectOptionByValue(profile.getFavouriteCategory(), favouriteCategory);
        selectOptionByValue(profile.getLanguageSelector(), languageSelector);

        // Zaznaczanie checkboxów
        handleCheckbox(enable_MyList, true); // Zaznacz checkbox
        handleCheckbox(enable_MyBanner, true); // Zaznacz checkbox

        // Kliknięcie przycisku submit
        click(submitButton);
    }
}
