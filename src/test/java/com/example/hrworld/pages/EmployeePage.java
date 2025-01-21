package com.example.hrworld.pages;

import com.example.hrworld.businessObject.Account;
import com.example.hrworld.businessObject.Profile;
import com.example.hrworld.businessObject.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.Random;

public class EmployeePage extends BasePage {

    // Konstruktor przyjmujący WebDriver
    public EmployeePage(WebDriver driver) {
        super(driver);
    }

    // Lokatory elementów
    private By id = By.name("username");
    private By password = By.name("password");
    private By repeatedPassword = By.name("repeatedPassword");
    private By firstName = By.name("account.firstName");
    private By lastName = By.name("account.lastName");
    private By email = By.name("account.email");
    private By phone = By.name("account.phone");
    private By address1 = By.name("account.address1");
    private By address2 = By.name("account.address2");
    private By city = By.name("account.city");
    private By state = By.name("account.state");
    private By zip = By.name("account.zip");
    private By country = By.name("account.country");
    private By favouriteCategory = By.name("account.favouriteCategoryId");
    private By languageSelector = By.name("account.languagePreference");
    private By submitButton = By.name("newAccount");
    private By enable_MyList = By.cssSelector("input[type='checkbox'][name='account.listOption']");
    private By enable_MyBanner = By.cssSelector("input[type='checkbox'][name='account.bannerOption']");
    private By signInLink = By.linkText("Sign In");
    private By registrationLink = By.linkText("Register Now!");

    // Kliknięcie linku "Sign In"
    public void clickSignIn() {
        click(signInLink);
    }

    // Kliknięcie linku "Register Now!"
    public void clickRegistration() {
        click(registrationLink);
    }

    // Wprowadzenie nazwy użytkownika z losowym numerem
    public void enterAdminWithRandomNumber(By locator) {
        Random rand = new Random();
        int randomNumber = rand.nextInt(900) + 100; // Generowanie liczby od 100 do 999
        String username = "admin" + randomNumber;  // Tworzenie ciągu "adminXXX"
        enterText(username, locator);
    }

    // Wypełnienie danych użytkownika
    public void enterUserData(User user) {
        // Wypełnienie danych logowania
        enterAdminWithRandomNumber(id);
        enterText(user.getPasswordValue(), password);
        enterText(user.getPasswordValue(), repeatedPassword);

        // Wypełnienie danych konta
        Account account = user.getAccount();
        enterText(account.getName(), firstName);
        enterText(account.getLastname(), lastName);
        enterText(account.getMail(), email);
        enterText(account.getTelephone(), phone);
        enterText(account.getAddress1(), address1);
        enterText(account.getAddress2(), address2);
        enterText(account.getTown(), city);
        enterText(account.getState(), state);
        enterText(account.getCityCode(), zip);
        enterText(account.getCountry(), country);

        // Wypełnienie danych profilu
        Profile profile = user.getProfile();
        selectOptionByValue(profile.getFavouriteCategory(), favouriteCategory);
        selectOptionByValue(profile.getLanguageSelector(), languageSelector);

        // Zaznaczanie checkboxów
        handleCheckbox(enable_MyList, profile.isEnable_MyList());
        handleCheckbox(enable_MyBanner, profile.isEnable_MyBanner());

        // Kliknięcie przycisku submit
        click(submitButton);
    }
}
