package com.example.hrworld;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private WebDriver driver;

    // Lokatory
    private By adminTab = By.xpath("//span[text()='Admin']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void goToAdminPage() {
        driver.findElement(adminTab).click();
    }
}
