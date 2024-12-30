package com.example.hrworld;

import org.junit.jupiter.api.Test;

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


        // Pobieramy imię pracownika na podstawie indeksu
        String firstName = employeePage.getEmployeeFirstNameByIndex(0);
        employeePage.enterName(firstName);
        employeePage.clickSearchButton();
        String firstNameFromRow = String.valueOf(employeePage.getFirstNameFromTable());
        System.out.println(firstNameFromRow);
        assertThat(firstName).isEqualTo(firstNameFromRow);
    }
}
