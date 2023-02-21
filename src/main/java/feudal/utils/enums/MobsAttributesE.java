package feudal.utils.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.bukkit.entity.EntityType;


@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum MobsAttributesE {

    POLAR_BEAR(EntityType.POLAR_BEAR, 60), CAVE_SPIDER(EntityType.CAVE_SPIDER, 24), ENDERMAN(EntityType.ENDERMAN, 80),
    IRON_GOLEM(EntityType.IRON_GOLEM, 200), SPIDER(EntityType.SPIDER, 32), WOLF(EntityType.WOLF, 16),
    BLAZE(EntityType.BLAZE, 40), CREEPER(EntityType.CREEPER, 40), ENDERMITE(EntityType.ENDERMITE, 16),
    GHAST(EntityType.GHAST, 20), GUARDIAN(EntityType.GUARDIAN, 60), HUSK(EntityType.HUSK, 40),
    SHULKER(EntityType.SHULKER, 60), SILVERFISH(EntityType.SILVERFISH, 16), SKELETON(EntityType.SKELETON, 40),
    STRAY(EntityType.STRAY, 40), VEX(EntityType.VEX, 28), VINDICATOR(EntityType.VINDICATOR, 48),
    ZOMBIE(EntityType.ZOMBIE, 40), WITHER_SKELETON(EntityType.WITHER_SKELETON, 40), ELDER_GUARDIAN(EntityType.ELDER_GUARDIAN, 160),
    WITHER(EntityType.WITHER, 1200), ENDER_DRAGON(EntityType.ENDER_DRAGON, 10000);

    EntityType entity;
    int hp;

    public static int getHPByEntity(EntityType entity) {

        for (MobsAttributesE mobsAttributesE : values())
            if (mobsAttributesE.getEntity() == entity)
                return mobsAttributesE.getHp();

        return 0;

    }
}
