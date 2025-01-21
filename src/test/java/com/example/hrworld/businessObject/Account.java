package com.example.hrworld.businessObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Generuje gettery, settery, equals, hashCode i toString
@AllArgsConstructor // Generuje konstruktor z wszystkimi polami
@NoArgsConstructor
public class Account {
    private String name;
    private String lastname;
    private String mail;
    private String telephone;
    private String address1;
    private String address2;
    private String town;
    private String state;
    private String cityCode;
    private String country;

}
