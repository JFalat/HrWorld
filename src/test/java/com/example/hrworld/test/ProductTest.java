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

        // Pobierz produkty dla kategorii FISH
        driver.get("https://przyklady.javastart.pl/jpetstore/actions/Catalog.action?viewCategory=&categoryId=FISH");
        List<Product> fishProducts = productPage.fetchProducts(ProductType.FISH);
        fishProducts.forEach(System.out::println); // Wyświetla produkty FISH w konsoli
        assertFalse(fishProducts.isEmpty());

        // Pobierz produkty dla kategorii DOGS
        driver.get("https://przyklady.javastart.pl/jpetstore/actions/Catalog.action?viewCategory=&categoryId=DOGS");
        List<Product> dogProducts = productPage.fetchProducts(ProductType.DOGS);
        dogProducts.forEach(System.out::println); // Wyświetla produkty DOGS w konsoli
        assertFalse(dogProducts.isEmpty());

        System.out.println("Liczba produktów FISH: " + fishProducts.size());
        System.out.println("Liczba produktów DOGS: " + dogProducts.size());
    }
}
