package com.example.hrworld.test;

import com.example.hrworld.businessObject.ItemDetails;
import com.example.hrworld.businessObject.Product;
import com.example.hrworld.businessObject.ProductType;
import com.example.hrworld.pages.ItemDetailPage;
import com.example.hrworld.pages.ProductPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

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
        List<Product> products = productPage.fetchProducts(ProductType.FISH);


        // Wypisz szczegóły produktów i ich itemów
        products.forEach(product -> {
            System.out.println("Product ID: " + product.getId());
            System.out.println("Product Name: " + product.getName());
            System.out.println("Items:");

            product.getItems().forEach(item -> System.out.printf(
                    item.getName()
            ));
            System.out.println("---------------------------");
        });

        // Sprawdzenie, czy lista produktów nie jest pusta
        assertFalse(products.isEmpty(), "Lista produktów nie powinna być pusta");

        // Sprawdzenie, czy pierwszy produkt zawiera przynajmniej jeden item
//        assertFalse(products.get(0).getItems().isEmpty(), "Lista itemów dla pierwszego produktu nie powinna być pusta");
    }

    @Test
    public void testFetchProductsAndDetails() {
        // Navigate to the FISH category page
        driver.get("https://przyklady.javastart.pl/jpetstore/actions/Catalog.action?viewCategory=&categoryId=FISH");

        // Create an instance of ProductPage
        ProductPage productPage = new ProductPage(driver);

        // Fetch all products
        List<Product> products = productPage.fetchProducts(ProductType.FISH);

        // Ensure the product list is not empty
        assertFalse(products.isEmpty(), "The product list should not be empty");

        // Iterate through each product
        for (Product product : products) {
            System.out.println("Product Id: " + product.getName());
            System.out.println("Product Name: " + product.getId());

            // Navigate to the product's detail page
            ItemDetailPage itemDetailPage = productPage.openItemDetailPage(product.getName());

            // Fetch items for the product
            List<ItemDetails> items = itemDetailPage.fetchItemsForProduct();

            // Print details of each item
            for (ItemDetails item : items) {
                System.out.println("Item ID: " + item.getItemId());
                System.out.println("Product ID: " + item.getProductId());
                System.out.println("Description: " + item.getDescription());
                System.out.println("Price: " + item.getPrice());
                System.out.println("---------------------------");
            }

            // Navigate back to the product list page
            driver.navigate().back();
        }
    }
    @Test
    public void testFetchAllCategoriesAndProducts() {
        // Przejdź do strony głównej sklepu
        driver.get("https://przyklady.javastart.pl/jpetstore/actions/Catalog.action");

        // Utwórz instancję ProductPage
        ProductPage productPage = new ProductPage(driver);

        // Iteruj przez wszystkie typy produktów
        for (ProductType productType : ProductType.values()) {
            // Kliknij w link odpowiadający danej kategorii
            driver.findElement(By.cssSelector("#QuickLinks > a[href*='categoryId=" + productType.name() + "']")).click();
            System.out.println("Category ID: " + productType.name());

            // Pobierz wszystkie produkty w danej kategorii
            List<Product> products = productPage.fetchProducts(productType);

            // Upewnij się, że lista produktów nie jest pusta
            assertFalse(products.isEmpty(), "Lista produktów dla kategorii " + productType.name() + " nie powinna być pusta");

            // Iteruj przez każdy produkt w danej kategorii
            for (Product product : products) {
                System.out.println("Product Id: " + product.getId());
                System.out.println("Product Name: " + product.getName());

                // Przejdź do strony szczegółów produktu
                ItemDetailPage itemDetailPage = productPage.openItemDetailPage(product.getName());

                // Pobierz szczegóły itemów dla produktu
                List<ItemDetails> items = itemDetailPage.fetchItemsForProduct();

                // Wypisz szczegóły każdego itemu
                for (ItemDetails item : items) {
                    System.out.println("Item ID: " + item.getItemId());
                    System.out.println("Product ID: " + item.getProductId());
                    System.out.println("Description: " + item.getDescription());
                    System.out.println("Price: " + item.getPrice());
                    System.out.println("---------------------------");
                }

                // Powrót do poprzedniej strony (listy produktów w danej kategorii)
                driver.navigate().back();
            }

            // Powrót do strony głównej przed przejściem do następnej kategorii
            driver.navigate().back();
        }
    }


}
