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

        // Pobierz wszystkie produkty wraz z ich itemami
        List<Product> products = productPage.fetchAllItemsForAllProducts(ProductType.FISH);


        // Wypisz szczegóły produktów i ich itemów
        products.forEach(product -> {
            System.out.println("Product ID: " + product.getId());
            System.out.println("Product Name: " + product.getName());
            System.out.println("Items:");

            product.getItems().forEach(item -> System.out.printf(
                    "  Item ID: %s, Product ID: %s, Description: %s, Price: %.2f%n",
                    item.getItemId(), product.getId(), item.getDescription(), item.getPrice()
            )
            );

            System.out.println("---------------------------");
        });

        // Sprawdzenie, czy lista produktów nie jest pusta
        assertFalse(products.isEmpty(), "Lista produktów nie powinna być pusta");

        // Sprawdzenie, czy pierwszy produkt zawiera przynajmniej jeden item
        assertFalse(products.get(0).getItems().isEmpty(), "Lista itemów dla pierwszego produktu nie powinna być pusta");
    }
}
