package feudal.utils.enums;

import feudal.data.cache.CacheFeudalValues;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.bukkit.entity.EntityType;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum MoneyForMobsE {
    BAT(EntityType.BAT, get(EntityType.BAT)),
    CHICKEN(EntityType.CHICKEN, get(EntityType.CHICKEN)),
    COW(EntityType.COW, get(EntityType.COW)),
    MUSHROOM_COW(EntityType.MUSHROOM_COW, get(EntityType.MUSHROOM_COW)),
    HORSE(EntityType.HORSE, get(EntityType.HORSE)),
    OCELOT(EntityType.OCELOT, get(EntityType.OCELOT)),
    PARROT(EntityType.PARROT, get(EntityType.PARROT)),
    POLAR_BEAR(EntityType.POLAR_BEAR, get(EntityType.POLAR_BEAR)),
    PIG(EntityType.PIG, get(EntityType.PIG)),
    RABBIT(EntityType.RABBIT, get(EntityType.RABBIT)),
    SHEEP(EntityType.SHEEP, get(EntityType.SHEEP)),
    SNOW_MAN(EntityType.SNOWMAN, get(EntityType.SNOWMAN)),
    SQUID(EntityType.SQUID, get(EntityType.SQUID)),
    VILLAGER(EntityType.VILLAGER, get(EntityType.VILLAGER)),
    CAVE_SPIDER(EntityType.CAVE_SPIDER, get(EntityType.CAVE_SPIDER)),
    ENDERMAN(EntityType.ENDERMAN, get(EntityType.ENDERMAN)),
    IRON_GOLEM(EntityType.IRON_GOLEM, get(EntityType.IRON_GOLEM)),
    LLAMA(EntityType.LLAMA, get(EntityType.LLAMA)),
    SPIDER(EntityType.SPIDER, get(EntityType.SPIDER)),
    WOLF(EntityType.WOLF, get(EntityType.WOLF)),
    BLAZE(EntityType.BLAZE, get(EntityType.BLAZE)),
    CREEPER(EntityType.CREEPER, get(EntityType.CREEPER)),
    ELDER_GUARDIAN(EntityType.ELDER_GUARDIAN, get(EntityType.ELDER_GUARDIAN)),
    ENDERMITE(EntityType.ENDERMITE, get(EntityType.ENDERMITE)),
    EVOKER(EntityType.EVOKER, get(EntityType.EVOKER)),
    GHAST(EntityType.GHAST, get(EntityType.GHAST)),
    GUARDIAN(EntityType.GUARDIAN, get(EntityType.GUARDIAN)),
    HUSK(EntityType.HUSK, get(EntityType.HUSK)),
    MAGMA_CUBE(EntityType.MAGMA_CUBE, get(EntityType.MAGMA_CUBE)),
    SHULKER(EntityType.SHULKER, get(EntityType.SHULKER)),
    SILVERFISH(EntityType.SILVERFISH, get(EntityType.SILVERFISH)),
    SKELETON(EntityType.SKELETON, get(EntityType.SKELETON)),
    SLIME(EntityType.SLIME, get(EntityType.SLIME)),
    STRAY(EntityType.STRAY, get(EntityType.STRAY)),
    VEX(EntityType.VEX, get(EntityType.VEX)),
    VINDICATOR(EntityType.VINDICATOR, get(EntityType.VINDICATOR)),
    WITCH(EntityType.WITCH, get(EntityType.WITCH)),
    WITHER_SKELETON(EntityType.WITHER_SKELETON, get(EntityType.WITHER_SKELETON)),
    ZOMBIE(EntityType.ZOMBIE, get(EntityType.ZOMBIE)),
    ZOMBIE_VILLAGER(EntityType.ZOMBIE_VILLAGER, get(EntityType.ZOMBIE_VILLAGER)),
    ENDER_DRAGON(EntityType.ENDER_DRAGON, get(EntityType.ENDER_DRAGON)),
    WITHER(EntityType.WITHER, get(EntityType.WITHER));

    EntityType entity;
    int money;

    public static int getMoneyByEntity(EntityType entity) {

        for (MoneyForMobsE moneyForMobsE : values())
            if (moneyForMobsE.getEntity() == entity)
                return moneyForMobsE.getMoney();

        return 0;

    }

    private static int get(EntityType entity) {

        if (CacheFeudalValues.getMoneyForMobs().containsKey(entity))
            return CacheFeudalValues.getMoneyForMobs().get(entity);

        return 0;

    }
}
