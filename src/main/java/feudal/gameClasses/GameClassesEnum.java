package feudal.gameClasses;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
public enum GameClassesEnum {
    MINER((byte) 7, Miner.class);

    byte id;
    Class aClass;

    public static GameClassesEnum getByID(long id) {
        for (GameClassesEnum gameClassesEnum : values())
            if (gameClassesEnum.getId() == id)
                return gameClassesEnum;
        return null;
    }

    public static GameClassesEnum getByClass(Class clas) {
        for (GameClassesEnum gameClassesEnum : values())
            if (gameClassesEnum.getAClass().equals(clas))
                return gameClassesEnum;
        return null;
    }
}
