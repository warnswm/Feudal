package feudal.utils.gameClassesEnums;

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
    SHEPHERD(7, "Пастух"), TRADER(8, "Торговец"), WOODCUTTER(9, "Дровосек");

    int id;
    String gameClassName;

    public static int getByString(String gameClassName) {
        for (GameClassesIDEnum gameClassesIDEnum : values())
            if (gameClassesIDEnum.getGameClassName().equals(gameClassName))
                return gameClassesIDEnum.getId();
        return 0;
    }
}
