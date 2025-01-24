package com.example.hrworld.pages;

import com.example.hrworld.businessObject.Product;
import com.example.hrworld.businessObject.ProductType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
    public List<Product> fetchProducts(ProductType type) {
        // Znajdź wszystkie wiersze produktów
        List<WebElement> rows = driver.findElements(productRows);

        // Mapuj wiersze na obiekty Product
        return rows.stream()
                .map(row -> {
                    // Pobierz ID produktu z pierwszej kolumny
                    String productId = row.findElement(By.cssSelector("td:first-child a")).getText();

                    // Pobierz nazwę produktu z drugiej kolumny
                    String productName = row.findElement(By.cssSelector("td:nth-child(2)")).getText();

                    // Twórz obiekt Product z pustą listą itemów
                    return new Product(type, productName, productId, List.of());
                })
                .collect(Collectors.toList());
    }
}
