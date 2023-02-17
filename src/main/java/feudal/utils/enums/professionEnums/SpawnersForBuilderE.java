package feudal.utils.enums.professionEnums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.bukkit.entity.EntityType;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum SpawnersForBuilderE {

    SPIDER(EntityType.SPIDER, 25),
    CAVE_SPIDER(EntityType.CAVE_SPIDER, 25);

    EntityType entity;
    int professionLvl;

    public static boolean canBreak(EntityType entity, int professionLvl) {

        for (SpawnersForBuilderE spawnersForBuilderE : values())
            if (spawnersForBuilderE.getEntity() == entity &&
                    professionLvl >= spawnersForBuilderE.getProfessionLvl())
                return true;

        return false;
    }
}
