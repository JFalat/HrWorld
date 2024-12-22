package com.example.hrworld;

import com.example.hrworld.BaseTest;
import com.example.hrworld.LoginPage;
import com.example.hrworld.MainPage;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class EmployeeTest extends BaseTest {

    @Test
    public void testFetchFirstNames() {
        // Open the login page
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        // Perform login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123");

        // Navigate to Admin -> System Users page
        MainPage mainPage = new MainPage(driver);
        mainPage.goToAdminPage();

        // Fetch first names from the Employee Page
        EmployeePage employeePage = new EmployeePage(driver);
        List<String> allEmployees = employeePage.getEmployeesList();
        System.out.println(allEmployees);
        List<String> firstName = employeePage.getEmployeesFirstName();
//        List<String> userName = employeePage.getEmployeesName();
        System.out.println(firstName);
//        System.out.println(userName);

    }
}
