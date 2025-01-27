package com.example.hrworld.pages;

import com.example.hrworld.businessObject.Item;
import com.example.hrworld.businessObject.Product;
import com.example.hrworld.businessObject.ProductType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductPage extends BasePage {

    private final WebDriverWait wait;

    // Konstruktor
    public ProductPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Oczekiwanie do 10 sekund
    }

    // Lokator dla wierszy produktów w tabeli
    private By productRows = By.xpath("//*[@id='Catalog']/table/tbody/tr[position() > 1]");
    private By productLink = By.xpath(".//td[1]"); // Link w pierwszej kolumnie ID produktu
    private By itemRows = By.xpath("//*[@id='Catalog']/table/tbody/tr[position() > 1]");

    /**
     * Pobiera wszystkie produkty z widocznej strony dla danej kategorii.
     *
     * @param type Typ kategorii, np. FISH, DOGS, REPTILES, CATS, BIRDS
     * @return Lista obiektów Product
     */
    public List<Product> fetchProducts(ProductType type) {
        // Oczekuj na widoczność wszystkich wierszy produktów
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(productRows));
        List<WebElement> rows = driver.findElements(productRows);

        // Mapuj wiersze na obiekty Product
        return rows.stream()
                .map(row -> {
                    // Oczekiwanie na widoczność linku produktu
                    WebElement link = wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(row, productLink));
                    String productId = link.getText(); // ID produktu
                    String productName = row.findElement(By.xpath(".//td[2]")).getText(); // Nazwa produktu

                    // Kliknij link, aby przejść na stronę itemów
                    link.click();

                    // Pobierz listę itemów dla danego produktu
                    List<Item> items = fetchItemsForProduct(productId);

                    // Wracaj na poprzednią stronę
                    driver.navigate().back();

                    // Upewnij się, że strona kategorii jest załadowana
                    wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(productRows));

                    // Utwórz obiekt Product
                    return new Product(type, productName, productId, items);
                })
                .collect(Collectors.toList());
    }

    /**
     * Pobiera itemy dla danego produktu.
     *
     * @param productId ID produktu
     * @return Lista obiektów Item
     */
    private List<Item> fetchItemsForProduct(String productId) {
        // Oczekuj na widoczność wierszy itemów
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(itemRows));
        List<WebElement> rows = driver.findElements(itemRows);

        List<Item> items = new ArrayList<>();
        for (WebElement row : rows) {
            WebElement itemLink = wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(row, By.xpath(".//td[1]")));
            String itemId = itemLink.getText(); // ID itemu
            String description = row.findElement(By.xpath("//td[3]")).getText(); // Opis
            double price = Double.parseDouble(
                    row.findElement(By.xpath("//td[4]")).getText().replace("$", "").trim() // Cena
            );

            items.add(new Item(itemId, productId, description, price));
        }

        return items;
    }
}
