package com.example.hrworld;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
    public void login(){
    // Poczekaj, aż strona się załaduje
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    // Wypełnij pole "Username"
    WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
    usernameField.sendKeys("jpetstore");

    // Wypełnij pole "Password"
    WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.sendKeys("jpetstore");

    // Kliknij przycisk "Login"
    WebElement loginButton = driver.findElement(By.name("signon"));
        loginButton.click();

    // Poczekaj na przekierowanie do strony głównej po udanym logowaniu
    WebElement accountLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Sign Out")));

    // Sprawdź, czy użytkownik został poprawnie zalogowany, szukając linku "Sign Out"
//       Assert.assertTrue(accountLink.isDisplayed(), "Użytkownik nie został zalogowany.");

    // Dodatkowe logowanie, aby zweryfikować, że pojawiła się strona po zalogowaniu
        System.out.println("Zalogowano pomyślnie, widoczny jest link 'Sign Out'.");
}
}
