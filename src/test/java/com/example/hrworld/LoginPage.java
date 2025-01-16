package com.example.hrworld;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class LoginPage extends BasePage {
    // Poczekaj, aż strona się załaduje
    private By usernameField = By.name("username");
    private By password = By.name("password");
    private By loginButton = By.name("singleton");

public LoginPage(WebDriver driver) {
            super(driver);
        }

public void login() {
    enterText("Admin", usernameField);
    enterText("password", password);
    click(loginButton);// Kliknięcie przycisku logowania
    }
}
