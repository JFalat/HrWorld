package com.example.hrworld.test;

import com.example.hrworld.businessObject.Product;
import com.example.hrworld.businessObject.ProductType;
import com.example.hrworld.pages.ProductPage;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class ProductTest extends BaseTest {

    @Test
    public void testFetchProducts() {
        ProductPage productPage = new ProductPage(driver);

        // Test dla kategorii FISH
        verifyProductsForCategory(productPage, ProductType.FISH);

        // Test dla kategorii DOGS
        verifyProductsForCategory(productPage, ProductType.DOGS);

        // Możesz dodać kolejne kategorie
        verifyProductsForCategory(productPage, ProductType.CATS);
        verifyProductsForCategory(productPage, ProductType.REPTILES);
        verifyProductsForCategory(productPage, ProductType.BIRDS);
    }

    /**
     * Metoda pomocnicza do testowania produktów dla danej kategorii.
     *
     * @param productPage Obiekt strony produktów
     * @param productType Typ produktu (FISH, DOGS, itp.)
     */
    private void verifyProductsForCategory(ProductPage productPage, ProductType productType) {
        String url = "https://przyklady.javastart.pl/jpetstore/actions/Catalog.action?viewCategory=&categoryId=" + productType.name();
        driver.get(url); // Przejdź na stronę odpowiedniej kategorii

        List<Product> products = productPage.fetchProducts(productType);
        products.forEach(System.out::println); // Wyświetl produkty w konsoli

        assertFalse(products.isEmpty(), "Lista produktów dla " + productType + " nie powinna być pusta");
        System.out.println("Liczba produktów dla " + productType + ": " + products.size());
    }
}
