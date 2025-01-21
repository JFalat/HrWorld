package com.example.hrworld.businessObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Generuje gettery, settery, equals, hashCode i toString
@AllArgsConstructor // Generuje konstruktor z wszystkimi polami
@NoArgsConstructor
public class Profile {
    private String favouriteCategory;
    private String languageSelector;
    private boolean enable_MyList;
    private boolean enable_MyBanner;
}
