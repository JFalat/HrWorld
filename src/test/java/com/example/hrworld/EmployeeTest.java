package com.example.hrworld;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
public class EmployeeTest extends BaseTest {

    @Test
    public void testFetchFirstNames() throws InterruptedException {
        driver.get("https://przyklady.javastart.pl/jpetstore/actions/Catalog.action");

        // Tworzenie obiektu strony
        EmployeePage employeePage = new EmployeePage(driver);
        User user = new User("password","password","name","lastname","mail","123","dsfds",
                "fdsfdsf","Wawa","maz","2647","Polandia","FISH", "english",
                true,true);

        employeePage.clickSignIn();
        employeePage.clickRegistration();
        employeePage.enterUserData(user);

        Thread.sleep(2000);



    }
}
