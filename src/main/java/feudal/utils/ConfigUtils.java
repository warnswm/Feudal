package feudal.utils;

import com.mongodb.client.MongoClients;
import feudal.Feudal;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
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

        FeudalValuesUtils.setMongoClient(MongoClients.create((String) databaseCfg.get("Mongo.address")));
        FeudalValuesUtils.setDatabase(FeudalValuesUtils.getMongoClient().getDatabase((String) databaseCfg.get("Mongo.name")));
        FeudalValuesUtils.setPlayersCollection(FeudalValuesUtils.getDatabase().getCollection((String) databaseCfg.get("Mongo.playersCollection")));
        FeudalValuesUtils.setKingdomsCollection(FeudalValuesUtils.getDatabase().getCollection((String) databaseCfg.get("Mongo.kingdomsCollection")));

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

        FeudalValuesUtils.setVampirismMaxLvl((int) enchantmentsCfg.get("Vampirism.vampirismMaxLvl"));
        FeudalValuesUtils.setVampirismPercentagePerLvl((double) enchantmentsCfg.get("Vampirism.vampirismPercentagePerLvl"));

        FeudalValuesUtils.setDoubleDamageMaxLvl((int) enchantmentsCfg.get("DoubleDamage.doubleDamageMaxLvl"));
        FeudalValuesUtils.setDoubleDamagePercentagePerLvl((double) enchantmentsCfg.get("DoubleDamage.doubleDamagePercentagePerLvl"));

        FeudalValuesUtils.setBlindnessMaxLvl((int) enchantmentsCfg.get("Blindness.blindnessMaxLvl"));
        FeudalValuesUtils.setBlindnessPercentagePerLvl((double) enchantmentsCfg.get("Blindness.blindnessPercentagePerLvl"));
        FeudalValuesUtils.setBlindnessTime((int) enchantmentsCfg.get("Blindness.blindnessTime"));
        FeudalValuesUtils.setBlindnessTimePercentagePerLvl((double) enchantmentsCfg.get("Blindness.blindnessTimePercentagePerLvl"));

        FeudalValuesUtils.setSlowdownMaxLvl((int) enchantmentsCfg.get("Slowdown.slowdownMaxLvl"));
        FeudalValuesUtils.setSlowdownPercentagePerLvl((double) enchantmentsCfg.get("Slowdown.slowdownPercentagePerLvl"));
        FeudalValuesUtils.setSlowdownTime((int) enchantmentsCfg.get("Slowdown.slowdownTime"));
        FeudalValuesUtils.setSlowdownTimePercentagePerLvl((double) enchantmentsCfg.get("Slowdown.slowdownTimePercentagePerLvl"));

        FeudalValuesUtils.setDesiccationMaxLvl((int) enchantmentsCfg.get("Desiccation.desiccationMaxLvl"));
        FeudalValuesUtils.setDesiccationPercentagePerLvl((double) enchantmentsCfg.get("Desiccation.desiccationPercentagePerLvl"));
        FeudalValuesUtils.setDesiccationTime((int) enchantmentsCfg.get("Desiccation.slowdownTime"));
        FeudalValuesUtils.setDesiccationTimePercentagePerLvl((double) enchantmentsCfg.get("Desiccation.slowdownTimePercentagePerLvl"));

        FeudalValuesUtils.setSwordStunMaxLvl((int) enchantmentsCfg.get("SwordStun.swordStunMaxLvl"));
        FeudalValuesUtils.setSwordStunPercentagePerLvl((double) enchantmentsCfg.get("SwordStun.swordStunPercentagePerLvl"));
        FeudalValuesUtils.setSwordStunTime((int) enchantmentsCfg.get("SwordStun.swordStunTime"));
        FeudalValuesUtils.setSwordStunTimePercentagePerLvl((double) enchantmentsCfg.get("SwordStun.swordStunTimePercentagePerLvl"));

        FeudalValuesUtils.setLevitationMaxLvl((int) enchantmentsCfg.get("Levitation.levitationMaxLvl"));
        FeudalValuesUtils.setLevitationPercentagePerLvl((double) enchantmentsCfg.get("Levitation.levitationPercentagePerLvl"));
        FeudalValuesUtils.setLevitationTime((int) enchantmentsCfg.get("Levitation.levitationTime"));
        FeudalValuesUtils.setLevitationTimePercentagePerLvl((double) enchantmentsCfg.get("Levitation.levitationTimePercentagePerLvl"));

        FeudalValuesUtils.setPoisoningMaxLvl((int) enchantmentsCfg.get("Poisoning.poisoningMaxLvl"));
        FeudalValuesUtils.setPoisoningPercentagePerLvl((double) enchantmentsCfg.get("Poisoning.poisoningPercentagePerLvl"));
        FeudalValuesUtils.setPoisoningTime((int) enchantmentsCfg.get("Poisoning.poisoningTime"));
        FeudalValuesUtils.setPoisoningTimePercentagePerLvl((double) enchantmentsCfg.get("Poisoning.poisoningTimePercentagePerLvl"));

        FeudalValuesUtils.setNauseaMaxLvl((int) enchantmentsCfg.get("Nausea.nauseaMaxLvl"));
        FeudalValuesUtils.setNauseaTimePercentagePerLvl((double) enchantmentsCfg.get("Nausea.nauseaPercentagePerLvl"));
        FeudalValuesUtils.setNauseaTime((int) enchantmentsCfg.get("Nausea.nauseaTime"));
        FeudalValuesUtils.setNauseaTimePercentagePerLvl((double) enchantmentsCfg.get("Nausea.nauseaTimePercentagePerLvl"));

        FeudalValuesUtils.setHookMaxLvl((int) enchantmentsCfg.get("Hook.hookMaxLvl"));
        FeudalValuesUtils.setHookPercentagePerLvl((double) enchantmentsCfg.get("Hook.hookPercentagePerLvl"));

        FeudalValuesUtils.setMulti_shootingMaxLvl((int) enchantmentsCfg.get("Multi-shooting.multi_shootingMaxLvl"));
        FeudalValuesUtils.setMulti_shootingPercentagePerLvl((double) enchantmentsCfg.get("Multi-shooting.multi_shootingPercentagePerLvl"));

        FeudalValuesUtils.setBowStunMaxLvl((int) enchantmentsCfg.get("BowStun.bowStunMaxLvl"));
        FeudalValuesUtils.setBowStunPercentagePerLvl((double) enchantmentsCfg.get("BowStun.bowStunPercentagePerLvl"));
        FeudalValuesUtils.setBowStunTime((int) enchantmentsCfg.get("BowStun.bowStunTime"));
        FeudalValuesUtils.setBowStunPercentagePerLvl((double) enchantmentsCfg.get("BowStun.bowStunTimePercentagePerLvl"));

        FeudalValuesUtils.setGreedMaxLvl((int) enchantmentsCfg.get("Greed.greedMaxLvl"));
        FeudalValuesUtils.setGreedPercentagePerLvl((double) enchantmentsCfg.get("Greed.greedPercentagePerLvl"));

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

        FeudalValuesUtils.setLandTax((int) kingdomTaxCfg.get("KingdomTax.land"));
        FeudalValuesUtils.setTaxOnResidents((int) kingdomTaxCfg.get("KingdomTax.residents"));
        FeudalValuesUtils.setTaxTreasuryPercent((double) kingdomTaxCfg.get("KingdomTax.treasury"));
        FeudalValuesUtils.setTimeTaxCollection((int) kingdomTaxCfg.get("KingdomTax.time"));

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

        FeudalValuesUtils.setMoneyForBat((int) moneyForMobsCfg.get("MoneyForMobs.bat"));
        FeudalValuesUtils.setMoneyForChicken((int) moneyForMobsCfg.get("MoneyForMobs.chicken"));
        FeudalValuesUtils.setMoneyForCow((int) moneyForMobsCfg.get("MoneyForMobs.cow"));
        FeudalValuesUtils.setMoneyForMushroomCow((int) moneyForMobsCfg.get("MoneyForMobs.mushroomCow"));
        FeudalValuesUtils.setMoneyForHorse((int) moneyForMobsCfg.get("MoneyForMobs.horse"));
        FeudalValuesUtils.setMoneyForOcelot((int) moneyForMobsCfg.get("MoneyForMobs.ocelot"));
        FeudalValuesUtils.setMoneyForParrot((int) moneyForMobsCfg.get("MoneyForMobs.parrot"));
        FeudalValuesUtils.setMoneyForPolarBear((int) moneyForMobsCfg.get("MoneyForMobs.polarBear"));
        FeudalValuesUtils.setMoneyForPig((int) moneyForMobsCfg.get("MoneyForMobs.pig"));
        FeudalValuesUtils.setMoneyForRabbit((int) moneyForMobsCfg.get("MoneyForMobs.rabbit"));
        FeudalValuesUtils.setMoneyForSheep((int) moneyForMobsCfg.get("MoneyForMobs.sheep"));
        FeudalValuesUtils.setMoneyForSnowMan((int) moneyForMobsCfg.get("MoneyForMobs.snowMan"));
        FeudalValuesUtils.setMoneyForSquiq((int) moneyForMobsCfg.get("MoneyForMobs.squiq"));
        FeudalValuesUtils.setMoneyForVillager((int) moneyForMobsCfg.get("MoneyForMobs.villager"));
        FeudalValuesUtils.setMoneyForCaveSpider((int) moneyForMobsCfg.get("MoneyForMobs.caveSpider"));
        FeudalValuesUtils.setMoneyForEnderman((int) moneyForMobsCfg.get("MoneyForMobs.enderMan"));
        FeudalValuesUtils.setMoneyForIronGolem((int) moneyForMobsCfg.get("MoneyForMobs.ironGolem"));
        FeudalValuesUtils.setMoneyForLlama((int) moneyForMobsCfg.get("MoneyForMobs.llama"));
        FeudalValuesUtils.setMoneyForSpider((int) moneyForMobsCfg.get("MoneyForMobs.spider"));
        FeudalValuesUtils.setMoneyForWolf((int) moneyForMobsCfg.get("MoneyForMobs.wolf"));
        FeudalValuesUtils.setMoneyForBlaze((int) moneyForMobsCfg.get("MoneyForMobs.blaze"));
        FeudalValuesUtils.setMoneyForCreeper((int) moneyForMobsCfg.get("MoneyForMobs.creeper"));
        FeudalValuesUtils.setMoneyForElderGuardian((int) moneyForMobsCfg.get("MoneyForMobs.elderGuardian"));
        FeudalValuesUtils.setMoneyForEndermite((int) moneyForMobsCfg.get("MoneyForMobs.endermite"));
        FeudalValuesUtils.setMoneyForEvolker((int) moneyForMobsCfg.get("MoneyForMobs.evolker"));
        FeudalValuesUtils.setMoneyForGhast((int) moneyForMobsCfg.get("MoneyForMobs.ghast"));
        FeudalValuesUtils.setMoneyForGuardian((int) moneyForMobsCfg.get("MoneyForMobs.guardian"));
        FeudalValuesUtils.setMoneyForHask((int) moneyForMobsCfg.get("MoneyForMobs.hask"));
        FeudalValuesUtils.setMoneyForMagmaCube((int) moneyForMobsCfg.get("MoneyForMobs.magmaCube"));
        FeudalValuesUtils.setMoneyForShulker((int) moneyForMobsCfg.get("MoneyForMobs.shulker"));
        FeudalValuesUtils.setMoneyForSilverfish((int) moneyForMobsCfg.get("MoneyForMobs.silverfish"));
        FeudalValuesUtils.setMoneyForSkeleton((int) moneyForMobsCfg.get("MoneyForMobs.skeleton"));
        FeudalValuesUtils.setMoneyForSlime((int) moneyForMobsCfg.get("MoneyForMobs.slime"));
        FeudalValuesUtils.setMoneyForStray((int) moneyForMobsCfg.get("MoneyForMobs.stray"));
        FeudalValuesUtils.setMoneyForVex((int) moneyForMobsCfg.get("MoneyForMobs.vex"));
        FeudalValuesUtils.setMoneyForVindicator((int) moneyForMobsCfg.get("MoneyForMobs.vindicator"));
        FeudalValuesUtils.setMoneyForWitch((int) moneyForMobsCfg.get("MoneyForMobs.witch"));
        FeudalValuesUtils.setMoneyForWitherSkeleton((int) moneyForMobsCfg.get("MoneyForMobs.witherSkeleton"));
        FeudalValuesUtils.setMoneyForZombie((int) moneyForMobsCfg.get("MoneyForMobs.zombie"));
        FeudalValuesUtils.setMoneyForZombieVillager((int) moneyForMobsCfg.get("MoneyForMobs.zombieVillager"));
        FeudalValuesUtils.setMoneyForEnderDragon((int) moneyForMobsCfg.get("MoneyForMobs.enderDragon"));
        FeudalValuesUtils.setMoneyForWither((int) moneyForMobsCfg.get("MoneyForMobs.Wither"));

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
            moneyForMobsCfg.set("MoneyForMobs.hask", 17);
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
            moneyForMobsCfg.set("MoneyForMobs.Wither", 7000);

            moneyForMobsCfg.save(file);

        }

    }

    public void readAttributesConfig() {

        File file = new File(path, "attributes.yml");
        createAttributesConfig();

        attributesCfg = YamlConfiguration.loadConfiguration(file);

        FeudalValuesUtils.setStrengthPercentageCost((double) attributesCfg.get("Attributes.strength"));
        FeudalValuesUtils.setStaminaPercentageCost((double) attributesCfg.get("Attributes.stamina"));
        FeudalValuesUtils.setLuckPercentageCost((double) attributesCfg.get("Attributes.luck"));
        FeudalValuesUtils.setSurvivabilityPercentageCost((double) attributesCfg.get("Attributes.survivability"));
        FeudalValuesUtils.setSpeedPercentageCost((double) attributesCfg.get("Attributes.speed"));

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

        FeudalValuesUtils.setTimeRestart((int) generalCfg.get("Time.restart"));
        FeudalValuesUtils.setTimeClearMail((int) generalCfg.get("Time.clearMail"));

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
