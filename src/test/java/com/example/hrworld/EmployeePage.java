package com.example.hrworld;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Thread.sleep;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


public class EmployeePage {
    private WebDriver driver;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


    public By getUsername() {
        return Username;
    }

    public void setUsername(By username) {
        Username = username;
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

    // Zlokalizowanie elementu typu WebElement
    private By Username = By.xpath("//div[contains(@class, 'oxd-grid-item oxd-grid-item--gutters')]//input[contains(@class, 'oxd-input oxd-input--active')]");
    private By selectRole = By.xpath("//div[contains(@class, 'oxd-select-text oxd-select-text--active')]");
    private By employeeName = By.xpath("//*[contains(@class,'oxd-autocomplete-wrapper')]//input");
    private By status = By.xpath("(//*[contains(@class,'oxd-select-text oxd-select-text--active')])[2]");

    private By searchButton = By.xpath("//div[contains(@class, 'oxd-table-filter-area')]//button[contains(@class, 'oxd-button--secondary')]");
    private By WholeTable = By.xpath("//div[contains(@class, 'oxd-table') and @role='table'] ");
    private By OneRow = By.xpath("//div[contains(@class,'oxd-table-card')]");
    private By OneColumn = By.xpath(".//div[contains(@class,'oxd-table-cell oxd-padding-cell')]/*");
    private By iconLocator = By.cssSelector(".oxd-icon.bi-check.oxd-checkbox-input-icon");

    // Konstruktor
    public EmployeePage(WebDriver driver) {
        this.driver = driver;
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

    public void enterName(String firstName) {
        try {
            // Czekanie na widoczność pola tekstowego, aby upewnić się, że możemy kliknąć
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement searchInputElement = wait.until(ExpectedConditions.elementToBeClickable(Username));
            // Kliknięcie w pole tekstowe
            searchInputElement.click();
            // Wpisanie wartości w pole tekstowe
            searchInputElement.clear(); // Czyszczenie poprzednich danych
            searchInputElement.sendKeys(firstName); // Wpisanie przekazanego imienia
        } catch (NoSuchElementException | TimeoutException e) {
            System.out.println("Błąd podczas interakcji z polem wyszukiwania: " + e.getMessage());
        }
    }

    List<Employee> mojZiomek = new ArrayList<>();

    public void addEmployeeToList() {
        mojZiomek.add(new Employee("aa", "Admin", "bbb", "Enabled"));
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

    public void fillFormWithEmployeeData(Employee employee) {
        // Wypełnianie pola Username
        WebElement usernameField = driver.findElement(Username);
        usernameField.clear();
        usernameField.sendKeys(employee.getUsername());
        // Klikanie w pole Rola, aby rozwinąć wybór
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        // Kliknięcie w dropdown, aby otworzyć opcje
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@class='oxd-select-text-input']")));
        dropdown.click();  // Kliknij, aby rozwinąć listę
        // Czekanie na widoczność opcji z tekstem "Admin"
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@class, 'oxd-select-dropdown')]/div/span[contains(text(), 'Admin')]")
        ));
        // Kliknięcie w opcję "Admin"
        option.click();
        // Wypełnianie pola Employee Name
        WebElement employeeNameField = driver.findElement(employeeName);
        employeeNameField.click();
        employeeNameField.sendKeys("JIHAN");
        WebElement suggestionList = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class, 'oxd-autocomplete-dropdown --positon-bottom')]") // Zmienna XPath zależnie od struktury HTML
        ));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // Pobranie wszystkich bezpośrednich dzieci suggestionList
        List<WebElement> children = suggestionList.findElements(
                By.xpath("./*"));
        children.getFirst().click();

        // Wybieranie statusu
        WebElement Userrole = lokator(driver).get(1);
        Userrole.click();  // Kliknij, aby rozwinąć listę

//        WebElement option1 = wait.until(ExpectedConditions.elementToBeClickable(
//                By.xpath("//div[contains(@class, 'oxd-select-dropdown')]/div/span[contains(text(), 'Admin')]")

        // Wybieranie statusu z rozwijanej listy

        WebElement suggestionList1 = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class, 'oxd-select-text-input')]")
                // Zmienna XPath zależnie od struktury HTML
        ));
        System.out.println(suggestionList1);
        List<WebElement> children1 = suggestionList1.findElements(By.xpath("./li"));
        System.out.println(children1);
//
//        for (WebElement option1 : children1) {
//            System.out.println(option1.getText()); // Zwróci tekst wszystkich opcji w rozwijanej liście
//        }
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//
////// Kliknięcie na pierwszą opcję z listy
//       if (!children1.isEmpty()) {
//            children1.get(0).click(); // Kliknij pierwszą opcję
//        }
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//
//        WebElement statusOption = driver.findElement(By.xpath("//div[@class='oxd-select-text-input' and contains(text(), 'Enabled')]"));
//        statusOption.click();
    }
    public void selectStatusFromDropdown() {
        try {
            // Czekamy na pojawienie się rozwijanej listy
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Zlokalizowanie rozwijanej listy
            WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[@class='oxd-select-text-input']")));

            // Kliknij na rozwijane menu, aby pokazać dostępne opcje
            dropdown.click();

            // Teraz czekamy na dostępność elementu 'Enabled' w rozwijanej liście
            WebElement enabledOption = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[@class='oxd-select-dropdown']//span[contains(text(), 'Enabled')]")));
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            // Klikamy na opcję 'Enabled'
            enabledOption.click();

        } catch (Exception e) {
            System.out.println("Błąd podczas wybierania statusu: " + e.getMessage());
        }
    }

}



