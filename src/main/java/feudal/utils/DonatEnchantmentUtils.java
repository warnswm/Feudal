package feudal.utils;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DonatEnchantmentUtils {

    final String name;
    int maxLvl, time;
    double percentagePerLvl, timePercentagePerLvl;

    public DonatEnchantmentUtils(String name) {
        this.name = name;
    }

    public DonatEnchantmentUtils setMaxLvl(int value) {
        maxLvl = value;
        return this;
    }

    public DonatEnchantmentUtils setTime(int value) {
        time = value;
        return this;
    }

    public DonatEnchantmentUtils setPercentagePerLvl(double value) {
        percentagePerLvl = value;
        return this;
    }

    public DonatEnchantmentUtils setTimePercentagePerLvl(double value) {
        timePercentagePerLvl = value;
        return this;
    }
}
