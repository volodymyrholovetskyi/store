package pl.holovetskyi.internetshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@SpringBootApplication
public class InternetShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(InternetShopApplication.class, args);
    }


}
