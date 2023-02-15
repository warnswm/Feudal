package feudal.utils.enums.gameClassesEnums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum GameClassesIDEnum {

    BUILDER(1, "Строитель"), COOK(2, "Повар"), FARMER(3, "Фермер"),
    FISHERMAN(4, "Рыболов"), HUNTER(5, "Охотник"), MINER(6, "Шахтёр"),
    SHEPHERD(7, "Пастух"), TRADER(8, "Торговец"), WOODCUTTER(9, "Дровосек"),
    CLERK(12, "Писарь");

    int id;
    String gameClassName;

    public static int getIDByString(String gameClassName) {

        for (GameClassesIDEnum gameClassesIDEnum : values())
            if (gameClassesIDEnum.getGameClassName().equals(gameClassName))
                return gameClassesIDEnum.getId();

        return 0;

    }

    public static String getNameByID(int id) {

        for (GameClassesIDEnum gameClassesIDEnum : values())
            if (gameClassesIDEnum.getId() == id)
                return gameClassesIDEnum.getGameClassName();

        return "Не выбран";

    }
}
