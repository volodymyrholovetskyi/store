package pl.holovetskyi.internetshop.auxiliary;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "discount")
public class Discount {

    private double percent;

    public double discountPercentage() {
        double percentDiscount = percent / 100;
        return percentDiscount;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }
}
