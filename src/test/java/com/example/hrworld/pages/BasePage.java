package com.example.hrworld.pages;

import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

@RequiredArgsConstructor // Lombok generuje konstruktor przyjmujący 'driver'
public class BasePage {
    protected final WebDriver driver;  // Finalne pole, które będzie przekazane do konstruktora

    // Metoda pomocnicza do oczekiwania na widoczność elementu
    protected WebElement waitForElement(By locator, Integer... waitTimeInSeconds) {
        int waitTime = (waitTimeInSeconds.length > 0) ? waitTimeInSeconds[0] : 10; // Domyślnie 10 sekund
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Kliknięcie elementu
    public void click(By locator) {
        waitForElement(locator).click();
    }

    // Wpisanie tekstu w pole
    public void enterText(String text, By locator) {
        WebElement element = waitForElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    // Wybór opcji z rozwijanej listy przez wartość
    public void selectOptionByValue(String value, By locator) {
        WebElement dropdownElement = waitForElement(locator);
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByValue(value);
    }

    // Wybór opcji z rozwijanej listy przez widoczny tekst
    public void selectOptionByVisibleText(String visibleText, By locator) {
        WebElement dropdownElement = waitForElement(locator);
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByVisibleText(visibleText);
    }

    // Wybór opcji z rozwijanej listy przez indeks
    public void selectOptionByIndex(int index, By locator) {
        WebElement dropdownElement = waitForElement(locator);
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByIndex(index);
    }

    // Zaznaczanie lub odznaczanie checkboxa
    public void handleCheckbox(By locator, boolean checkCheckbox) {
        WebElement checkbox = waitForElement(locator);
        boolean isChecked = checkbox.isSelected();

        if (checkCheckbox && !isChecked) {
            checkbox.click(); // Zaznacz checkbox, jeśli nie jest zaznaczony
        } else if (!checkCheckbox && isChecked) {
            checkbox.click(); // Odznacz checkbox, jeśli jest zaznaczony
        }
    }

    // Metoda do uzyskania tekstu z elementu
    public String getElementText(By locator) {
        return waitForElement(locator).getText();
    }

    // Metoda do uzyskania atrybutu z elementu (np. "value", "href" itd.)
    public String getElementAttribute(By locator, String attribute) {
        return waitForElement(locator).getAttribute(attribute);
    }
}
