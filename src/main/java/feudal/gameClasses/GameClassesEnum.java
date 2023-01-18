package feudal.gameClasses;

import feudal.gameClasses.firstGameClasses.*;
import feudal.gameClasses.secondGameClasses.*;
import feudal.gameClasses.thirdGameClasses.Baron;
import feudal.gameClasses.thirdGameClasses.King;
import feudal.gameClasses.thirdGameClasses.Knight;
import feudal.gameClasses.thirdGameClasses.Squire;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
public enum GameClassesEnum {
    BUILDER(1, Builder.class),
    CLERK(2, Clerk.class),
    COOK(3, Cook.class),
    FARMER(4, Farmer.class),
    FISHERMAN(5, Fisherman.class),
    MINER(6, Miner.class),
    SHEPHERD(7, Shepherd.class),
    WOODCUTTER(8, Woodcutter.class),
    ALCHEMIST(9, Alchemist.class),
    BLACKSMITH(10, Blacksmith.class),
    GUARD(11, Guard.class),
    HEALER(12, Healer.class),
    KILLER(13, Killer.class),
    TRADER(14, Trader.class),
    BARON(15, Baron.class),
    KING(16, King.class),
    KNIGHT(17, Knight.class),
    SQUIRE(18, Squire.class);

    int id;
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
