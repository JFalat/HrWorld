package com.example.hrworld.businessObject;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Product {
    private ProductType type;       // Typ produktu (np. FISH, DOGS)
    private String name;            // Nazwa produktu
    private String id;              // ID produktu
    private List<Item> items;       // Lista itemów powiązanych z produktem
}
