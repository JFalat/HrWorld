package com.example.hrworld;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


public class EmployeePage {
    private WebDriver driver;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


    // Zlokalizowanie elementu typu WebElement
    private By searchInput = By.xpath("//div[contains(@class, 'oxd-grid-item oxd-grid-item--gutters')]//input[contains(@class, 'oxd-input oxd-input--active')]");
    private By searchButton = By.xpath("//div[contains(@class, 'oxd-table-filter-area')]//button[contains(@class, 'oxd-button--secondary')]");
    private By WholeTable = By.xpath("//div[contains(@class, 'oxd-table') and @role='table'] ");
    private By OneRow = By.xpath("//div[contains(@class,'oxd-table-card')]");
    private By OneColumn = By.xpath(".//div[contains(@class,'oxd-table-cell oxd-padding-cell')]/*");
    private By iconLocator = By.cssSelector(".oxd-icon.bi-check.oxd-checkbox-input-icon");

    // Konstruktor
    public EmployeePage(WebDriver driver) {
        this.driver = driver;
    }

    public List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<>();
        // Znajdź całą tabelę
        WebElement table = driver.findElement(WholeTable);
        // Znajdź wszystkie wiersze w tabeli
        List<WebElement> rows = table.findElements(OneRow);
        // Iteruj po wierszach
        for (WebElement row : rows) {
            // Znajdź wszystkie kolumny w danym wierszu
            List<WebElement> columns = row.findElements(OneColumn);
            if (columns.size() >= 4) {
                String username = columns.get(1).getText().trim();
                String userRole = columns.get(2).getText().trim();
                String employeeName = columns.get(3).getText().trim();
                String status = columns.get(4).getText().trim();
                // Tworzenie nowego obiektu Employee i dodawanie do listy
                employees.add(new Employee(username, userRole, employeeName, status));
            }
        }
        return employees; // Zwróć listę obiektów Employee
    }

    List<Employee>getEmployeesStream(){
        WebElement table = driver.findElement(WholeTable);
        List<WebElement> rows = table.findElements(OneRow);
        return rows.stream()
                .map(row->{
                    List<WebElement> columns = row.findElements(OneColumn);
                    if (columns.size() >= 4) {
                        String username = columns.get(1).getText().trim();
                        String userRole = columns.get(2).getText().trim();
                        String employeeName = columns.get(3).getText().trim();
                        String status = columns.get(4).getText().trim();
                        // Tworzenie nowego obiektu Employee
                        return new Employee(username, userRole, employeeName, status);
                    }
                    return null; // W przypadku nieprawidłowego wiersza
                })
                .filter(employee -> employee != null) // Filtrujemy null-owe wartości
                .collect(Collectors.toList()); // Zbieramy do listy
    }

    public void enterName(String firstName) {
        try {
            // Czekanie na widoczność pola tekstowego, aby upewnić się, że możemy kliknąć
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement searchInputElement = wait.until(ExpectedConditions.elementToBeClickable(searchInput));
            // Kliknięcie w pole tekstowe
            searchInputElement.click();
            // Wpisanie wartości w pole tekstowe
            searchInputElement.clear(); // Czyszczenie poprzednich danych
            searchInputElement.sendKeys(firstName); // Wpisanie przekazanego imienia
        } catch (NoSuchElementException | TimeoutException e) {
            System.out.println("Błąd podczas interakcji z polem wyszukiwania: " + e.getMessage());
        }
    }


    // Metoda do kliknięcia w przycisk wyszukiwania
    public void clickSearchButton() {
        try {
            // Poczekaj na dostępność przycisku
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(searchButton));
            button.click();
            System.out.println("Przycisk wyszukiwania został kliknięty.");
        } catch (NoSuchElementException | TimeoutException e) {
            System.out.println("Nie udało się znaleźć przycisku wyszukiwania: " + e.getMessage());
        }
    }
    public List<WebElement> getIcons() {
        return driver.findElements(iconLocator);
    }
}


