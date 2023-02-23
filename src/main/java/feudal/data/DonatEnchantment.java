package feudal.data;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@ToString
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DonatEnchantment {

    final String name;
    int maxLvl, time;
    double percentagePerLvl, timePercentagePerLvl;

    public DonatEnchantment(String displayName) {
        name = displayName;
    }

    public DonatEnchantment setMaxLvl(int value) {
        maxLvl = value;
        return this;
    }

    public DonatEnchantment setTime(int value) {
        time = value;
        return this;
    }

    public DonatEnchantment setPercentagePerLvl(double value) {
        percentagePerLvl = value;
        return this;
    }

    public DonatEnchantment setTimePercentagePerLvl(double value) {
        timePercentagePerLvl = value;
        return this;
    }
}
