package pl.holovetskyi.internetshop.auxiliary;

import java.util.Random;

public class RandomPrice {

    public static int random() {
        int min = 50;
        int max = 300;
        int diff = max - min;
        Random random = new Random();
        int i = random.nextInt(diff + 1);
        return i += min;
    }
}
