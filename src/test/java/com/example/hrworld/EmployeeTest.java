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


        // Pobieramy imię pracownika na podstawie indeksu
        List<String> employeeData = employeePage.iterateTable();

        // Wypisz dane dla wizualizacji
        for (String data : employeeData) {
            System.out.println(data);
        }

        // Przykład asercji - sprawdzamy, czy lista nie jest pusta
        assertThat(employeeData).isNotEmpty(); // Sprawdza, czy lista zawiera jakieś dane
    }
//        employeePage.enterName(firstName);
//        employeePage.clickSearchButton();
//        String firstNameFromRow = String.valueOf(employeePage.getFirstNameFromTable());
//        System.out.println(firstNameFromRow);
//        assertThat(firstName).isEqualTo(firstNameFromRow);
    }

