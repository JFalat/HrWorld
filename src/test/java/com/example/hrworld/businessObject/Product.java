package com.example.hrworld.businessObject;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {
    private String type;
    private String name;
    private String id;
}
