package com.example.hrworld;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Button {
    private By searchButton = By.xpath("//div[contains(@class, 'oxd-table-filter-area')]//button[contains(@class, 'oxd-button--secondary')]");

    public void clickSearchButton() {
        try {
            // Poczekaj na dostępność przycisku
            WebDriver driver = new ChromeDriver();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(searchButton));
            button.click();
            System.out.println("Przycisk wyszukiwania został kliknięty.");
        } catch (NoSuchElementException | TimeoutException e) {
            System.out.println("Nie udało się znaleźć przycisku wyszukiwania: " + e.getMessage());
        }
    }
}
