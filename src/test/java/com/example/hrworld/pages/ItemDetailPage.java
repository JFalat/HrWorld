package com.example.hrworld.pages;

import com.example.hrworld.businessObject.ItemDetails;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

public class ItemDetailPage extends BasePage {

    private final WebDriverWait wait;

    private By itemRows = By.xpath("//*[@id=\"Catalog\"]//table"); // Przykładowa struktura tabeli itemów

    public ItemDetailPage(WebDriver driver, WebDriverWait wait) {
        super(driver);
        this.wait = wait;
    }

    /**
     * Pobiera listę itemów ze strony.
     *
     * @return Lista obiektów Item.
     */
    public List<ItemDetails> fetchItemsForProduct() {
        // Oczekuj na obecność wierszy tabeli itemów
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(itemRows));
        List<WebElement> rows = driver.findElements(itemRows);

        // Mapuj wiersze na obiekty Item
        return rows.stream()
                .map(row -> {
                    // Pobierz dane z kolumn w wierszu
                    String itemId = row.findElement(By.xpath(".//td[1]")).getText().trim();
                    String productId = row.findElement(By.xpath(".//td[2]")).getText().trim();
                    String description = row.findElement(By.xpath(".//td[3]")).getText().trim();
                    // Pobranie ceny i usunięcie znaku "$"
                    String priceText = row.findElement(By.xpath(".//td[4]")).getText().trim();
                    priceText = priceText.replace("$", "").trim(); // Usunięcie znaku dolara
                    double price = Double.parseDouble(priceText);

                    // Twórz obiekt Item
                    return new ItemDetails(itemId, productId, description, price);
                })
                .collect(Collectors.toList());
    }

}
