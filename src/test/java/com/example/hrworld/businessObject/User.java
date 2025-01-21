package com.example.hrworld.businessObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Generuje gettery, settery, equals, hashCode i toString
@AllArgsConstructor // Generuje konstruktor z wszystkimi polami
@NoArgsConstructor  // Generuje konstruktor bezargumentowy
public class User {
    private String passwordValue;

    // Kompozycja: User posiada Profile i Account
    private Profile profile;
    private Account account;

}
