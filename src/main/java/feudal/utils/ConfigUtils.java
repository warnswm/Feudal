package feudal.utils;

import com.mongodb.client.MongoClients;
import feudal.Feudal;
import feudal.data.DonatEnchantment;
import feudal.data.cache.CacheFeudalValues;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.NonNls;

import java.io.File;

@UtilityClass
public class ConfigUtils {
    private final File path = Feudal.getPlugin().getDataFolder();
    private @NonNls FileConfiguration databaseCfg;
    private @NonNls FileConfiguration enchantmentsCfg;
    private @NonNls FileConfiguration kingdomTaxCfg;
    private @NonNls FileConfiguration moneyForMobsCfg;
    private @NonNls FileConfiguration attributesCfg;
    private @NonNls FileConfiguration generalCfg;

    public void readDatabaseConfig() {

        File file = new File(path, "database.yml");
        createDatabaseConfig();

        databaseCfg = YamlConfiguration.loadConfiguration(file);

        CacheFeudalValues.setMongoClient(MongoClients.create((String) databaseCfg.get("Mongo.address")));
        CacheFeudalValues.setDatabase(CacheFeudalValues.getMongoClient().getDatabase((String) databaseCfg.get("Mongo.name")));
        CacheFeudalValues.setPlayersCollection(CacheFeudalValues.getDatabase().getCollection((String) databaseCfg.get("Mongo.playersCollection")));
        CacheFeudalValues.setKingdomsCollection(CacheFeudalValues.getDatabase().getCollection((String) databaseCfg.get("Mongo.kingdomsCollection")));

    }

    @SneakyThrows
    public void saveDatabaseConfig() {

        File file = new File(path, "database.yml");
        createDatabaseConfig();

        YamlConfiguration.loadConfiguration(file).save(file);

    }

    @SneakyThrows
    public void createDatabaseConfig() {

        if (!path.exists())
            path.mkdir();

        File file = new File(path, "database.yml");
        if (!file.exists()) {

            file.createNewFile();

            databaseCfg = YamlConfiguration.loadConfiguration(file);

            databaseCfg.set("Mongo.address", "mongodb://localhost:27017");
            databaseCfg.set("Mongo.name", "local");
            databaseCfg.set("Mongo.playersCollection", "players");
            databaseCfg.set("Mongo.kingdomsCollection", "kingdoms");

            databaseCfg.save(file);

        }

    }

    public void readEnchantmentsConfig() {

        File file = new File(path, "enchantments.yml");
        createEnchantmentsConfig();

        enchantmentsCfg = YamlConfiguration.loadConfiguration(file);

        DonatEnchantment vampirism = new DonatEnchantment("vampirism")
                .setMaxLvl((int) enchantmentsCfg.get("Vampirism.vampirismMaxLvl"))
                .setPercentagePerLvl((double) enchantmentsCfg.get("Vampirism.vampirismPercentagePerLvl"));

        DonatEnchantment doubleDamage = new DonatEnchantment("doubleDamage")
                .setMaxLvl((int) enchantmentsCfg.get("DoubleDamage.doubleDamageMaxLvl"))
                .setPercentagePerLvl((double) enchantmentsCfg.get("DoubleDamage.doubleDamagePercentagePerLvl"));

        DonatEnchantment greed = new DonatEnchantment("greed")
                .setMaxLvl((int) enchantmentsCfg.get("Greed.greedMaxLvl"))
                .setPercentagePerLvl((double) enchantmentsCfg.get("Greed.greedPercentagePerLvl"));

        DonatEnchantment hook = new DonatEnchantment("hook")
                .setMaxLvl((int) enchantmentsCfg.get("Hook.hookMaxLvl"))
                .setPercentagePerLvl((double) enchantmentsCfg.get("Hook.hookPercentagePerLvl"));

        DonatEnchantment multi_shooting = new DonatEnchantment("multi-shooting")
                .setMaxLvl((int) enchantmentsCfg.get("Multi-shooting.multi_shootingMaxLvl"))
                .setPercentagePerLvl((double) enchantmentsCfg.get("Multi-shooting.multi_shootingPercentagePerLvl"));

        DonatEnchantment blindness = new DonatEnchantment("blindness")
                .setMaxLvl((int) enchantmentsCfg.get("Blindness.blindnessMaxLvl"))
                .setPercentagePerLvl((double) enchantmentsCfg.get("Blindness.blindnessPercentagePerLvl"))
                .setTime((int) enchantmentsCfg.get("Blindness.blindnessTime"))
                .setTimePercentagePerLvl((double) enchantmentsCfg.get("Blindness.blindnessTimePercentagePerLvl"));

        DonatEnchantment slowdown = new DonatEnchantment("slowdown")
                .setMaxLvl((int) enchantmentsCfg.get("Slowdown.slowdownMaxLvl"))
                .setPercentagePerLvl((double) enchantmentsCfg.get("Slowdown.slowdownPercentagePerLvl"))
                .setTime((int) enchantmentsCfg.get("Slowdown.slowdownTime"))
                .setTimePercentagePerLvl((double) enchantmentsCfg.get("Slowdown.slowdownTimePercentagePerLvl"));

        DonatEnchantment desiccation = new DonatEnchantment("desiccation")
                .setMaxLvl((int) enchantmentsCfg.get("Desiccation.desiccationMaxLvl"))
                .setPercentagePerLvl((double) enchantmentsCfg.get("Desiccation.desiccationPercentagePerLvl"))
                .setTime((int) enchantmentsCfg.get("Desiccation.slowdownTime"))
                .setTimePercentagePerLvl((double) enchantmentsCfg.get("Desiccation.slowdownTimePercentagePerLvl"));

        DonatEnchantment swordStun = new DonatEnchantment("swordStun")
                .setMaxLvl((int) enchantmentsCfg.get("SwordStun.swordStunMaxLvl"))
                .setPercentagePerLvl((double) enchantmentsCfg.get("SwordStun.swordStunPercentagePerLvl"))
                .setTime((int) enchantmentsCfg.get("SwordStun.swordStunTime"))
                .setTimePercentagePerLvl((double) enchantmentsCfg.get("SwordStun.swordStunTimePercentagePerLvl"));

        DonatEnchantment levitation = new DonatEnchantment("levitation")
                .setMaxLvl((int) enchantmentsCfg.get("Levitation.levitationMaxLvl"))
                .setPercentagePerLvl((double) enchantmentsCfg.get("Levitation.levitationPercentagePerLvl"))
                .setTime((int) enchantmentsCfg.get("Levitation.levitationTime"))
                .setTimePercentagePerLvl((double) enchantmentsCfg.get("Levitation.levitationTimePercentagePerLvl"));

        DonatEnchantment poisoning = new DonatEnchantment("poisoning")
                .setMaxLvl((int) enchantmentsCfg.get("Poisoning.poisoningMaxLvl"))
                .setPercentagePerLvl((double) enchantmentsCfg.get("Poisoning.poisoningPercentagePerLvl"))
                .setTime((int) enchantmentsCfg.get("Poisoning.poisoningTime"))
                .setTimePercentagePerLvl((double) enchantmentsCfg.get("Poisoning.poisoningTimePercentagePerLvl"));

        DonatEnchantment nausea = new DonatEnchantment("nausea")
                .setMaxLvl((int) enchantmentsCfg.get("Nausea.nauseaMaxLvl"))
                .setPercentagePerLvl((double) enchantmentsCfg.get("Nausea.nauseaPercentagePerLvl"))
                .setTime((int) enchantmentsCfg.get("Nausea.nauseaTime"))
                .setTimePercentagePerLvl((double) enchantmentsCfg.get("Nausea.nauseaTimePercentagePerLvl"));

        DonatEnchantment bowStun = new DonatEnchantment("bowStun")
                .setMaxLvl((int) enchantmentsCfg.get("BowStun.bowStunMaxLvl"))
                .setPercentagePerLvl((double) enchantmentsCfg.get("BowStun.bowStunPercentagePerLvl"))
                .setTime((int) enchantmentsCfg.get("BowStun.bowStunTime"))
                .setTimePercentagePerLvl((double) enchantmentsCfg.get("BowStun.bowStunTimePercentagePerLvl"));


        CacheFeudalValues.getDonatEnchantment().put(vampirism.getName(), vampirism);
        CacheFeudalValues.getDonatEnchantment().put(doubleDamage.getName(), doubleDamage);
        CacheFeudalValues.getDonatEnchantment().put(greed.getName(), greed);
        CacheFeudalValues.getDonatEnchantment().put(hook.getName(), hook);
        CacheFeudalValues.getDonatEnchantment().put(multi_shooting.getName(), multi_shooting);
        CacheFeudalValues.getDonatEnchantment().put(blindness.getName(), blindness);
        CacheFeudalValues.getDonatEnchantment().put(slowdown.getName(), slowdown);
        CacheFeudalValues.getDonatEnchantment().put(desiccation.getName(), desiccation);
        CacheFeudalValues.getDonatEnchantment().put(swordStun.getName(), swordStun);
        CacheFeudalValues.getDonatEnchantment().put(levitation.getName(), levitation);
        CacheFeudalValues.getDonatEnchantment().put(poisoning.getName(), poisoning);
        CacheFeudalValues.getDonatEnchantment().put(nausea.getName(), nausea);
        CacheFeudalValues.getDonatEnchantment().put(bowStun.getName(), bowStun);

    }

    @SneakyThrows
    public void saveEnchantmentsConfig() {

        File file = new File(path, "enchantments.yml");
        createEnchantmentsConfig();

        YamlConfiguration.loadConfiguration(file).save(file);

    }

    @SneakyThrows
    public void createEnchantmentsConfig() {

        if (!path.exists())
            path.mkdir();

        File file = new File(path, "enchantments.yml");

        if (!file.exists()) {

            file.createNewFile();

            enchantmentsCfg = YamlConfiguration.loadConfiguration(file);

            enchantmentsCfg.set("Vampirism.vampirismMaxLvl", 1);
            enchantmentsCfg.set("Vampirism.vampirismPercentagePerLvl", 1.0);

            enchantmentsCfg.set("DoubleDamage.doubleDamageMaxLvl", 1);
            enchantmentsCfg.set("DoubleDamage.doubleDamagePercentagePerLvl", 1.0);

            enchantmentsCfg.set("Blindness.blindnessMaxLvl", 1);
            enchantmentsCfg.set("Blindness.blindnessPercentagePerLvl", 1.0);
            enchantmentsCfg.set("Blindness.blindnessTime", 1);
            enchantmentsCfg.set("Blindness.blindnessTimePercentagePerLvl", 1.0);

            enchantmentsCfg.set("Slowdown.slowdownMaxLvl", 1);
            enchantmentsCfg.set("Slowdown.slowdownPercentagePerLvl", 1.0);
            enchantmentsCfg.set("Slowdown.slowdownTime", 1);
            enchantmentsCfg.set("Slowdown.slowdownTimePercentagePerLvl", 1.0);

            enchantmentsCfg.set("Desiccation.desiccationMaxLvl", 1);
            enchantmentsCfg.set("Desiccation.desiccationPercentagePerLvl", 1.0);
            enchantmentsCfg.set("Desiccation.slowdownTime", 1);
            enchantmentsCfg.set("Desiccation.slowdownTimePercentagePerLvl", 1.0);

            enchantmentsCfg.set("SwordStun.swordStunMaxLvl", 1);
            enchantmentsCfg.set("SwordStun.swordStunPercentagePerLvl", 1.0);
            enchantmentsCfg.set("SwordStun.swordStunTime", 1);
            enchantmentsCfg.set("SwordStun.swordStunTimePercentagePerLvl", 1.0);

            enchantmentsCfg.set("Levitation.levitationMaxLvl", 1);
            enchantmentsCfg.set("Levitation.levitationPercentagePerLvl", 1.0);
            enchantmentsCfg.set("Levitation.levitationTime", 1);
            enchantmentsCfg.set("Levitation.levitationTimePercentagePerLvl", 1.0);

            enchantmentsCfg.set("Poisoning.poisoningMaxLvl", 1);
            enchantmentsCfg.set("Poisoning.poisoningPercentagePerLvl", 1.0);
            enchantmentsCfg.set("Poisoning.poisoningTime", 1);
            enchantmentsCfg.set("Poisoning.poisoningTimePercentagePerLvl", 1.0);

            enchantmentsCfg.set("Nausea.nauseaMaxLvl", 1);
            enchantmentsCfg.set("Nausea.nauseaPercentagePerLvl", 1.0);
            enchantmentsCfg.set("Nausea.nauseaTime", 1);
            enchantmentsCfg.set("Nausea.nauseaTimePercentagePerLvl", 1.0);

            enchantmentsCfg.set("Hook.hookMaxLvl", 1);
            enchantmentsCfg.set("Hook.hookPercentagePerLvl", 1.0);

            enchantmentsCfg.set("Multi-shooting.multi_shootingMaxLvl", 1);
            enchantmentsCfg.set("Multi-shooting.multi_shootingPercentagePerLvl", 1.0);

            enchantmentsCfg.set("BowStun.bowStunMaxLvl", 1);
            enchantmentsCfg.set("BowStun.bowStunPercentagePerLvl", 1.0);
            enchantmentsCfg.set("BowStun.bowStunTime", 1);
            enchantmentsCfg.set("BowStun.bowStunTimePercentagePerLvl", 1.0);

            enchantmentsCfg.set("Greed.greedMaxLvl", 1);
            enchantmentsCfg.set("Greed.greedPercentagePerLvl", 1.0);

            enchantmentsCfg.save(file);

        }
    }

    public void readKingdomTaxConfig() {

        File file = new File(path, "kingdomTax.yml");
        createKingdomTaxConfig();

        kingdomTaxCfg = YamlConfiguration.loadConfiguration(file);

        CacheFeudalValues.setLandTax((int) kingdomTaxCfg.get("KingdomTax.land"));
        CacheFeudalValues.setTaxOnResidents((int) kingdomTaxCfg.get("KingdomTax.residents"));
        CacheFeudalValues.setTaxTreasuryPercent((double) kingdomTaxCfg.get("KingdomTax.treasury"));
        CacheFeudalValues.setTimeTaxCollection((int) kingdomTaxCfg.get("KingdomTax.time"));

    }

    @SneakyThrows
    public void saveKingdomTaxConfig() {

        File file = new File(path, "kingdomTax.yml");
        createKingdomTaxConfig();

        YamlConfiguration.loadConfiguration(file).save(file);

    }

    @SneakyThrows
    public void createKingdomTaxConfig() {

        if (!path.exists())
            path.mkdir();

        File file = new File(path, "kingdomTax.yml");
        if (!file.exists()) {

            file.createNewFile();

            kingdomTaxCfg = YamlConfiguration.loadConfiguration(file);

            kingdomTaxCfg.set("KingdomTax.land", 1500);
            kingdomTaxCfg.set("KingdomTax.residents", 300);
            kingdomTaxCfg.set("KingdomTax.treasury", 3.0);
            kingdomTaxCfg.set("KingdomTax.time", 6);

            kingdomTaxCfg.save(file);

        }

    }

    public void readMoneyForMobsConfig() {

        File file = new File(path, "moneyForMobs.yml");
        createMoneyForMobsConfig();

        moneyForMobsCfg = YamlConfiguration.loadConfiguration(file);

        CacheFeudalValues.getMoneyForMobs().put(EntityType.BAT, (int) moneyForMobsCfg.get("MoneyForMobs.bat"));
        CacheFeudalValues.getMoneyForMobs().put(EntityType.CHICKEN, (int) moneyForMobsCfg.get("MoneyForMobs.chicken"));
        CacheFeudalValues.getMoneyForMobs().put(EntityType.COW, (int) moneyForMobsCfg.get("MoneyForMobs.cow"));
        CacheFeudalValues.getMoneyForMobs().put(EntityType.MUSHROOM_COW, (int) moneyForMobsCfg.get("MoneyForMobs.mushroomCow"));
        CacheFeudalValues.getMoneyForMobs().put(EntityType.OCELOT, (int) moneyForMobsCfg.get("MoneyForMobs.ocelot"));
        CacheFeudalValues.getMoneyForMobs().put(EntityType.POLAR_BEAR, (int) moneyForMobsCfg.get("MoneyForMobs.polarBear"));
        CacheFeudalValues.getMoneyForMobs().put(EntityType.PIG, (int) moneyForMobsCfg.get("MoneyForMobs.pig"));
        CacheFeudalValues.getMoneyForMobs().put(EntityType.RABBIT, (int) moneyForMobsCfg.get("MoneyForMobs.rabbit"));
        CacheFeudalValues.getMoneyForMobs().put(EntityType.SNOWMAN, (int) moneyForMobsCfg.get("MoneyForMobs.snowMan"));
        CacheFeudalValues.getMoneyForMobs().put(EntityType.VILLAGER, (int) moneyForMobsCfg.get("MoneyForMobs.villager"));
        CacheFeudalValues.getMoneyForMobs().put(EntityType.CAVE_SPIDER, (int) moneyForMobsCfg.get("MoneyForMobs.caveSpider"));
        CacheFeudalValues.getMoneyForMobs().put(EntityType.ENDERMAN, (int) moneyForMobsCfg.get("MoneyForMobs.enderMan"));
        CacheFeudalValues.getMoneyForMobs().put(EntityType.IRON_GOLEM, (int) moneyForMobsCfg.get("MoneyForMobs.ironGolem"));
        CacheFeudalValues.getMoneyForMobs().put(EntityType.LLAMA, (int) moneyForMobsCfg.get("MoneyForMobs.llama"));
        CacheFeudalValues.getMoneyForMobs().put(EntityType.SPIDER, (int) moneyForMobsCfg.get("MoneyForMobs.spider"));
        CacheFeudalValues.getMoneyForMobs().put(EntityType.WOLF, (int) moneyForMobsCfg.get("MoneyForMobs.wolf"));
        CacheFeudalValues.getMoneyForMobs().put(EntityType.BLAZE, (int) moneyForMobsCfg.get("MoneyForMobs.blaze"));
        CacheFeudalValues.getMoneyForMobs().put(EntityType.CREEPER, (int) moneyForMobsCfg.get("MoneyForMobs.creeper"));
        CacheFeudalValues.getMoneyForMobs().put(EntityType.ELDER_GUARDIAN, (int) moneyForMobsCfg.get("MoneyForMobs.elderGuardian"));
        CacheFeudalValues.getMoneyForMobs().put(EntityType.ENDERMITE, (int) moneyForMobsCfg.get("MoneyForMobs.endermite"));
        CacheFeudalValues.getMoneyForMobs().put(EntityType.EVOKER, (int) moneyForMobsCfg.get("MoneyForMobs.evolker"));
        CacheFeudalValues.getMoneyForMobs().put(EntityType.GHAST, (int) moneyForMobsCfg.get("MoneyForMobs.ghast"));
        CacheFeudalValues.getMoneyForMobs().put(EntityType.GUARDIAN, (int) moneyForMobsCfg.get("MoneyForMobs.guardian"));
        CacheFeudalValues.getMoneyForMobs().put(EntityType.HUSK, (int) moneyForMobsCfg.get("MoneyForMobs.husk"));
        CacheFeudalValues.getMoneyForMobs().put(EntityType.MAGMA_CUBE, (int) moneyForMobsCfg.get("MoneyForMobs.magmaCube"));
        CacheFeudalValues.getMoneyForMobs().put(EntityType.SHULKER, (int) moneyForMobsCfg.get("MoneyForMobs.shulker"));
        CacheFeudalValues.getMoneyForMobs().put(EntityType.SILVERFISH, (int) moneyForMobsCfg.get("MoneyForMobs.silverfish"));
        CacheFeudalValues.getMoneyForMobs().put(EntityType.SKELETON, (int) moneyForMobsCfg.get("MoneyForMobs.skeleton"));
        CacheFeudalValues.getMoneyForMobs().put(EntityType.SLIME, (int) moneyForMobsCfg.get("MoneyForMobs.slime"));
        CacheFeudalValues.getMoneyForMobs().put(EntityType.STRAY, (int) moneyForMobsCfg.get("MoneyForMobs.stray"));
        CacheFeudalValues.getMoneyForMobs().put(EntityType.VEX, (int) moneyForMobsCfg.get("MoneyForMobs.vex"));
        CacheFeudalValues.getMoneyForMobs().put(EntityType.VINDICATOR, (int) moneyForMobsCfg.get("MoneyForMobs.vindicator"));
        CacheFeudalValues.getMoneyForMobs().put(EntityType.WITCH, (int) moneyForMobsCfg.get("MoneyForMobs.witch"));
        CacheFeudalValues.getMoneyForMobs().put(EntityType.WITHER_SKELETON, (int) moneyForMobsCfg.get("MoneyForMobs.witherSkeleton"));
        CacheFeudalValues.getMoneyForMobs().put(EntityType.ZOMBIE, (int) moneyForMobsCfg.get("MoneyForMobs.zombie"));
        CacheFeudalValues.getMoneyForMobs().put(EntityType.ZOMBIE_VILLAGER, (int) moneyForMobsCfg.get("MoneyForMobs.zombieVillager"));
        CacheFeudalValues.getMoneyForMobs().put(EntityType.ENDER_DRAGON, (int) moneyForMobsCfg.get("MoneyForMobs.enderDragon"));
        CacheFeudalValues.getMoneyForMobs().put(EntityType.WITHER, (int) moneyForMobsCfg.get("MoneyForMobs.Wither"));

    }

    @SneakyThrows
    public void saveMoneyForMobsConfig() {

        File file = new File(path, "moneyForMobs.yml");
        createMoneyForMobsConfig();

        YamlConfiguration.loadConfiguration(file).save(file);

    }

    @SneakyThrows
    public void createMoneyForMobsConfig() {

        if (!path.exists())
            path.mkdir();

        File file = new File(path, "moneyForMobs.yml");
        if (!file.exists()) {

            file.createNewFile();

            moneyForMobsCfg = YamlConfiguration.loadConfiguration(file);

            moneyForMobsCfg.set("MoneyForMobs.bat", 1);
            moneyForMobsCfg.set("MoneyForMobs.chicken", 3);
            moneyForMobsCfg.set("MoneyForMobs.cow", 5);
            moneyForMobsCfg.set("MoneyForMobs.mushroomCow", 5);
            moneyForMobsCfg.set("MoneyForMobs.horse", 7);
            moneyForMobsCfg.set("MoneyForMobs.ocelot", 5);
            moneyForMobsCfg.set("MoneyForMobs.parrot", 5);
            moneyForMobsCfg.set("MoneyForMobs.polarBear", 8);
            moneyForMobsCfg.set("MoneyForMobs.pig", 5);
            moneyForMobsCfg.set("MoneyForMobs.rabbit", 7);
            moneyForMobsCfg.set("MoneyForMobs.sheep", 5);
            moneyForMobsCfg.set("MoneyForMobs.snowMan", 1);
            moneyForMobsCfg.set("MoneyForMobs.squiq", 3);
            moneyForMobsCfg.set("MoneyForMobs.villager", 10);
            moneyForMobsCfg.set("MoneyForMobs.caveSpider", 20);
            moneyForMobsCfg.set("MoneyForMobs.enderMan", 27);
            moneyForMobsCfg.set("MoneyForMobs.ironGolem", 45);
            moneyForMobsCfg.set("MoneyForMobs.llama", 7);
            moneyForMobsCfg.set("MoneyForMobs.spider", 17);
            moneyForMobsCfg.set("MoneyForMobs.wolf", 15);
            moneyForMobsCfg.set("MoneyForMobs.blaze", 20);
            moneyForMobsCfg.set("MoneyForMobs.creeper", 23);
            moneyForMobsCfg.set("MoneyForMobs.elderGuardian", 5000);
            moneyForMobsCfg.set("MoneyForMobs.endermite", 1);
            moneyForMobsCfg.set("MoneyForMobs.evolker", 17);
            moneyForMobsCfg.set("MoneyForMobs.ghast", 15);
            moneyForMobsCfg.set("MoneyForMobs.guardian", 30);
            moneyForMobsCfg.set("MoneyForMobs.husk", 17);
            moneyForMobsCfg.set("MoneyForMobs.magmaCube", 23);
            moneyForMobsCfg.set("MoneyForMobs.shulker", 27);
            moneyForMobsCfg.set("MoneyForMobs.silverfish", 1);
            moneyForMobsCfg.set("MoneyForMobs.skeleton", 17);
            moneyForMobsCfg.set("MoneyForMobs.slime", 23);
            moneyForMobsCfg.set("MoneyForMobs.stray", 20);
            moneyForMobsCfg.set("MoneyForMobs.vex", 5);
            moneyForMobsCfg.set("MoneyForMobs.vindicator", 23);
            moneyForMobsCfg.set("MoneyForMobs.witch", 13);
            moneyForMobsCfg.set("MoneyForMobs.witherSkeleton", 30);
            moneyForMobsCfg.set("MoneyForMobs.zombie", 15);
            moneyForMobsCfg.set("MoneyForMobs.zombieVillager", 15);
            moneyForMobsCfg.set("MoneyForMobs.enderDragon", 10000);
            moneyForMobsCfg.set("MoneyForMobs.wither", 7000);

            moneyForMobsCfg.save(file);

        }

    }

    public void readAttributesConfig() {

        File file = new File(path, "attributes.yml");
        createAttributesConfig();

        attributesCfg = YamlConfiguration.loadConfiguration(file);

        CacheFeudalValues.setStrengthPercentageCost((double) attributesCfg.get("Attributes.strength"));
        CacheFeudalValues.setStaminaPercentageCost((double) attributesCfg.get("Attributes.stamina"));
        CacheFeudalValues.setLuckPercentageCost((double) attributesCfg.get("Attributes.luck"));
        CacheFeudalValues.setSurvivabilityPercentageCost((double) attributesCfg.get("Attributes.survivability"));
        CacheFeudalValues.setSpeedPercentageCost((double) attributesCfg.get("Attributes.speed"));

    }


    @SneakyThrows
    public void saveAttributesConfig() {

        File file = new File(path, "attributes.yml");
        createKingdomTaxConfig();

        YamlConfiguration.loadConfiguration(file).save(file);

    }

    @SneakyThrows
    public void createAttributesConfig() {

        if (!path.exists())
            path.mkdir();

        File file = new File(path, "attributes.yml");
        if (!file.exists()) {

            file.createNewFile();

            attributesCfg = YamlConfiguration.loadConfiguration(file);

            attributesCfg.set("Attributes.strength", 5.0);
            attributesCfg.set("Attributes.stamina", 5.0);
            attributesCfg.set("Attributes.luck", 7.0);
            attributesCfg.set("Attributes.survivability", 5.0);
            attributesCfg.set("Attributes.speed", 5.0);

            attributesCfg.save(file);

        }

    }

    public void readGeneralConfig() {

        File file = new File(path, "general.yml");
        createGeneralConfig();

        generalCfg = YamlConfiguration.loadConfiguration(file);

        CacheFeudalValues.setTimeRestart((int) generalCfg.get("Time.restart"));
        CacheFeudalValues.setTimeClearMail((int) generalCfg.get("Time.clearMail"));

    }


    @SneakyThrows
    public void saveGeneralConfig() {

        File file = new File(path, "general.yml");
        createKingdomTaxConfig();

        YamlConfiguration.loadConfiguration(file).save(file);

    }

    @SneakyThrows
    public void createGeneralConfig() {

        if (!path.exists())
            path.mkdir();

        File file = new File(path, "general.yml");
        if (!file.exists()) {

            file.createNewFile();

            generalCfg = YamlConfiguration.loadConfiguration(file);

            generalCfg.set("Time.restart", 8);
            generalCfg.set("Time.clearMail", 72);

            generalCfg.save(file);

        }

    }

}
