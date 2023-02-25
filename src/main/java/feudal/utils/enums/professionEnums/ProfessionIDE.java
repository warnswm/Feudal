package feudal.utils.enums.professionEnums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ProfessionIDE {

    BUILDER(1, "Строитель", 3), COOK(2, "Повар", 3), FARMER(3, "Фермер", 3),
    FISHERMAN(4, "Рыболов", 3), HUNTER(5, "Охотник", 3), MINER(6, "Шахтёр", 3),
    SHEPHERD(7, "Пастух", 3), TRADER(8, "Торговец", 6), WOODCUTTER(9, "Дровосек", 3),
    ALCHEMIST(10, "Алхимик", 6), BLACKSMITH(11, "Кузнец", 6), CLERK(12, "Писарь", 3),
    GUARD(13, "Стражник", 6), HEALER(14, "Лекарь", 6), KILLER(15, "Убийца", 1),
    BARON(16, "Барон", 22), KING(17, "Король", 30), KNIGHT(18, "Рыцарь", 14),
    SQUIRE(19, "Оруженосец", 10);

    int id;
    String professionName;
    int containsLand;

    public static int getIDByString(String professionName) {

        if (professionName == null) return 0;

        for (ProfessionIDE professionIDE : values())
            if (professionIDE.getProfessionName().equals(professionName))
                return professionIDE.getId();

        return 0;

    }

    public static String getNameByID(int id) {

        for (ProfessionIDE professionIDE : values())
            if (professionIDE.getId() == id)
                return professionIDE.getProfessionName();

        return "Не выбрана";

    }

    public static int getContainsLandBYID(int id) {

        for (ProfessionIDE professionIDE : values())
            if (professionIDE.getId() == id)
                return professionIDE.getContainsLand();

        return 0;

    }
}
