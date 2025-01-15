package com.example.hrworld;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class LoginPage extends BasePage {
    // Poczekaj, aż strona się załaduje
        @FindBy(name = "username")
        private WebElement usernameField;

        @FindBy(name="password")
        private WebElement passwordField;

        @FindBy(name="singon")
        private WebElement loginButton;

public LoginPage(WebDriver driver) {
            super(driver);
        }

public void login(String username, String password) {
        usernameField.sendKeys(username);  // Wpisanie nazwy użytkownika
        passwordField.sendKeys(password);  // Wpisanie hasła
        loginButton.click();               // Kliknięcie przycisku logowania
    }
}
