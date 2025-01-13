package com.example.hrworld;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
public class EmployeeTest extends BaseTest {

    @Test
    public void testFetchFirstNames() throws InterruptedException {
        driver.get("https://przyklady.javastart.pl/jpetstore/actions/Catalog.action");

        // Tworzenie obiektu strony
        Registration registration = new Registration(driver);

        // Kliknij w link "Sign In"
        registration.clickSignIn();
        registration.clickRegistration();

        EmployeePage employeePage = new EmployeePage();
        employeePage.enterUserData(driver);


        Thread.sleep(2000);



    }
}
