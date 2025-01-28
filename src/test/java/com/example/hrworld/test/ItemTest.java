package com.example.hrworld.test;

import com.example.hrworld.businessObject.Item;
import com.example.hrworld.pages.ItemPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ItemTest extends BaseTest {

    @Test
    public void testFetchItems() {
        // Przejdź do strony konkretnego produktu (np. FI-SW-01)
        driver.get("https://przyklady.javastart.pl/jpetstore/actions/Catalog.action?viewProduct=&productId=FI-SW-01");

        // Utwórz obiekt WebDriverWait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Utwórz obiekt strony ItemPage
        ItemPage itemPage = new ItemPage(driver, wait);

        // Pobierz wszystkie itemy dla tego produktu
        List<Item> items = itemPage.fetchItemsForProduct();

        // Sprawdź, czy lista itemów nie jest pusta
        assertFalse(items.isEmpty(), "Lista itemów jest pusta!");

        // Wypisz szczegóły każdego itemu
        for (Item item : items) {
            System.out.println("Item ID: " + item.getItemId());
            System.out.println("Product ID: " + item.getProductId());
            System.out.println("Description: " + item.getDescription());
            System.out.println("Price: " + item.getPrice());

            // Dodatkowe asercje dla weryfikacji
            assertNotNull(item.getItemId(), "Item ID jest nullem!");
            assertNotNull(item.getDescription(), "Opis jest nullem!");
            assertNotNull(item.getProductId(), "Product ID jest nullem!");
            assertFalse(item.getPrice() <= 0, "Cena jest niepoprawna!");
        }
    }
}
