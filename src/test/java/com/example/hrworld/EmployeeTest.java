package com.example.hrworld;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
public class EmployeeTest extends BaseTest {

    @Test
    public void testFetchFirstNames() throws InterruptedException {
        driver.get("https://przyklady.javastart.pl/jpetstore/actions/Catalog.action");

        // Tworzenie obiektu strony
        EmployeePage employeePage = new EmployeePage(driver);
        employeePage.clickSignIn();
        employeePage.clickRegistration();
        employeePage.enterUserData();

        Thread.sleep(2000);



    }
}
