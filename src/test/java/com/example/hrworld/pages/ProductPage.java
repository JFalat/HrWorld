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

public class ProductPage extends BasePage {

    private final WebDriverWait wait;

    public ProductPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    private By productRows = By.xpath("//*[@id='Catalog']/table/tbody/tr[position() > 1]");
    private By productIdColumn = By.xpath(".//td[1]");
    private By productNameColumn = By.xpath(".//td[2]");


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
//        itemLink.click();

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
                ExpectedConditions.presenceOfAllElementsLocatedBy(productRows)
        );

        List<Product> products = new ArrayList<>();

        for (WebElement element : productElements) {
            // Pobranie Product ID (link)
            WebElement productLink = element.findElement(productIdColumn);
            String productId = productLink.getText();

            // Pobranie nazwy produktu
            String productName = element.findElement(productNameColumn).getText().trim();

            // Otwórz stronę szczegółów produktu i pobierz listę itemów
            ItemPage itemPage = openItemDetailPage(productId);
            List<Item> items = itemPage.fetchItemsForProduct();  // Pobranie itemów

            // Tworzenie obiektu Product z pobranymi itemami
            Product product = new Product(type, productId, productName, items);
            products.add(product);

            // Powrót do listy produktów
            driver.navigate().back();
        }
        return products;
    }

    /**
     * Pobiera wszystkie itemy dla wszystkich produktów danego typu.
     *
     * @param type Typ produktu (ProductType).
     * @return Lista produktów z przypisanymi itemami.
     */

    public ItemPage openItemDetailPage(String productId) {
        // Znajdź link do produktu na podstawie jego identyfikatora
        WebElement productLink = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//a[contains(@href, 'productId=" + productId + "')]")
        ));

        // Kliknij link, aby przejść do strony szczegółów produktu
        productLink.click();

        // Zwróć nową instancję ItemDetailPage
        return new ItemPage(driver, wait);
    }

}
