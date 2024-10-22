package org.example.nestcomm.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long ID;
    @Column(name = "price")
    private int price;
    @Column(name = "name")
    private String name;
    @Column(name = "description", columnDefinition = "text")
    private String description;
    @Column(name = "city")
    private String city;
    @Column(name = "author")
    private String author;
    @Column(name = "category")
    private String category;

    // CascadeType.ALL - удаляет все фотографии связанные с товаром при удалении товара
    // и добавляет все фотографии при добавления товара, т.е отдельно к репозиторию фотографий обращаться не нужно
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
    private List<Image> images = new ArrayList<>();

    private Long previewImageId;

    public void addImage(Image image) {
        image.setProduct(this);
        this.images.add(image);
    }
}
