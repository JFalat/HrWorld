package com.example.hrworld.businessObject;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Item {
    private String itemId;    // Unikalne ID itemu
    private String productId;
    private String description;
    private double price;     // Cena itemu
}
