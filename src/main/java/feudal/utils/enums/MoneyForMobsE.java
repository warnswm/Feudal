package feudal.utils.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.bukkit.entity.EntityType;

import static feudal.utils.FeudalValuesUtils.*;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum MoneyForMobsE {
    BAT(EntityType.BAT, getMoneyForBat()), CHICKEN(EntityType.CHICKEN, getMoneyForChicken()), COW(EntityType.COW, getMoneyForCow()),
    MUSHROOM_COW(EntityType.MUSHROOM_COW, getMoneyForMushroomCow()), HORSE(EntityType.HORSE, getMoneyForHorse()), OCELOT(EntityType.OCELOT, getMoneyForOcelot()),
    PARROT(EntityType.PARROT, getMoneyForParrot()), POLAR_BEAR(EntityType.POLAR_BEAR, getMoneyForPolarBear()), PIG(EntityType.PIG, getMoneyForPig()),
    RABBIT(EntityType.RABBIT, getMoneyForRabbit()), SHEEP(EntityType.SHEEP, getMoneyForSheep()), SNOW_MAN(EntityType.SNOWMAN, getMoneyForSnowMan()),
    SQUID(EntityType.SQUID, getMoneyForSquiq()), VILLAGER(EntityType.VILLAGER, getMoneyForVillager()), CAVE_SPIDER(EntityType.CAVE_SPIDER, getMoneyForCaveSpider()),
    ENDERMAN(EntityType.ENDERMAN, getMoneyForEnderMan()), IRON_GOLEM(EntityType.IRON_GOLEM, getMoneyForIronGolem()), LLAMA(EntityType.LLAMA, getMoneyForLlama()),
    SPIDER(EntityType.SPIDER, getMoneyForSpider()), WOLF(EntityType.WOLF, getMoneyForWolf()), BLAZE(EntityType.BLAZE, getMoneyForBlaze()),
    CREEPER(EntityType.CREEPER, getMoneyForCreeper()), ELDER_GUARDIAN(EntityType.ELDER_GUARDIAN, getMoneyForElderGuardian()), ENDERMITE(EntityType.ENDERMITE, getMoneyForEndermite()),
    EVOKER(EntityType.EVOKER, getMoneyForEvolker()), GHAST(EntityType.GHAST, getMoneyForGhast()), GUARDIAN(EntityType.GUARDIAN, getMoneyForGuardian()),
    HUSK(EntityType.HUSK, getMoneyForHask()), MAGMA_CUBE(EntityType.MAGMA_CUBE, getMoneyForMagmaCube()), SHULKER(EntityType.SHULKER, getMoneyForShulker()),
    SILVERFISH(EntityType.SILVERFISH, getMoneyForSilverfish()), SKELETON(EntityType.SKELETON, getMoneyForSkeleton()), SLIME(EntityType.SLIME, getMoneyForSlime()),
    STRAY(EntityType.STRAY, getMoneyForStray()), VEX(EntityType.VEX, getMoneyForVex()), VINDICATOR(EntityType.VINDICATOR, getMoneyForVindicator()),
    WITCH(EntityType.WITCH, getMoneyForWitch()), WITHER_SKELETON(EntityType.WITHER_SKELETON, getMoneyForWitherSkeleton()), ZOMBIE(EntityType.ZOMBIE, getMoneyForZombie()),
    ZOMBIE_VILLAGER(EntityType.ZOMBIE_VILLAGER, getMoneyForZombieVillager()), ENDER_DRAGON(EntityType.ENDER_DRAGON, getMoneyForEnderDragon()), WITHER(EntityType.WITHER, getMoneyForWither());

    EntityType entity;
    int money;

    public static int getMoneyByEntity(EntityType entity) {

        for (MoneyForMobsE moneyForMobsE : values())
            if (moneyForMobsE.getEntity() == entity)
                return moneyForMobsE.getMoney();

        return 0;

    }
}
