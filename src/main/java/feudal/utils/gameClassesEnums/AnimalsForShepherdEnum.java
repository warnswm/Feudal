package feudal.utils.gameClassesEnums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.bukkit.entity.EntityType;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum AnimalsForShepherdEnum {
    SHEEP(EntityType.SHEEP, 5),
    COW(EntityType.COW, 5),
    MUSHROOM_COW(EntityType.MUSHROOM_COW, 5),
    OCELOT(EntityType.OCELOT, 5),
    RABBIT(EntityType.RABBIT, 5),
    PIG(EntityType.PIG, 6),
    HORSE(EntityType.HORSE, 25),
    DONKEY(EntityType.DONKEY, 25),
    CHICKEN(EntityType.CHICKEN, 3),
    WOLF(EntityType.WOLF, 12),
    LLAMA(EntityType.LLAMA, 12);

    EntityType entity;
    int attributeExp;

    public static int getByEntity(EntityType entity) {
        for (AnimalsForShepherdEnum animalsForShepherdEnum : values())
            if (animalsForShepherdEnum.getEntity() == entity)
                return animalsForShepherdEnum.attributeExp;
        return 0;
    }
}