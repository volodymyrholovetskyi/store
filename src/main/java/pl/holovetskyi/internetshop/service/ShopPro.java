package pl.holovetskyi.internetshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.holovetskyi.internetshop.auxiliary.Discount;
import pl.holovetskyi.internetshop.auxiliary.VatTax;
import pl.holovetskyi.internetshop.domain.Product;
import pl.holovetskyi.internetshop.repository.ShopRepository;

import java.util.List;

@Service
@Profile("ShopPro")
public class ShopPro {

    private ShopRepository shopRepository;
    private Discount discount;
    private VatTax vat;
    //    private MessageSource messageSource;
    private MessageSource messageSource;

    @Autowired
    public ShopPro(ShopRepository shopRepository, Discount discount, VatTax vat, MessageSource messageSource) {
        this.shopRepository = shopRepository;
        this.discount = discount;
        this.vat = vat;
        this.messageSource = messageSource;
    }

    public int sumOfProducts() {
        List<Product> allProduct = shopRepository.getAllProducts();
        System.out.println("---------------------------------------------");
        allProduct.forEach(System.out::println);
        System.out.println("---------------------------------------------");
        int sum = allProduct.stream()
                .mapToInt(x -> x.getPriceProduct())
                .sum();
        System.out.printf(" Cena końcowa bez VAT: %d zl. \n", sum);
        System.out.println("---------------------------------------------");
        return sum;
    }

    public double priceWithVAT() {
        int sum = sumOfProducts();
        double pricePlusVat = sum * vat.percentageOfVat();
        System.out.printf("\n Wartosc brutto dla kwoty: %d zl netto, \n przy %.1f%s stawce VAT, \n wynosi: %.2f " +
                        "zl.\n",
                sum, vat.getPercentVAT(), "%", pricePlusVat);
        System.out.println("---------------------------------------------");

        return pricePlusVat;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void calculateDiscounts() {
        double sum = priceWithVAT();
        double discounts = discount.discountPercentage() * sum;
        System.out.printf("\n Rabat -%.1f%s, od sumy: %.2f zl, wynosi: %.2f zl.\n", discount.getPercent(), "%", sum,
                discounts);

    }
}
