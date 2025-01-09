package com.example.hrworld;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeePage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Zlokalizowanie elementu typu WebElement
    By usernameLocator = By.xpath("//input[contains(@class, 'oxd-input oxd-input--active')]");
    private By selectRole = By.xpath("//div[contains(@class, 'oxd-select-text oxd-select-text--active')]");
    private By employeeName = By.xpath("//*[contains(@class,'oxd-autocomplete-wrapper')]//input");
    private By status = By.xpath("(//*[contains(@class,'oxd-select-text oxd-select-text--active')])[2]");

    private By WholeTable = By.xpath("//div[contains(@class, 'oxd-table') and @role='table']");
    private By OneRow = By.xpath("//div[contains(@class,'oxd-table-card')]");
    private By OneColumn = By.xpath(".//div[contains(@class,'oxd-table-cell oxd-padding-cell')]/*");
    private By iconLocator = By.cssSelector(".oxd-icon.bi-check.oxd-checkbox-input-icon");

    // Konstruktor
    public EmployeePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Inicjalizacja wait po przypisaniu drivera
    }

    public By getUsername() {
        return usernameLocator;
    }

    public void setUsername(By username) {
        usernameLocator = username;
    }

    public By getSelectRole() {
        return selectRole;
    }

    public void setSelectRole(By selectRole) {
        this.selectRole = selectRole;
    }

    public By getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(By employeeName) {
        this.employeeName = employeeName;
    }

    public By getStatus() {
        return status;
    }

    public void setStatus(By status) {
        this.status = status;
    }

    // Pobieranie wszystkich elementów pasujących do XPath
    public List<WebElement> lokator(WebDriver driver) {
        List<WebElement> elements = driver.findElements(By.xpath("//div[@class='oxd-select-text-input']"));

        // Sprawdzenie, czy lista ma co najmniej dwa elementy
        if (elements.size() >= 2) {
            // Przypisanie pierwszego i drugiego elementu do WebElementów
            WebElement Userrole = elements.get(0); // Pierwszy element (indeks 0)
            WebElement Status = elements.get(1); // Drugi element (indeks 1)

            // Możesz teraz używać WE1 i WE2 do dalszych działań, np. kliknięć, sprawdzania tekstu itp.
        } else {
            System.out.println("Mniej niż dwa elementy pasujące do XPath!");
        }
        return elements;
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
                String usernameLocator = columns.get(1).getText().trim();
                String userRole = columns.get(2).getText().trim();
                String employeeName = columns.get(3).getText().trim();
                String status = columns.get(4).getText().trim();
                // Tworzenie nowego obiektu Employee i dodawanie do listy
                employees.add(new Employee(usernameLocator, userRole, employeeName, status));
            }
        }
        return employees; // Zwróć listę obiektów Employee
    }

    // Zmiana na strumień
    List<Employee> getEmployeesStream() {
        WebElement table = driver.findElement(WholeTable);
        List<WebElement> rows = table.findElements(OneRow);
        return rows.stream()
                .map(row -> {
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

    List<Employee> mojZiomek = new ArrayList<>();

    public void addEmployeeToList() {
        mojZiomek.add(new Employee("aa", "Admin", "bbb", "Enabled"));
    }

    // Metoda do kliknięcia w przycisk wyszukiwania
    public List<WebElement> getIcons() {
        return driver.findElements(iconLocator);
    }

    // Metoda do wypełnienia formularza danymi pracownika
    public void fillFormWithEmployeeData(Employee employee) {
        // Wypełnianie pola Username
        try {
            selectElementFromListOfTheSameElement(usernameLocator, 0);  // Wybierze drugi element
            TextField.enterText(driver, "Honorata", usernameLocator);  // `Username` to locator dla pola w formularzu
        } catch (NoSuchElementException | TimeoutException e) {
            System.out.println("Błąd przy wypełnianiu formularza: " + e.getMessage());
        }

        // Klikanie w pole Rola, aby rozwinąć wybór
        SelectClass.selectValueFromDropdown(driver, selectRole, "Admin");
    }

    // Metoda do wyboru elementu z listy
    public String selectElementFromListOfTheSameElement(By lokator, int index) {
        WebElement suggestionList = wait.until(ExpectedConditions.visibilityOfElementLocated(lokator));

        try {
            Thread.sleep(5000); // Czekamy na załadowanie się elementów (lepszym rozwiązaniem byłoby użycie WebDriverWait)
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Pobranie wszystkich bezpośrednich dzieci suggestionList
        List<WebElement> children = suggestionList.findElements(By.xpath("./*"));

        // Zmiana na wyszukiwanie po indeksie
        if (index >= 0 && index < children.size()) {
            children.get(index).click(); // Kliknięcie na element o danym indeksie
            return "Element clicked";
        } else {
            return "Invalid index"; // Zwrócenie komunikatu, jeśli indeks jest poza zakresem
        }
    }
}
