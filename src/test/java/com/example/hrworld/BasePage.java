package com.example.hrworld;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import java.time.Duration;
import java.util.Random;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void selectOptionByValue(String value,By locator) {
        WebElement dropdownElement = driver.findElement(locator);
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByValue(value);
    }

    public void selectOptionByVisibleText(By locator, String visibleText) {
        WebElement dropdownElement = driver.findElement(locator);
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByVisibleText(visibleText);
    }

    public void selectOptionByIndex(By locator, int index) {
        WebElement dropdownElement = driver.findElement(locator);
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByIndex(index);
    }
    public void checkCheckbox(By locator) {
        WebElement checkbox = driver.findElement(locator);
        if (!checkbox.isSelected()) {
            checkbox.click();  // Zaznacza checkbox
        }
    }
    // Metoda do odznaczenia checkboxa, jeśli jest zaznaczony
    public void uncheckCheckbox(By locator) {
        WebElement checkbox = driver.findElement(locator);
        if (checkbox.isSelected()) {
            checkbox.click();  // Odznacza checkbox
        }
    }
    // Metoda do przełączania stanu checkboxa (jeśli jest zaznaczony, odznacza; jeśli jest odznaczony, zaznacza)
    public void toggleCheckbox(By locator) {
        WebElement checkbox = driver.findElement(locator);
        checkbox.click();  // Przełącza stan checkboxa
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
