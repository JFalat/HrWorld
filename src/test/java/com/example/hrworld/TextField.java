package com.example.hrworld;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TextField {
    public static void enterText(WebDriver driver, String text, By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Czekanie na widoczność pola tekstowego, aby upewnić się, że możemy kliknąć
            WebElement searchInputElement = wait.until(ExpectedConditions.elementToBeClickable(locator));
            // Kliknięcie w pole tekstowe
            searchInputElement.click();
            // Wpisanie wartości w pole tekstowe
            searchInputElement.clear(); // Czyszczenie poprzednich danych
            searchInputElement.sendKeys(text); // Wpisanie przekazanego imienia
        } catch (NoSuchElementException | TimeoutException e) {
            System.out.println("Błąd podczas interakcji z polem wyszukiwania: " + e.getMessage());
        }
    }

}
