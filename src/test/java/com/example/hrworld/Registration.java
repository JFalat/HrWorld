package com.example.hrworld;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Registration {
    private WebDriver driver;

    // Lokalizator dla linku "Sign In"
    private By signInLink = By.linkText("Sign In");
    private By registrationLink = By.linkText("Register Now!");


    // Konstruktor
    public Registration(WebDriver driver) {
        this.driver = driver;
    }

    // Metoda do kliknięcia w link "Sign In"
    public void clickSignIn() {
        WebElement signIn = driver.findElement(signInLink);
        signIn.click();
    }
    public void clickRegistration() {
        WebElement registration = driver.findElement(registrationLink);
        registration.click();
    }
}