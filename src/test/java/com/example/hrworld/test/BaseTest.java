package com.example.hrworld.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait; // Globalny WebDriverWait

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);

        // Konfiguracja implicit wait (dla wyszukiwania elementów)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Inicjalizacja globalnego WebDriverWait z czasem oczekiwania
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Maksymalizacja okna
        driver.manage().window().maximize();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
