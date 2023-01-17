package feudal.classes;

import feudal.entity.archer.ArcherRider;
import feudal.entity.swordsman.SwordsmanRider;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
public enum ClassesEnum {

    ALCHEMIST((byte) 1, Alchemist.class),
    ANGLER((byte) 2, Angler.class),
    ARCHER((byte) 3, Archer.class),
    BLACKSMITH((byte) 4, Blacksmith.class),
    FARMER((byte) 0, Farmer.class),
    HUNTER((byte) 6, Hunter.class),
    MINER((byte) 7, Miner.class),
    SWORDSMAN((byte) 8, Swordsman.class),
    ARCHER_RIDER((byte) 9, ArcherRider.class),
    SWORDSMAN_RIDER((byte) 10, SwordsmanRider.class),
    WOODCUTTER((byte) 11, Woodcutter.class);

    byte id;
    Class aClass;

    public static ClassesEnum getByID(long id) {
        for (ClassesEnum classesEnum : values())
            if (classesEnum.getId() == id)
                return classesEnum;
        return null;
    }

    public static ClassesEnum getByClass(Class clas) {
        for (ClassesEnum classesEnum : values())
            if (classesEnum.getAClass().equals(clas))
                return classesEnum;
        return null;
    }
}
