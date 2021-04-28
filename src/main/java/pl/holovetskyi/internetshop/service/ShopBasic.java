package pl.holovetskyi.internetshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.holovetskyi.internetshop.domain.Product;
import pl.holovetskyi.internetshop.repository.ShopRepository;

import java.util.List;

@Service
@Profile("ShopBasic")
public class ShopBasic {

    private ShopRepository shopRepository;

    @Autowired
    public ShopBasic(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void sumOfProducts() {
        List<Product> allProducts = shopRepository.getAllProducts();
        System.out.println("---------------------------------------------");
        allProducts.forEach(System.out::println);
        System.out.println("---------------------------------------------");
        int sum = allProducts.stream()
                .mapToInt(x -> x.getPriceProduct())
                .sum();
        System.out.printf(" Cena ostateczna: %d zl. \n", sum);
    }
}
