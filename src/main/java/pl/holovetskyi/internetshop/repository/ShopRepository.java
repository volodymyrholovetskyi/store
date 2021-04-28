package pl.holovetskyi.internetshop.repository;

import org.springframework.stereotype.Repository;
import pl.holovetskyi.internetshop.auxiliary.RandomPrice;
import pl.holovetskyi.internetshop.domain.Product;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ShopRepository {

    List<Product> productCart = new ArrayList<>();

    public void createProduct(Product product) {
        productCart.add(product);
    }

    public List<Product> getAllProducts() {
        return productCart;
    }

    @PostConstruct
    public void build() {
        createProduct(new Product("Trouser", RandomPrice.random()));
        createProduct(new Product("T-shirt", RandomPrice.random()));
        createProduct(new Product("Hat", RandomPrice.random()));
        createProduct(new Product("Boots", RandomPrice.random()));
        createProduct(new Product("Clock", RandomPrice.random()));
    }

    @Override
    public String toString() {
        return "ShopRepository{" +
                "productCart=" + productCart +
                '}';
    }
}
