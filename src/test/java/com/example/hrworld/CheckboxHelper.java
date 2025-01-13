package com.example.hrworld;
import org.openqa.selenium.*;

public class CheckboxHelper {

    // Metoda do zaznaczenia checkboxa, jeśli nie jest zaznaczony
    public static void checkCheckbox(WebDriver driver, By locator) {
        WebElement checkbox = driver.findElement(locator);
        if (!checkbox.isSelected()) {
            checkbox.click();  // Zaznacza checkbox
        }
    }

    // Metoda do odznaczenia checkboxa, jeśli jest zaznaczony
    public static void uncheckCheckbox(WebDriver driver, By locator) {
        WebElement checkbox = driver.findElement(locator);
        if (checkbox.isSelected()) {
            checkbox.click();  // Odznacza checkbox
        }
    }

    // Metoda do przełączania stanu checkboxa (jeśli jest zaznaczony, odznacza; jeśli jest odznaczony, zaznacza)
    public static void toggleCheckbox(WebDriver driver, By locator) {
        WebElement checkbox = driver.findElement(locator);
        checkbox.click();  // Przełącza stan checkboxa
    }
}
