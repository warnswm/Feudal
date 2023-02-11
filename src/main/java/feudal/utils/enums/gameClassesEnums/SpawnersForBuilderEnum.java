package feudal.utils.enums.gameClassesEnums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.bukkit.entity.EntityType;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum SpawnersForBuilderEnum {

    SPIDER(EntityType.SPIDER, 25),
    CAVE_SPIDER(EntityType.CAVE_SPIDER, 25);


    EntityType entity;
    int gameClassLvl;

    public static boolean canBreak(EntityType entity, int gameClassLvl) {
        for (SpawnersForBuilderEnum spawnersForBuilderEnum : values())
            if (spawnersForBuilderEnum.getEntity() == entity && gameClassLvl >= spawnersForBuilderEnum.getGameClassLvl())
                return true;
        return false;
    }
}
