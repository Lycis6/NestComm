package org.example.nestcomm.models;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {
    private Long ID;
    private int price;
    private String name;
    private String description;
    private String city;
    private String author;
    private String category;
}
