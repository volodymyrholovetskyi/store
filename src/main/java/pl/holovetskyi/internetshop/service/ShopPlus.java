package pl.holovetskyi.internetshop.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.holovetskyi.internetshop.auxiliary.VatTax;
import pl.holovetskyi.internetshop.domain.Product;
import pl.holovetskyi.internetshop.repository.ShopRepository;

import java.util.List;

@Service
@Profile("ShopPlus")
public class ShopPlus {


    private ShopRepository shopRepository;
    private VatTax vatTax;

    @Autowired
    public ShopPlus(ShopRepository shopRepository, VatTax vatTax) {
        this.shopRepository = shopRepository;
        this.vatTax = vatTax;
    }

    public int sumOfProducts() {
        List<Product> allProduct = shopRepository.getAllProducts();
        System.out.println("---------------------------------------------");
        allProduct.forEach(System.out::println);
        System.out.println("---------------------------------------------");
        int sum = allProduct.stream()
                .mapToInt(x -> x.getPriceProduct())
                .sum();
        System.out.printf(" Cena koncowa bez VAT: %d zl. \n", sum);
        System.out.println("---------------------------------------------");
        return sum;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void priceWithVAT() {
        int sum = sumOfProducts();
        double pricePlusVat = sum * vatTax.percentageOfVat();
        System.out.printf("\n Wartosc brutto dla kwoty: %d zl netto, \n przy %.1f%s stawce VAT, \n wynosi: %.2f zl.\n",
                sum, vatTax.getPercentVAT(), "%", pricePlusVat);
    }
}

