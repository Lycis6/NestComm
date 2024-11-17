package org.example.nestcomm.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductDto {
    @NotNull
    private int price;
    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    private String city;
    private String author;
    @NotNull
    private String category;
//    @NotNull
//    private int balance;
}
