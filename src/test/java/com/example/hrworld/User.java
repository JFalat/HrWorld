package com.example.hrworld;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data // Generuje gettery, settery, equals, hashCode i toString
//@AllArgsConstructor // Generuje konstruktor z wszystkimi polami
//@NoArgsConstructor  // Generuje konstruktor bezargumentowy
public class User {
    private String passwordValue;
    private String repeatedPasswordValue;

    // Kompozycja: User posiada Profile i Account
    private Profile profile;
    private Account account;

    // Konstruktor z wszystkimi polami (w tym profile i account)
    public User(String passwordValue, String repeatedPasswordValue, Profile profile, Account account) {
        this.passwordValue = passwordValue;
        this.repeatedPasswordValue = repeatedPasswordValue;
        this.profile = profile;
        this.account = account;
    }
}
