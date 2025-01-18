package com.example.hrworld;

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

    // Konstruktor generowany automatycznie przez Lombok (dzięki @RequiredArgsConstructor)
    // WebDriver i WebDriverWait są przekazywane jako wymagane argumenty

    public void selectOptionByValue(String value, By locator) {
        WebElement dropdownElement = driver.findElement(locator);
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByValue(value);
    }

    public void selectOptionByVisibleText(String visibleText, By locator) {
        WebElement dropdownElement = driver.findElement(locator);
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByVisibleText(visibleText);
    }

    public void selectOptionByIndex(int index, By locator) {
        WebElement dropdownElement = driver.findElement(locator);
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByIndex(index);
    }

    public void handleCheckbox(By locator, boolean czyDodacCheckbox) {
        WebElement checkbox = driver.findElement(locator);
        boolean isChecked = checkbox.isSelected();

        if (czyDodacCheckbox && !isChecked) {
            // Jeśli chcemy zaznaczyć i checkbox nie jest zaznaczony
            checkbox.click();
        } else if (!czyDodacCheckbox && isChecked) {
            // Jeśli chcemy odznaczyć i checkbox jest zaznaczony
            checkbox.click();
        }
    }

    public void enterText(String text, By locator) {
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

    public void click(By locator) {
        driver.findElement(locator).click();
    }
}
