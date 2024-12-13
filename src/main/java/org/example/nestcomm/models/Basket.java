package org.example.nestcomm.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "basket")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    // хранятся id товаров через разделитель ","
    @Column(name = "ordered_product_ids", columnDefinition = "text")
    private String orderedProductIds;
    @Column(name = "total_price")
    private Integer totalPrice;
//    private int totalPrice;

    // Парсит строку с id на массив лонгов, удаляет дубликаты id из массива и считает их количество
    public Long[][] TransferStringToLong(){
        String[] productIds = this.orderedProductIds.split(",");
        if(productIds.length == 0) {
            log.error("Empty product ids");
            return null;
        }

        Long[] result = new Long[productIds.length];
        for(int i = 0; i < productIds.length; i++){
            if(!productIds[i].isEmpty()) {
                result[i] = Long.parseLong(productIds[i]);
            }
        }

        Long [] filteredIds = new Long[result.length];
        Long [] counts = new Long[result.length];
        boolean found = false;
        int i = 0;
        for(Long x : result){
            if(x != null){
                for(Long y : filteredIds){
                    if(x.equals(y)){
                        found = true;
                        break;
                    }
                }
                if(!found){
                    filteredIds[i] = x;
                    counts[i] = Arrays.stream(result).filter(element -> Objects.equals(element, x)).count();
                    i++;
                }
                found = false;
            }
        }
        Long[][] matrix = new Long[filteredIds.length][2];
        i = 0;
        for(Long x : filteredIds){
            if(x != null){
                matrix[i][0] = x;
                matrix[i][1] = counts[i];
                i++;
            }
        }
        return matrix;
    }
}
