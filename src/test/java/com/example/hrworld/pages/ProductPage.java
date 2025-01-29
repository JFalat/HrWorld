package com.example.hrworld.pages;

import com.example.hrworld.businessObject.Item;
import com.example.hrworld.businessObject.Product;
import com.example.hrworld.businessObject.ProductType;
import lombok.RequiredArgsConstructor;
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

    public ProductPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    private By productRows = By.xpath("//*[@id='Catalog']/table/tbody/tr[position() > 1]");
    private By productLink = By.xpath(".//td[1]");

    /**
     * Otwiera stronę itemu na podstawie nazwy produktu i zwraca obiekt ItemPage.
     *
     * @param itemName Nazwa itemu, którego stronę chcesz otworzyć.
     * @return Instancja klasy ItemPage.
     */
    public ItemPage openItemPage(String itemName) {
        WebElement itemLink = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//a[text()='" + itemName + "']")
        ));

        // Kliknij link
        itemLink.click();

        // Zwróć instancję ItemPage
        return new ItemPage(driver, wait);
    }

    /**
     * Pobiera listę produktów z tabeli i zwraca je jako listę obiektów Product.
     *
     * @param type Typ produktu (ProductType).
     * @return Lista obiektów Product.
     */
    public List<Product> fetchProducts(ProductType type) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        List<WebElement> productElements = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("your-product-selector"))
        );

        List<Product> products = new ArrayList<>();
        for (WebElement element : productElements) {
            String productName = element.findElement(By.xpath(".//td[2]")).getText().trim();

            // Musisz znaleźć sposób na uzyskanie productId, np. przez inny atrybut
            String productId = element.findElement(By.xpath(".//td[1]")).getText().trim();

            Product product = new Product(productId, productName);
            List<Item> items = fetchItemsForProduct(product);

            if (items == null) {
                System.out.println("Warning: No items found for product " + productName);
                items = new ArrayList<>(); // Ustaw pustą listę zamiast przerywać pętlę
            }

            product.setItems(items);
            products.add(product);
        }
        return products;
    }

}
