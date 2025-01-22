package com.example.hrworld.test;
import com.example.hrworld.businessObject.Product;
import com.example.hrworld.pages.ProductPage;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class ProductTest extends BaseTest {

    @Test
    public void testFetchProducts() {
        driver.get("https://przyklady.javastart.pl/jpetstore/actions/Catalog.action?viewCategory=&categoryId=FISH");
        ProductPage productPage = new ProductPage(driver);

        List<Product> products = productPage.fetchProducts();
        products.forEach(System.out::println); // Wyświetla produkty w konsoli

        // Opcjonalnie: Assercje
        System.out.println(products.size());    }
}
