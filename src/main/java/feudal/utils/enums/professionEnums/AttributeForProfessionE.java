package feudal.utils.enums.professionEnums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum AttributeForProfessionE {
    STRENGTH_AND_STAMINA(1, "Выносливость", "Сила", 3), STAMINA_AND_LUCK(2, "Выносливость", "Удача", 8),
    SURVIVABILITY_AND_LUCK(4, "Живучесть", "Удача"), SPEED_AND_STAMINA(5, "Скорость", "Выносливость"),
    STRENGTH_AND_LUCK(6, "Сила", "Удача"), STAMINA_AND_SURVIVABILITY(7, "Выносливость", "Живучесть"),
    STRENGTH_AND_SURVIVABILITY(9, "Сила", "Живучесть");

    final int oneClassID;
    final String oneAttributeName;
    final String secondAttributeName;
    int secondClassID;

    AttributeForProfessionE(int oneClassID, String oneAttributeName, String secondAttributeName) {
        this.oneClassID = oneClassID;
        this.oneAttributeName = oneAttributeName;
        this.secondAttributeName = secondAttributeName;
    }

    public static String getOneAttributeNameByID(int id) {

        for (AttributeForProfessionE attributeForProfessionE : values())
            if (attributeForProfessionE.getOneClassID() == id ||
                    attributeForProfessionE.getSecondClassID() == id)
                return attributeForProfessionE.getOneAttributeName();

        return "";

    }

    public static String getSecondAttributeNameByID(int id) {

        for (AttributeForProfessionE attributeForProfessionE : values())
            if (attributeForProfessionE.getOneClassID() == id ||
                    attributeForProfessionE.getSecondClassID() == id)
                return attributeForProfessionE.getSecondAttributeName();

        return "";

    }
}