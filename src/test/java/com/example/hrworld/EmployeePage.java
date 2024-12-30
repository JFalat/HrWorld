package com.example.hrworld;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeePage {
    private WebDriver driver;

    // Lokatory dla tabeli, wierszy oraz kolumn
    private By tableRowLocator = By.xpath("//div[@class='oxd-table']//div[@role='row']");
    private By employeeNameColumnLocator = By.xpath("//div[@class='oxd-table-card-cell']//div[contains(@class, 'data')]");
    private By roleColumnLocator = By.xpath("//div[@class='oxd-table-card-cell']//div[contains(@class, 'header') and text()='User Role']/following-sibling::div");
//    private By searchInputLocator = By.xpath("//input[contains(@class, 'oxd-input') and contains(@class, 'oxd-input--active')]");
// Zlokalizowanie elementu typu WebElement
    private By searchInput = By.xpath("//div[contains(@class, 'oxd-grid-item oxd-grid-item--gutters')]//input[contains(@class, 'oxd-input oxd-input--active')]");
//    private By searchInputLocator = By.xpath("//div[contains(@class, 'oxd-grid-item oxd-grid-item--gutters')]//input[@placeholder='Type for hints...']");
    private By firstNameCellLocator = By.xpath(".//div[contains(@class, 'oxd-table-body')]//div[2]/div");

    public String getFirstNameFromTable() {
        WebElement firstNameCell = driver.findElement(firstNameCellLocator);
        String firstName1 = firstNameCell.getText().trim();
        return firstName1;


    }

    private By firstDivLocator = By.xpath(".//div[contains(@class, 'oxd-table-cell oxd-padding-cell')]//div[1]");

    // Lokator dla listy sugestii (np. by zlokalizować wszystkie sugerowane opcje)
    private By suggestionsLocator = By.xpath("//div[contains(@class, 'oxd-autocomplete-dropdown') and contains(@class, '--position-bottom') and @role='listbox']");
    private By firstRow = By.xpath("(//div[@class='oxd-table']//div[@role='row'])[1]");
    private By inputElement = By.xpath("//input[@class='oxd-input oxd-input--active']");
    private By searchButton = By.xpath("//div[contains(@class, 'oxd-table-filter-area')]//button[contains(@class, 'oxd-button--secondary')]");

    // Konstruktor
    public EmployeePage(WebDriver driver) {
        this.driver = driver;
    }

    // Pobierz pierwszy wiersz tabeli
    public String getFirstRow() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement row = wait.until(ExpectedConditions.visibilityOfElementLocated(firstRow));
            return row.getText();
        } catch (NoSuchElementException e) {
            System.out.println("Element not found: " + e.getMessage());
            return "";
        }
    }

    // Pobierz listę imion pracowników
    public List<String> getEmployeesFirstName() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<WebElement> rows = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//div[contains(@class, 'oxd-table-row oxd-table-row--with-border')]")));

        List<String> employeeFirstNames = new ArrayList<>();
        for (WebElement row : rows) {
            try {
                WebElement nameCell = row.findElement(By.xpath(".//div[contains(@class,'oxd-table-cell')][2]"));
                String firstName = nameCell.getText().trim();
                if (!firstName.isEmpty()) {
                    employeeFirstNames.add(firstName);
                }
            } catch (NoSuchElementException e) {
                System.out.println("Nie znaleziono imienia w wierszu");
            }
        }
        System.out.println("Imiona pracowników: " + employeeFirstNames);
        return employeeFirstNames;
    }

    // Pobierz imię pracownika na podstawie indeksu
    public String getEmployeeFirstNameByIndex(int index) {
        List<String> employeeFirstNames = getEmployeesFirstName();
        if (index >= 0 && index < employeeFirstNames.size()) {
            String firstName = employeeFirstNames.get(index);
            System.out.println("Imię pracownika na indeksie " + index + ": " + firstName);
            return firstName;
        } else {
            System.out.println("Brak pracownika o podanym indeksie: " + index);
            return "Brak pracownika o podanym indeksie";
        }
    }

    // Pobierz role użytkowników
    public List<String> getUserRoles() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<WebElement> rows = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//div[contains(@class, 'oxd-table-row oxd-table-row--with-border')]")));

        List<String> userRoles = new ArrayList<>();
        for (WebElement row : rows) {
            try {
                WebElement roleCell = row.findElement(By.xpath(".//div[contains(@class,'oxd-table-cell')][3]"));
                String role = roleCell.getText().trim();
                if (!role.isEmpty()) {
                    userRoles.add(role);
                }
            } catch (NoSuchElementException e) {
                System.out.println("Nie znaleziono roli w wierszu");
            }
        }
        System.out.println(userRoles);
        return userRoles;
    }

    // Metoda wyszukiwania po imieniu i wybierania sugestii
//    public void enterFirstNameAndSelectSuggestion(String firstName) {
//        // Zlokalizowanie pola tekstowego
//        WebElement searchInput = driver.findElement(searchInputLocator);
//
//        // Wpisanie wartości w pole wyszukiwania
//        searchInput.clear();  // Czyszczenie poprzednich danych
//        searchInput.sendKeys(firstName);
//
//        // Oczekiwanie na pojawienie się sugestii
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//        wait.until(ExpectedConditions.visibilityOfElementLocated(suggestionsLocator));
//
//        // Zlokalizowanie wszystkich sugestii
//        List<WebElement> suggestions = driver.findElements(suggestionsLocator);
//
//        // Sprawdzenie, czy są dostępne sugestie
//        if (!suggestions.isEmpty()) {
//            // Kliknięcie na pierwszą sugestię
//            suggestions.get(0).click();
//        } else {
//            System.out.println("Brak sugestii do wyboru");
//        }
//    }
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
    }

