package org.example.nestcomm.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.nestcomm.dto.ProductDto;

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
    @Column(name = "balance")
    private Integer balance;

    // CascadeType.ALL - удаляет все фотографии связанные с товаром при удалении товара
    // и добавляет все фотографии при добавлении товара, т.е отдельно к репозиторию фотографий обращаться не нужно
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "product")
    private List<Image> images = new ArrayList<>();

    private Long previewImageId;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public boolean IsInStock(){
        return balance > 0;
    }

    public void addImage(Image image) {
        image.setProduct(this);
        this.images.add(image);
    }

    public void transferDtoToModel(ProductDto dto) {
        this.price = dto.getPrice();
        this.name = dto.getName();
        this.description = dto.getDescription();
        this.city = dto.getCity();
        if(dto.getAuthor() != null) this.author = dto.getAuthor();
        this.category = dto.getCategory();
        this.balance = dto.getBalance();
    }
}
