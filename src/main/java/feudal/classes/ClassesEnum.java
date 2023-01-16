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
    BUILDER((byte) 5, Builder.class),
    FARMER((byte) 6, Farmer.class),
    HUNTER((byte) 7, Hunter.class),
    MINER((byte) 8, Miner.class),
    SWORDSMAN((byte) 9, Swordsman.class),
    ARCHER_RIDER((byte) 10, ArcherRider.class),
    SWORDSMAN_RIDER((byte) 11, SwordsmanRider.class),
    WOODCUTTER((byte) 12, Woodcutter.class);

    byte id;
    Class aClass;

    public static ClassesEnum getByID(long id) {
        for (ClassesEnum packets : values())
            if (packets.getId() == id)
                return packets;
        return null;
    }

    public static ClassesEnum getByClass(Class clas) {
        for (ClassesEnum packets : values())
            if (packets.getAClass().equals(clas))
                return packets;
        return null;
    }
}
