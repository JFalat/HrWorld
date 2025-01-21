package com.example.hrworld.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    private By usernameField = By.name("username");
    private By password = By.name("password");
    private By loginButton = By.name("singleton");

    // Konstruktor przyjmujący WebDriver
    public LoginPage(WebDriver driver) {
        super(driver); // Przekazanie WebDriver do klasy nadrzędnej
    }

    public void login() {
        enterText("Admin", usernameField);
        enterText("password", password);
        click(loginButton);
    }
}
