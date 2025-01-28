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
        // Oczekuj na obecność wierszy tabeli z produktami
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(productRows));
        List<WebElement> rows = driver.findElements(productRows);

        // Mapuj wiersze na obiekty Product
        return rows.stream()
                .map(row -> {
                    try {
                        // Oczekuj na obecność linku w pierwszej kolumnie
                        WebElement link = wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(row, productLink));
                        String productId = link.getText().trim();
                        String productName = row.findElement(By.xpath(".//td[2]")).getText().trim();

                        // Użyj openItemPage, aby przejść do strony itemu
                        ItemPage itemPage = this.openItemPage(productName);

                        // Użyj metody fetchItemsForProduct w ItemPage
                        List<Item> items = itemPage.fetchItemsForProduct();

                        // Wróć na stronę produktów
                        driver.navigate().back();

                        // Poczekaj na ponowne załadowanie tabeli produktów
                        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(productRows));

                        // Utwórz obiekt Product
                        return new Product(type, productName, productId, items);
                    } catch (Exception e) {
                        System.err.println("Błąd podczas przetwarzania produktu: " + e.getMessage());
                        return null;
                    }
                })
                .filter(product -> product != null) // Usuń null
                .collect(Collectors.toList());
    }
}
