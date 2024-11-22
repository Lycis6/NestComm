package org.example.nestcomm.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductDto {
    @NotNull
    private int price;
    @NotNull
    @Size(max = 20)
    private String name;
    @NotNull
    @Size(max = 200)
    private String description;
    @NotNull
    @Size(max = 20)
    private String city;
    private String author;
    @NotNull
    @Size(max = 20)
    private String category;
    @NotNull
    private Integer balance;
}
