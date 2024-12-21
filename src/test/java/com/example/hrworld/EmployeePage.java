package com.example.hrworld;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class EmployeePage {
    private WebDriver driver;

    // Locator for table rows
    private By tableRowLocator = By.xpath("//div[@class='oxd-table']//div[@role='row']");


    public EmployeePage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Get first names from the usernames column.
     * Assumes the format of usernames is "FirstName LastName".
     *
     * @return List of first names
     */
    public List<String> getEmployeesList() {
        // Pobranie elementów tabeli
        List<WebElement> employeesElements = driver.findElements(tableRowLocator);
        List<String> employeesList = employeesElements.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        // Wypisanie listy w konsoli
        System.out.println(employeesList);
        // Zwrócenie listy
        return employeesList;
    }
    public List<String> getEmployeesFirstName() {

        List<WebElement> employeesFirstName = driver.findElements(By.xpath("(//div[@class='oxd-table']//div[@role='row'])[1]"));
        List<String> employeesList = employeesFirstName.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        // Wypisanie listy w konsoli
        System.out.println(employeesList);
        // Zwrócenie listy
        return employeesList;
    }
    public List<String> getUserRole() {

        List<WebElement> UserRole = driver.findElements(By.xpath("(//div[@class='oxd-table']//div[@role='row'])[2]"));
        List<String> employeesList2 = UserRole.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        // Wypisanie listy w konsoli
        System.out.println(UserRole);
        // Zwrócenie listy
        return employeesList2;
    }
    public List<String> getEmployeesName() {

        List<WebElement> EmployeesName = driver.findElements(By.xpath("(//div[@class='oxd-table']//div[@role='row'])[3]"));
        List<String> employeesList3 = EmployeesName.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        // Wypisanie listy w konsoli
        System.out.println(EmployeesName);
        // Zwrócenie listy
        return employeesList3;
    }
    public List<String> getStatus() {

        List<WebElement> EmployeeStatus = driver.findElements(By.xpath("(//div[@class='oxd-table']//div[@role='row'])[4]"));
        List<String> employeesList4 = EmployeeStatus.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        // Wypisanie listy w konsoli
        System.out.println(employeesList4);
        // Zwrócenie listy
        return employeesList4;
    }

//    List<WebElement> EmployeeName = driver.findElements(By.xpath("(//div[@class='oxd-table']//div[@role='row'])[3]"));
//        List<WebElement> Status = driver.findElements(By.xpath("(//div[@class='oxd-table']//div[@role='row'])[4]"));
//
        // Mapowanie tekstu elementów na listę String

    }

