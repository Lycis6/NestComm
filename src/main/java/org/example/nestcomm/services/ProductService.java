package org.example.nestcomm.services;
import org.example.nestcomm.models.Product;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ProductService {
    private List<Product> products = new ArrayList<>();
    static private Long ID = 0L;

    ProductService()
    {
        ID++;
        products.add(new Product(ID,69999,"PS5", "game desk", "Moscow","Andrey", "Electronics"));
    }

    public List<Product> getList(){return products;}

    public void saveProduct(Product product){
        ID++;
        product.setID(ID);
        products.add(product);
    }

    public Product getProductById(Long id){
        for(int i = 0; i < products.size(); i++){
            if(Objects.equals(products.get(i).getID(), ID)){
                return products.get(i);
            }
        }
        return null;
    }

    public void deleteProduct(Long ID){
        for(int i = 0; i < products.size(); i++){
            if(Objects.equals(products.get(i).getID(), ID)){
                products.remove(i);
            }
        }
    }
}
