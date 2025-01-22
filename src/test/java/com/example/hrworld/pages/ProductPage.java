package com.example.hrworld.pages;
import com.example.hrworld.businessObject.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;

public class ProductPage extends BasePage {

    // Konstruktor
    public ProductPage(WebDriver driver) {
        super(driver);
    }

    // Lokatory elementów produktów
    private By productRows = By.cssSelector("table tbody tr:not(:first-child)");
    public List<Product> fetchProducts() {
        List<Product> productList = new ArrayList<>();

        // Znajdź wszystkie wiersze produktów
        List<WebElement> rows = driver.findElements(productRows);

        for (WebElement row : rows) {
            // Pobierz ID produktu z pierwszej kolumny
            String productId = row.findElement(By.cssSelector("td:first-child a")).getText();

            // Pobierz nazwę produktu z drugiej kolumny
            String productName = row.findElement(By.cssSelector("td:nth-child(2)")).getText();

            // Dodaj nowy produkt do listy
            productList.add(new Product("FISH", productName, productId));
        }

        return productList;
    }

}
