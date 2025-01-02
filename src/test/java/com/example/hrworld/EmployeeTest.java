package com.example.hrworld;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class EmployeeTest extends BaseTest {

    @Test
    public void testFetchFirstNames() {
        // Sprawdzamy, czy driver jest poprawnie zainicjalizowany
        assertThat(driver).isNotNull();

        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        // Wykonujemy logowanie
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123");

        // Nawigujemy do strony Admin -> System Users
        MainPage mainPage = new MainPage(driver);
        mainPage.goToAdminPage();

        // Pobieramy imiona pracowników z Employee Page
        EmployeePage employeePage = new EmployeePage(driver);
        List<Employee> employeeList = employeePage.getEmployees();

        // Wypisanie danych o pracownikach
        for (Employee employee : employeeList) {
            System.out.println(employee);  // Wypisuje obiekt Employee
        }

        // Przykład asercji - sprawdzamy, czy lista pracowników nie jest pusta
        assertThat(employeeList).isNotEmpty();

        // Pobieramy pierwsze username (first name) z listy pracowników
        String firstName = employeeList.get(0).getUsername();

        // Wysyłamy imię do pola wyszukiwania
        employeePage.enterName(firstName);

        // Klikamy przycisk wyszukiwania
        employeePage.clickSearchButton();

        // Dodatkowa asercja, sprawdzenie, czy pierwsze imię z listy zgadza się z danymi na stronie
        // (w zależności od aplikacji, możesz dodać metodę do pobrania wartości z tabeli)
//         String firstNameFromRow = String.valueOf(employeePage.getFirstNameFromTable());
//         assertThat(firstName).isEqualTo(firstNameFromRow);
    }
}