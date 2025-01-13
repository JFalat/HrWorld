package com.example.hrworld;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;

public class TextField {

    public static void enterText(WebDriver driver, String text, By locator) {
        try {
            // Tworzymy WebDriverWait, aby poczekać na widoczność elementu
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Czekaj do 10 sekund
            WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(locator)); // Czekaj na widoczność elementu

            // Kliknij na element
            field.click();

            // Wyczyść pole przed wpisaniem tekstu (opcjonalnie)
            field.clear();

            // Wpisz tekst do pola
            field.sendKeys(text);

            System.out.println("Text entered: " + text + " into field located by: " + locator.toString());
        } catch (Exception e) {
            System.out.println("Failed to enter text. Error: " + e.getMessage());
        }
    }
    public static void enterAdminWithRandomNumber(WebDriver driver, By locator) {
        Random rand = new Random();
        int randomNumber = rand.nextInt(900) + 100;  // Generowanie liczby od 100 do 999
        String username = "admin" + randomNumber;  // Tworzenie ciągu "adminXXX"
        enterText(driver, username, locator);  // Wpisanie tekstu w pole
    }
}