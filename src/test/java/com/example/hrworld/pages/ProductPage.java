package com.example.hrworld.pages;

import com.example.hrworld.businessObject.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductPage extends BasePage {

    // Konstruktor
    public ProductPage(WebDriver driver) {
        super(driver);
    }

    // Lokator dla wierszy produktów w tabeli
    private By productRows = By.cssSelector("table tbody tr:not(:first-child)");

    /**
     * Metoda pobiera produkty z bieżącej strony dla danej kategorii.
     *
     * @param type Typ kategorii, np. FISH, DOGS, REPTILES, CATS, BIRDS
     * @return Lista obiektów Product
     */
    public List<Product> fetchProducts(String type) {
        List<Product> productList = new ArrayList<>();

        // Znajdź wszystkie wiersze produktów
        List<WebElement> rows = driver.findElements(productRows);

        return rows.stream()
                .map(row -> {
                    String productId = row.findElement(By.cssSelector("td:first-child a")).getText();
                    String productName = row.findElement(By.cssSelector("td:nth-child(2)")).getText();
                return new Product(type,productId,productName);
                })
                .collect(Collectors.toList());
    }
    }

