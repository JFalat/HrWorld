package com.example.hrworld;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SelectClass {

    // Przekazywanie WebDriver jako argument, aby metoda działała na już istniejącej instancji przeglądarki
    public static void selectValueFromDropdown(WebDriver driver, By dropdownLocator, String valueToSelect) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

            // Czekamy na dostępność rozwijanego menu
            WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(dropdownLocator));
            // Kliknij rozwijane menu
            dropdown.click();

            // Czekamy na widoczność opcji, która zawiera tekst 'valueToSelect'
            WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[contains(@class, 'oxd-select-dropdown --positon-bottom')]/div/span[contains(text(), '"+ valueToSelect +"')]")
            ));

            // Scrollowanie do opcji, aby upewnić się, że jest widoczna
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", option);

            // Kliknij opcję, która pasuje do tekstu 'valueToSelect'
            option.click();

        } catch (Exception e) {
            System.out.println("Błąd podczas wybierania wartości z rozwijanego menu: " + e.getMessage());
        }
    }

}
