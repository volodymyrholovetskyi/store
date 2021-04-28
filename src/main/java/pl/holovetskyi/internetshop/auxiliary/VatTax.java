package pl.holovetskyi.internetshop.auxiliary;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "vat-tax")
public class VatTax {

    private double percentVAT;

    public double percentageOfVat() {
        double sum = percentVAT / 100 + 1;
        return sum;
    }

    public double getPercentVAT() {
        return percentVAT;
    }

    public void setPercentVAT(double percentVAT) {
        this.percentVAT = percentVAT;
    }
}
