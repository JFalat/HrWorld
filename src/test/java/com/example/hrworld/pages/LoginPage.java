package com.example.hrworld;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class LoginPage extends BasePage {
    // Poczekaj, aż strona się załaduje
    private By usernameField = By.name("username");
    private By password = By.name("password");
    private By loginButton = By.name("singleton");

    public LoginPage(WebDriver driver) {
        super(driver, new WebDriverWait(driver, Duration.ofSeconds(10)));  // Wywołanie konstruktora klasy nadrzędnej z dwoma argumentami
    }

    public void login() {
        enterText("Admin", usernameField);
        enterText("password", password);
        click(loginButton);// Kliknięcie przycisku logowania
    }
}