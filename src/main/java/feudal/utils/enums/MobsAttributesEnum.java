package feudal.utils.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.bukkit.entity.EntityType;


@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum MobsAttributesEnum {

    POLAR_BEAR(EntityType.POLAR_BEAR, 18, 60),
    CAVE_SPIDER(EntityType.CAVE_SPIDER, 6, 24),
    ENDERMAN(EntityType.ENDERMAN, 20, 80),
    IRON_GOLEM(EntityType.IRON_GOLEM, 62, 200),
    SPIDER(EntityType.SPIDER, 6, 32),
    WOLF(EntityType.WOLF, 12, 16),
    BLAZE(EntityType.BLAZE, 10, 40),
    CREEPER(EntityType.CREEPER, 98, 40),
    ELDER_GUARDIAN(EntityType.ELDER_GUARDIAN, 24, 160),
    ENDERMITE(EntityType.ENDERMITE, 6, 16),
    GHAST(EntityType.GHAST, 50, 20),
    GUARDIAN(EntityType.GUARDIAN, 18, 60),
    HUSK(EntityType.HUSK, 10, 40),
    SHULKER(EntityType.SHULKER, 8, 60),
    SILVERFISH(EntityType.SILVERFISH, 2, 16),
    SKELETON(EntityType.SKELETON, 10, 40),
    STRAY(EntityType.STRAY, 10, 40),
    VEX(EntityType.VEX, 26, 28),
    VINDICATOR(EntityType.VINDICATOR, 38, 48),
    ZOMBIE(EntityType.ZOMBIE, 10, 40),
    WITHER_SKELETON(EntityType.WITHER_SKELETON, 24, 40);

    EntityType entity;
    int strength;
    int hp;

    public static int getStrengthByEntity(EntityType entity) {
        for (MobsAttributesEnum mobsAttributesEnum : values())
            if (mobsAttributesEnum.getEntity() == entity)
                return mobsAttributesEnum.getStrength();
        return 0;
    }

    public static int getHPByEntity(EntityType entity) {
        for (MobsAttributesEnum mobsAttributesEnum : values())
            if (mobsAttributesEnum.getEntity() == entity)
                return mobsAttributesEnum.getHp();
        return 0;
    }
}
