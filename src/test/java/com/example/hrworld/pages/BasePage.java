package com.example.hrworld.pages;

import lombok.RequiredArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

@RequiredArgsConstructor
public class BasePage {
    protected final WebDriver driver;
    protected final WebDriverWait wait;

    // Konstruktor z domyślnym czasem oczekiwania
    public BasePage(WebDriver driver) {
        this(driver, Duration.ofSeconds(10)); // Domyślny czas 10 sekund
    }

    // Konstruktor z niestandardowym czasem oczekiwania
    public BasePage(WebDriver driver, Duration waitTime) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, waitTime);
    }
    // Metoda pomocnicza do oczekiwania na widoczność elementu
    protected WebElement waitForElement(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void selectOptionByValue(String value, By locator) {
        WebElement dropdownElement = waitForElement(locator);
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByValue(value);
    }

    public void selectOptionByVisibleText(String visibleText, By locator) {
        WebElement dropdownElement = waitForElement(locator);
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByVisibleText(visibleText);
    }

    public void selectOptionByIndex(int index, By locator) {
        WebElement dropdownElement = waitForElement(locator);
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByIndex(index);
    }

    public void handleCheckbox(By locator, boolean checkCheckbox) {
        WebElement checkbox = waitForElement(locator);
        boolean isChecked = checkbox.isSelected();

        if (checkCheckbox && !isChecked) {
            checkbox.click();
        } else if (!checkCheckbox && isChecked) {
            checkbox.click();
        }
    }

    public void enterText(String text, By locator) {
        try {
            WebElement field = waitForElement(locator);

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

    public void click(By locator) {
        try {
            WebElement element = waitForElement(locator);
            element.click();
        } catch (Exception e) {
            System.out.println("Failed to click element. Error: " + e.getMessage());
        }
    }
}
