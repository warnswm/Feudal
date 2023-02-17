package feudal.utils.enums.professionEnums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ProfessionIDE {

    BUILDER(1, "Строитель"), COOK(2, "Повар"), FARMER(3, "Фермер"),
    FISHERMAN(4, "Рыболов"), HUNTER(5, "Охотник"), MINER(6, "Шахтёр"),
    SHEPHERD(7, "Пастух"), TRADER(8, "Торговец"), WOODCUTTER(9, "Дровосек"),
    CLERK(12, "Писарь");

    int id;
    String gameClassName;

    public static int getIDByString(String gameClassName) {

        for (ProfessionIDE professionIDE : values())
            if (professionIDE.getGameClassName().equals(gameClassName))
                return professionIDE.getId();

        return 0;

    }

    public static String getNameByID(int id) {

        for (ProfessionIDE professionIDE : values())
            if (professionIDE.getId() == id)
                return professionIDE.getGameClassName();

        return "Не выбран";

    }
}