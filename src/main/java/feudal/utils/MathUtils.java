package feudal.utils;


import java.util.concurrent.ThreadLocalRandom;

public class MathUtils {
    public static int getRandomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }
}
