package com.example.hrworld.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage extends BasePage{
    public MainPage(WebDriver driver) {
        super(driver);
    }
    public static void selectAnimalByImage(WebDriver driver, String animalName) {
        // Dynamiczny XPath na podstawie nazwy zwierzęcia
        String xpath = "//div[@id='QuickLinks']/a[contains(@href, 'categoryId=" + animalName + "')]";
        WebElement animal = driver.findElement(By.xpath(xpath));
        animal.click(); // Kliknięcie w wybrane zwierzę
    }
    public static void selectAnimalByLink(WebDriver driver, String categoryId) {
        // Dynamiczny XPath na podstawie categoryId
        String xpath = "//div[@id='QuickLinks']/a[contains(@href, 'categoryId=" + categoryId + "')]";
        WebElement animalLink = driver.findElement(By.xpath(xpath));
        animalLink.click(); // Kliknięcie w wybrane zwierzę
    }
}
