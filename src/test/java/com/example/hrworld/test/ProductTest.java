package com.example.hrworld.test;

import com.example.hrworld.businessObject.Item;
import com.example.hrworld.businessObject.Product;
import com.example.hrworld.businessObject.ProductType;
import com.example.hrworld.pages.ProductPage;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class ProductTest extends BaseTest {

    @Test
    public void testFetchProducts() {
        // Przejdź do strony z kategorią FISH
        driver.get("https://przyklady.javastart.pl/jpetstore/actions/Catalog.action?viewCategory=&categoryId=FISH");

        // Utwórz obiekt strony ProductPage
        ProductPage productPage = new ProductPage(driver);

        // Pobierz wszystkie produkty dla kategorii FISH
        List<Product> products = productPage.fetchProducts(ProductType.FISH);

        // Wypisz szczegóły produktów i ich itemów
        for (Product product : products) {
            System.out.println("Product ID: " + product.getId());
            System.out.println("Product Name: " + product.getName());
            System.out.println("Items:");
            for (Item item : product.getItems()) {
                System.out.println("  Item ID: " + item.getItemId());
                System.out.println("  Description: " + item.getDescription());
                System.out.println("  Price: " + item.getPrice());
            }
            System.out.println("---------------------------");
        }
    }
}