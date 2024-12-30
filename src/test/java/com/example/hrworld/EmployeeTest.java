package com.example.hrworld;

import org.junit.jupiter.api.Test;

import static java.lang.Thread.sleep;
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
        employeePage.getFirstRow();  // Pobieramy pierwszy wiersz tabeli
//        employeePage.getUserRoles();  // Pobieramy role użytkowników

        // Pobieramy imię pracownika na podstawie indeksu
        String firstName = employeePage.getEmployeeFirstNameByIndex(0);
//        employeePage.enterFirstNameAndSelectSuggestion(firstName);  // Metoda wyszukiwania i wybierania sugestii
//        employeePage.searchInput.click();
//        employeePage.searchInput.sendKeys(firstName);
        employeePage.enterName(firstName);
        employeePage.clickSearchButton();
        String firstNameFromRow = String.valueOf(employeePage.getFirstNameFromTable());
        System.out.println(firstNameFromRow);
        assertThat(firstName).isEqualTo(firstNameFromRow);
        // Pauza na 5 sekund

        // Możesz dodać dalsze asercje, jeśli chcesz porównać wyniki lub zwrócić uwagę na inne elementy.
    }
}
