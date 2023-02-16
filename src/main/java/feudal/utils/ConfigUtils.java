package feudal.utils;

import com.mongodb.client.MongoClients;
import feudal.Feudal;
import lombok.SneakyThrows;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NonNls;

import java.io.File;

public class ConfigUtils {
    private static final FeudalValuesUtils feudalValuesUtils = new FeudalValuesUtils();
    private static final File path = Feudal.getPlugin().getDataFolder();
    private static @NonNls FileConfiguration databaseCfg;
    private static @NonNls FileConfiguration enchantmentsCfg;
    private static @NonNls FileConfiguration kingdomTaxCfg;

    public static void readDatabaseConfig() {

        File file = new File(path, "database.yml");
        createDatabaseConfig();

        databaseCfg = YamlConfiguration.loadConfiguration(file);

        feudalValuesUtils.setMongoClient(MongoClients.create((String) databaseCfg.get("Mongo.address")));
        feudalValuesUtils.setDatabase(feudalValuesUtils.getMongoClient().getDatabase((String) databaseCfg.get("Mongo.name")));
        feudalValuesUtils.setPlayersCollection(feudalValuesUtils.getDatabase().getCollection((String) databaseCfg.get("Mongo.playersCollection")));
        feudalValuesUtils.setKingdomsCollection(feudalValuesUtils.getDatabase().getCollection((String) databaseCfg.get("Mongo.kingdomsCollection")));

    }

    @SneakyThrows
    public static void saveDatabaseConfig() {

        File file = new File(path, "database.yml");
        createDatabaseConfig();

        YamlConfiguration.loadConfiguration(file).save(file);

    }

    @SneakyThrows
    public static void createDatabaseConfig() {

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

    public static void readEnchantmentsConfig() {

        File file = new File(path, "enchantments.yml");
        createEnchantmentsConfig();

        enchantmentsCfg = YamlConfiguration.loadConfiguration(file);

        feudalValuesUtils.setVampirismMaxLvl((int) enchantmentsCfg.get("Vampirism.vampirismMaxLvl"));
        feudalValuesUtils.setVampirismPercentagePerLvl((double) enchantmentsCfg.get("Vampirism.vampirismPercentagePerLvl"));

        feudalValuesUtils.setDoubleDamageMaxLvl((int) enchantmentsCfg.get("DoubleDamage.doubleDamageMaxLvl"));
        feudalValuesUtils.setDoubleDamagePercentagePerLvl((double) enchantmentsCfg.get("DoubleDamage.doubleDamagePercentagePerLvl"));

        feudalValuesUtils.setBlindnessMaxLvl((int) enchantmentsCfg.get("Blindness.blindnessMaxLvl"));
        feudalValuesUtils.setBlindnessPercentagePerLvl((double) enchantmentsCfg.get("Blindness.blindnessPercentagePerLvl"));
        feudalValuesUtils.setBlindnessTime((int) enchantmentsCfg.get("Blindness.blindnessTime"));
        feudalValuesUtils.setBlindnessTimePercentagePerLvl((double) enchantmentsCfg.get("Blindness.blindnessTimePercentagePerLvl"));

        feudalValuesUtils.setSlowdownMaxLvl((int) enchantmentsCfg.get("Slowdown.slowdownMaxLvl"));
        feudalValuesUtils.setSlowdownPercentagePerLvl((double) enchantmentsCfg.get("Slowdown.slowdownPercentagePerLvl"));
        feudalValuesUtils.setSlowdownTime((int) enchantmentsCfg.get("Slowdown.slowdownTime"));
        feudalValuesUtils.setSlowdownTimePercentagePerLvl((double) enchantmentsCfg.get("Slowdown.slowdownTimePercentagePerLvl"));

        feudalValuesUtils.setDesiccationMaxLvl((int) enchantmentsCfg.get("Desiccation.desiccationMaxLvl"));
        feudalValuesUtils.setDesiccationPercentagePerLvl((double) enchantmentsCfg.get("Desiccation.desiccationPercentagePerLvl"));
        feudalValuesUtils.setDesiccationTime((int) enchantmentsCfg.get("Desiccation.slowdownTime"));
        feudalValuesUtils.setDesiccationTimePercentagePerLvl((double) enchantmentsCfg.get("Desiccation.slowdownTimePercentagePerLvl"));

        feudalValuesUtils.setSwordStunMaxLvl((int) enchantmentsCfg.get("SwordStun.swordStunMaxLvl"));
        feudalValuesUtils.setSwordStunPercentagePerLvl((double) enchantmentsCfg.get("SwordStun.swordStunPercentagePerLvl"));
        feudalValuesUtils.setSwordStunTime((int) enchantmentsCfg.get("SwordStun.swordStunTime"));
        feudalValuesUtils.setSwordStunTimePercentagePerLvl((double) enchantmentsCfg.get("SwordStun.swordStunTimePercentagePerLvl"));

        feudalValuesUtils.setLevitationMaxLvl((int) enchantmentsCfg.get("Levitation.levitationMaxLvl"));
        feudalValuesUtils.setLevitationPercentagePerLvl((double) enchantmentsCfg.get("Levitation.levitationPercentagePerLvl"));
        feudalValuesUtils.setLevitationTime((int) enchantmentsCfg.get("Levitation.levitationTime"));
        feudalValuesUtils.setLevitationTimePercentagePerLvl((double) enchantmentsCfg.get("Levitation.levitationTimePercentagePerLvl"));

        feudalValuesUtils.setPoisoningMaxLvl((int) enchantmentsCfg.get("Poisoning.poisoningMaxLvl"));
        feudalValuesUtils.setPoisoningPercentagePerLvl((double) enchantmentsCfg.get("Poisoning.poisoningPercentagePerLvl"));
        feudalValuesUtils.setPoisoningTime((int) enchantmentsCfg.get("Poisoning.poisoningTime"));
        feudalValuesUtils.setPoisoningTimePercentagePerLvl((double) enchantmentsCfg.get("Poisoning.poisoningTimePercentagePerLvl"));

        feudalValuesUtils.setNauseaMaxLvl((int) enchantmentsCfg.get("Nausea.nauseaMaxLvl"));
        feudalValuesUtils.setNauseaTimePercentagePerLvl((double) enchantmentsCfg.get("Nausea.nauseaPercentagePerLvl"));
        feudalValuesUtils.setNauseaTime((int) enchantmentsCfg.get("Nausea.nauseaTime"));
        feudalValuesUtils.setNauseaTimePercentagePerLvl((double) enchantmentsCfg.get("Nausea.nauseaTimePercentagePerLvl"));

        feudalValuesUtils.setHookMaxLvl((int) enchantmentsCfg.get("Hook.hookMaxLvl"));
        feudalValuesUtils.setHookPercentagePerLvl((double) enchantmentsCfg.get("Hook.hookPercentagePerLvl"));

        feudalValuesUtils.setMulti_shootingMaxLvl((int) enchantmentsCfg.get("Multi-shooting.multi_shootingMaxLvl"));
        feudalValuesUtils.setMulti_shootingPercentagePerLvl((double) enchantmentsCfg.get("Multi-shooting.multi_shootingPercentagePerLvl"));

        feudalValuesUtils.setBowStunMaxLvl((int) enchantmentsCfg.get("BowStun.bowStunMaxLvl"));
        feudalValuesUtils.setBowStunPercentagePerLvl((double) enchantmentsCfg.get("BowStun.bowStunPercentagePerLvl"));

        feudalValuesUtils.setGreedMaxLvl((int) enchantmentsCfg.get("Greed.greedMaxLvl"));
        feudalValuesUtils.setGreedPercentagePerLvl((double) enchantmentsCfg.get("Greed.greedPercentagePerLvl"));

    }

    @SneakyThrows
    public static void saveEnchantmentsConfig() {

        File file = new File(path, "enchantments.yml");
        createEnchantmentsConfig();

        YamlConfiguration.loadConfiguration(file).save(file);

    }

    @SneakyThrows
    public static void createEnchantmentsConfig() {

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

            enchantmentsCfg.set("Greed.greedMaxLvl", 1);
            enchantmentsCfg.set("Greed.greedPercentagePerLvl", 1.0);

            enchantmentsCfg.save(file);

        }
    }

    public static void readKingdomTaxConfig() {

        File file = new File(path, "kingdomTax.yml");
        createKingdomTaxConfig();

        kingdomTaxCfg = YamlConfiguration.loadConfiguration(file);

        feudalValuesUtils.setLandTax((int) kingdomTaxCfg.get("KingdomTax.land"));
        feudalValuesUtils.setTaxOnResidents((int) kingdomTaxCfg.get("KingdomTax.residents"));
        feudalValuesUtils.setTaxTreasuryPercent((int) kingdomTaxCfg.get("KingdomTax.treasury"));
        feudalValuesUtils.setTimeTaxCollection((int) kingdomTaxCfg.get("KingdomTax.time"));

    }

    @SneakyThrows
    public static void saveKingdomTaxConfig() {

        File file = new File(path, "kingdomTax.yml");
        createKingdomTaxConfig();

        YamlConfiguration.loadConfiguration(file).save(file);

    }

    @SneakyThrows
    public static void createKingdomTaxConfig() {

        if (!path.exists())
            path.mkdir();

        File file = new File(path, "kingdomTax.yml");
        if (!file.exists()) {

            file.createNewFile();

            databaseCfg = YamlConfiguration.loadConfiguration(file);

            kingdomTaxCfg.set("KingdomTax.land", 1500);
            kingdomTaxCfg.set("KingdomTax.residents", 300);
            kingdomTaxCfg.set("KingdomTax.treasury", 3);
            kingdomTaxCfg.set("KingdomTax.time", 432000);

            databaseCfg.save(file);

        }

    }
}
