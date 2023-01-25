package feudal.utils.gameClassesEnums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum AttributeForGameClassesEnum {
    STRENGTH_AND_STAMINA(1, 3, "Сила", "Выносливость"),
    STAMINA_AND_LUCK(2, 8, "Выносливость", "Удача"),
    SURVIVABILITY_AND_LUCK(4, "Живучесть", "Удача"),
    SPEED_AND_STAMINA(5, "Скорость", "Выносливость"),
    STRENGTH_AND_LUCK(6, "Сила", "Удача"),
    STAMINA_AND_SURVIVABILITY(7, "Выносливость", "Живучесть"),
    STRENGTH_AND_SURVIVABILITY(9, "Сила", "Живучесть");

    final int oneClassID;
    int secondClassID;
    final String oneAttributeName;
    final String secondAttributeName;

    AttributeForGameClassesEnum(int oneClassID, String oneAttributeName, String secondAttributeName) {
        this.oneClassID = oneClassID;
        this.oneAttributeName = oneAttributeName;
        this.secondAttributeName = secondAttributeName;
    }

    public static String getOneAttributeNameByID(int id) {
        for (AttributeForGameClassesEnum attributeForGameClassesEnum : values())
            if (attributeForGameClassesEnum.getOneClassID() == id ||
                    attributeForGameClassesEnum.getSecondClassID() == id)
                return attributeForGameClassesEnum.getOneAttributeName();
        return "";
    }
    public static String getSecondAttributeNameByID(int id) {
        for (AttributeForGameClassesEnum attributeForGameClassesEnum : values())
            if (attributeForGameClassesEnum.getOneClassID() == id ||
                    attributeForGameClassesEnum.getSecondClassID() == id)
                return attributeForGameClassesEnum.getSecondAttributeName();
        return "";
    }
}
