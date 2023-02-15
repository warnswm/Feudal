package feudal.utils.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.bukkit.entity.EntityType;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum MoneyForMobsE {
    BAT(EntityType.BAT, 1), CHICKEN(EntityType.CHICKEN, 3), COW(EntityType.COW, 5),
    MUSHROOM_COW(EntityType.MUSHROOM_COW, 5), HORSE(EntityType.HORSE, 7), OCELOT(EntityType.OCELOT, 5),
    PARROT(EntityType.PARROT, 5), POLAR_BEAR(EntityType.POLAR_BEAR, 8), PIG(EntityType.PIG, 5),
    RABBIT(EntityType.RABBIT, 7), SHEEP(EntityType.SHEEP, 5), SNOW_MAN(EntityType.SNOWMAN, 1),
    SQUID(EntityType.SQUID, 3), VILLAGER(EntityType.VILLAGER, 10), CAVE_SPIDER(EntityType.CAVE_SPIDER, 20),
    ENDERMAN(EntityType.ENDERMAN, 27), IRON_GOLEM(EntityType.IRON_GOLEM, 45), LLAMA(EntityType.LLAMA, 7),
    SPIDER(EntityType.SPIDER, 17), WOLF(EntityType.WOLF, 15), BLAZE(EntityType.BLAZE, 20),
    CREEPER(EntityType.CREEPER, 23), ELDER_GUARDIAN(EntityType.ELDER_GUARDIAN, 5000), ENDERMITE(EntityType.ENDERMITE, 1),
    EVOKER(EntityType.EVOKER, 17), GHAST(EntityType.GHAST, 15), GUARDIAN(EntityType.GUARDIAN, 30),
    HUSK(EntityType.HUSK, 17), MAGMA_CUBE(EntityType.MAGMA_CUBE, 23), SHULKER(EntityType.SHULKER, 27),
    SILVERFISH(EntityType.SILVERFISH, 1), SKELETON(EntityType.SKELETON, 17), SLIME(EntityType.SLIME, 23),
    STRAY(EntityType.STRAY, 20), VEX(EntityType.VEX, 5), VINDICATOR(EntityType.VINDICATOR, 23),
    WITCH(EntityType.WITCH, 13), WITHER_SKELETON(EntityType.WITHER_SKELETON, 30), ZOMBIE(EntityType.ZOMBIE, 15),
    ZOMBIE_VILLAGER(EntityType.ZOMBIE_VILLAGER, 15), ENDER_DRAGON(EntityType.ENDER_DRAGON, 10000), WITHER(EntityType.WITHER, 7000);

    EntityType entity;
    int money;

    public static int getMoneyByEntity(EntityType entity) {

        for (MoneyForMobsE moneyForMobsE : values())
            if (moneyForMobsE.getEntity() == entity)
                return moneyForMobsE.getMoney();

        return 0;

    }
}
