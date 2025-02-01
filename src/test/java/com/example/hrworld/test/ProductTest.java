package com.example.hrworld.test;

import com.example.hrworld.businessObject.Item;
import com.example.hrworld.businessObject.Product;
import com.example.hrworld.businessObject.ProductType;
import com.example.hrworld.pages.ItemPage;
import com.example.hrworld.pages.MainPage;
import com.example.hrworld.pages.ProductPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class ProductTest extends BaseTest {

    @Test
    public void testFetchAllCategoriesAndProducts() {
        // Przejdź do strony głównej sklepu
        driver.get("https://przyklady.javastart.pl/jpetstore/actions/Catalog.action");

        // Utwórz instancję MainPage i ProductPage
        MainPage mainPage = new MainPage(driver);
        ProductPage productPage = new ProductPage(driver);

        // Iteruj przez wszystkie typy produktów
        for (ProductType productType : ProductType.values()) {
            // Wybierz kategorię poprzez obrazek (mapa estoremap)
            MainPage.selectAnimalByImage(driver, productType.name());
            System.out.println("🛒 Wybrano kategorię: " + productType.name());

            // Pobierz wszystkie produkty w danej kategorii (razem z itemami)
            List<Product> products = productPage.fetchProducts(productType);

            // Upewnij się, że lista produktów nie jest pusta
            assertFalse(products.isEmpty(), "❌ Lista produktów dla kategorii " + productType.name() + " jest pusta!");

            // Iteruj przez każdy produkt w danej kategorii
            for (Product product : products) {
                System.out.println("🔹 Product Id: " + product.getId());
                System.out.println("🔹 Product Name: " + product.getName());

                // Upewnij się, że produkt ma co najmniej jeden item
                assertFalse(product.getItems().isEmpty(), "⚠️ Produkt " + product.getName() + " nie ma żadnych itemów!");

                // Wypisz szczegóły każdego itemu
                product.getItems().forEach(item -> {
                    System.out.println("   🔸 Item ID: " + item.getItemId());
                    System.out.println("   🔸 Product ID: " + item.getProductId());
                    System.out.println("   🔸 Description: " + item.getDescription());
                    System.out.println("   💰 Price: $" + item.getPrice());
                    System.out.println("   ---------------------------");
                });
            }

            // Powrót do strony głównej
            driver.navigate().back();
        }
    }
}
