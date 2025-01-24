package com.example.hrworld.businessObject;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {
    private ProductType type;
    private String name;
    private String id;
}
