package feudal.utils.enums.gameClassesEnums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ClerkTakeExpEnum {

    I(1, 1.5f),
    II(1, 2.5f),
    III(1, 4f),
    IV(1, 5f),
    V(1, 7.5f);


    int lvl;
    float exp;

    public static float getLvl(int lvl) {
        for (ClerkTakeExpEnum blocksForFarmerEnum : values())
            if (blocksForFarmerEnum.getLvl() == lvl)
                return blocksForFarmerEnum.getExp();
        return 0;
    }
}
