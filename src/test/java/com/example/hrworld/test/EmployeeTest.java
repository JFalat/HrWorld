package com.example.hrworld.test;

import com.example.hrworld.businessObject.Account;
import com.example.hrworld.businessObject.Profile;
import com.example.hrworld.businessObject.User;
import com.example.hrworld.pages.EmployeePage;
import org.junit.jupiter.api.Test;

public class EmployeeTest extends BaseTest {

    @Test
    public void testFetchFirstNames() {
        driver.get("https://przyklady.javastart.pl/jpetstore/actions/Catalog.action");

        EmployeePage employeePage = new EmployeePage(driver);

        Account account = new Account("name", "lastname", "mail", "123", "dsfds", "fdsfdsf", "Wawa", "maz", "2647", "Polandia");
        Profile profile = new Profile("FISH", "english", true, true);
        User user = new User("password", profile, account);

        employeePage.clickSignIn();
        employeePage.clickRegistration();
        employeePage.enterUserData(user);
    }
}
