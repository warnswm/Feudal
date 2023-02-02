package feudal.utils;


import java.util.Random;

public class MathUtils {
    public static int getRandInt(int min, int max) {
        return new Random().ints(min, max)
                .findFirst()
                .getAsInt();
    }
}
