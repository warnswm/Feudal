package feudal.utils;

import com.mongodb.client.MongoClients;
import feudal.Feudal;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NonNls;

import java.io.File;
import java.io.IOException;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
enum ConfigUtils {
    ;

    public static final String ENCHANTMENTS_YML = "enchantments.yml";
    static File path = Feudal.getPlugin().getDataFolder();
    static @NonNls FileConfiguration databaseConfiguration;

    public static void readDatabaseConfig() {

        File file = new File(path, "database.yml");
        checkDatabaseConfig();

        databaseConfiguration = YamlConfiguration.loadConfiguration(file);

        FeudalValuesUtils.mongoClient = MongoClients.create((String) databaseConfiguration.get("Mongo.address"));
        FeudalValuesUtils.database = FeudalValuesUtils.mongoClient.getDatabase((String) databaseConfiguration.get("Mongo.name"));
        FeudalValuesUtils.playersCollection = FeudalValuesUtils.database.getCollection((String) databaseConfiguration.get("Mongo.playersCollection"));
        FeudalValuesUtils.kingdomsCollection = FeudalValuesUtils.database.getCollection((String) databaseConfiguration.get("Mongo.kingdomsCollection"));

    }

    public static void saveDatabaseConfig() {

        File file = new File(path, "database.yml");
        checkDatabaseConfig();

        try {

            YamlConfiguration.loadConfiguration(file).save(file);

        } catch (IOException e) {

            throw new RuntimeException(e);

        }

    }

    public static void checkDatabaseConfig() {

        if (!path.exists())
            path.mkdir();

        File file = new File(path, "database.yml");

        if (!file.exists())

            try {

                file.createNewFile();

                databaseConfiguration = YamlConfiguration.loadConfiguration(file);

                databaseConfiguration.set("Mongo.address", "mongodb://localhost:27017");
                databaseConfiguration.set("Mongo.name", "local");
                databaseConfiguration.set("Mongo.playersCollection", "players");
                databaseConfiguration.set("Mongo.kingdomsCollection", "kingdoms");

                databaseConfiguration.save(file);

            } catch (IOException e) {

                throw new RuntimeException(e);

            }
    }

    public static void readEnchantmentsConfig() {

        File file = new File(path, ENCHANTMENTS_YML);
        checkEnchantmentsConfig();

        databaseConfiguration = YamlConfiguration.loadConfiguration(file);

        FeudalValuesUtils.vampirismMaxLvl = (int) databaseConfiguration.get("Vampirism.vampirismMaxLvl");
        FeudalValuesUtils.vampirismPercentagePerLvl = (double) databaseConfiguration.get("Vampirism.vampirismPercentagePerLvl");

        FeudalValuesUtils.doubleDamageMaxLvl = (int) databaseConfiguration.get("DoubleDamage.doubleDamageMaxLvl");
        FeudalValuesUtils.doubleDamagePercentagePerLvl = (double) databaseConfiguration.get("DoubleDamage.doubleDamagePercentagePerLvl");

        FeudalValuesUtils.blindnessMaxLvl = (int) databaseConfiguration.get("Blindness.blindnessMaxLvl");
        FeudalValuesUtils.blindnessPercentagePerLvl = (double) databaseConfiguration.get("Blindness.blindnessPercentagePerLvl");
        FeudalValuesUtils.blindnessTime = (int) databaseConfiguration.get("Blindness.blindnessTime");
        FeudalValuesUtils.blindnessTimePercentagePerLvl = (double) databaseConfiguration.get("Blindness.blindnessTimePercentagePerLvl");

        FeudalValuesUtils.slowdownMaxLvl = (int) databaseConfiguration.get("Slowdown.slowdownMaxLvl");
        FeudalValuesUtils.slowdownPercentagePerLvl = (double) databaseConfiguration.get("Slowdown.slowdownPercentagePerLvl");
        FeudalValuesUtils.slowdownTime = (int) databaseConfiguration.get("Slowdown.slowdownTime");
        FeudalValuesUtils.slowdownTimePercentagePerLvl = (double) databaseConfiguration.get("Slowdown.slowdownTimePercentagePerLvl");

        FeudalValuesUtils.desiccationMaxLvl = (int) databaseConfiguration.get("Desiccation.desiccationMaxLvl");
        FeudalValuesUtils.desiccationPercentagePerLvl = (double) databaseConfiguration.get("Desiccation.desiccationPercentagePerLvl");
        FeudalValuesUtils.desiccationTime = (int) databaseConfiguration.get("Desiccation.slowdownTime");
        FeudalValuesUtils.desiccationTimePercentagePerLvl = (double) databaseConfiguration.get("Desiccation.slowdownTimePercentagePerLvl");

        FeudalValuesUtils.swordStunMaxLvl = (int) databaseConfiguration.get("SwordStun.swordStunMaxLvl");
        FeudalValuesUtils.swordStunPercentagePerLvl = (double) databaseConfiguration.get("SwordStun.swordStunPercentagePerLvl");
        FeudalValuesUtils.swordStunTime = (int) databaseConfiguration.get("SwordStun.swordStunTime");
        FeudalValuesUtils.swordStunTimePercentagePerLvl = (double) databaseConfiguration.get("SwordStun.swordStunTimePercentagePerLvl");

        FeudalValuesUtils.levitationMaxLvl = (int) databaseConfiguration.get("Levitation.levitationMaxLvl");
        FeudalValuesUtils.levitationPercentagePerLvl = (double) databaseConfiguration.get("Levitation.levitationPercentagePerLvl");
        FeudalValuesUtils.levitationTime = (int) databaseConfiguration.get("Levitation.levitationTime");
        FeudalValuesUtils.levitationTimePercentagePerLvl = (double) databaseConfiguration.get("Levitation.levitationTimePercentagePerLvl");

        FeudalValuesUtils.poisoningMaxLvl = (int) databaseConfiguration.get("Poisoning.poisoningMaxLvl");
        FeudalValuesUtils.poisoningPercentagePerLvl = (double) databaseConfiguration.get("Poisoning.poisoningPercentagePerLvl");
        FeudalValuesUtils.poisoningTime = (int) databaseConfiguration.get("Poisoning.poisoningTime");
        FeudalValuesUtils.poisoningTimePercentagePerLvl = (double) databaseConfiguration.get("Poisoning.poisoningTimePercentagePerLvl");

        FeudalValuesUtils.nauseaMaxLvl = (int) databaseConfiguration.get("Nausea.nauseaMaxLvl");
        FeudalValuesUtils.nauseaPercentagePerLvl = (double) databaseConfiguration.get("Nausea.nauseaPercentagePerLvl");
        FeudalValuesUtils.nauseaTime = (int) databaseConfiguration.get("Nausea.nauseaTime");
        FeudalValuesUtils.nauseaTimePercentagePerLvl = (double) databaseConfiguration.get("Nausea.nauseaTimePercentagePerLvl");

        FeudalValuesUtils.hookMaxLvl = (int) databaseConfiguration.get("Hook.hookMaxLvl");
        FeudalValuesUtils.hookPercentagePerLvl = (double) databaseConfiguration.get("Hook.hookPercentagePerLvl");

        FeudalValuesUtils.multi_shootingMaxLvl = (int) databaseConfiguration.get("Multi-shooting.multi_shootingMaxLvl");
        FeudalValuesUtils.multi_shootingPercentagePerLvl = (double) databaseConfiguration.get("Multi-shooting.multi_shootingPercentagePerLvl");

        FeudalValuesUtils.bowStunMaxLvl = (int) databaseConfiguration.get("BowStun.bowStunMaxLvl");
        FeudalValuesUtils.bowStunPercentagePerLvl = (double) databaseConfiguration.get("BowStun.bowStunPercentagePerLvl");

        FeudalValuesUtils.greedMaxLvl = (int) databaseConfiguration.get("Greed.greedMaxLvl");
        FeudalValuesUtils.greedPercentagePerLvl = (double) databaseConfiguration.get("Greed.greedPercentagePerLvl");

    }

    public static void saveEnchantmentsConfig() {

        File file = new File(path, ENCHANTMENTS_YML);
        checkEnchantmentsConfig();

        try {

            YamlConfiguration.loadConfiguration(file).save(file);

        } catch (IOException e) {

            throw new RuntimeException(e);

        }


    }

    public static void checkEnchantmentsConfig() {

        if (!path.exists())
            path.mkdir();

        File file = new File(path, ENCHANTMENTS_YML);

        if (!file.exists())

            try {

                file.createNewFile();

                databaseConfiguration = YamlConfiguration.loadConfiguration(file);

                databaseConfiguration.set("Vampirism.vampirismMaxLvl", 1);
                databaseConfiguration.set("Vampirism.vampirismPercentagePerLvl", 1.0);

                databaseConfiguration.set("DoubleDamage.doubleDamageMaxLvl", 1);
                databaseConfiguration.set("DoubleDamage.doubleDamagePercentagePerLvl", 1.0);

                databaseConfiguration.set("Blindness.blindnessMaxLvl", 1);
                databaseConfiguration.set("Blindness.blindnessPercentagePerLvl", 1.0);
                databaseConfiguration.set("Blindness.blindnessTime", 1);
                databaseConfiguration.set("Blindness.blindnessTimePercentagePerLvl", 1.0);

                databaseConfiguration.set("Slowdown.slowdownMaxLvl", 1);
                databaseConfiguration.set("Slowdown.slowdownPercentagePerLvl", 1.0);
                databaseConfiguration.set("Slowdown.slowdownTime", 1);
                databaseConfiguration.set("Slowdown.slowdownTimePercentagePerLvl", 1.0);

                databaseConfiguration.set("Desiccation.desiccationMaxLvl", 1);
                databaseConfiguration.set("Desiccation.desiccationPercentagePerLvl", 1.0);
                databaseConfiguration.set("Desiccation.slowdownTime", 1);
                databaseConfiguration.set("Desiccation.slowdownTimePercentagePerLvl", 1.0);

                databaseConfiguration.set("SwordStun.swordStunMaxLvl", 1);
                databaseConfiguration.set("SwordStun.swordStunPercentagePerLvl", 1.0);
                databaseConfiguration.set("SwordStun.swordStunTime", 1);
                databaseConfiguration.set("SwordStun.swordStunTimePercentagePerLvl", 1.0);

                databaseConfiguration.set("Levitation.levitationMaxLvl", 1);
                databaseConfiguration.set("Levitation.levitationPercentagePerLvl", 1.0);
                databaseConfiguration.set("Levitation.levitationTime", 1);
                databaseConfiguration.set("Levitation.levitationTimePercentagePerLvl", 1.0);

                databaseConfiguration.set("Poisoning.poisoningMaxLvl", 1);
                databaseConfiguration.set("Poisoning.poisoningPercentagePerLvl", 1.0);
                databaseConfiguration.set("Poisoning.poisoningTime", 1);
                databaseConfiguration.set("Poisoning.poisoningTimePercentagePerLvl", 1.0);

                databaseConfiguration.set("Nausea.nauseaMaxLvl", 1);
                databaseConfiguration.set("Nausea.nauseaPercentagePerLvl", 1.0);
                databaseConfiguration.set("Nausea.nauseaTime", 1);
                databaseConfiguration.set("Nausea.nauseaTimePercentagePerLvl", 1.0);

                databaseConfiguration.set("Hook.hookMaxLvl", 1);
                databaseConfiguration.set("Hook.hookPercentagePerLvl", 1.0);

                databaseConfiguration.set("Multi-shooting.multi_shootingMaxLvl", 1);
                databaseConfiguration.set("Multi-shooting.multi_shootingPercentagePerLvl", 1.0);

                databaseConfiguration.set("BowStun.bowStunMaxLvl", 1);
                databaseConfiguration.set("BowStun.bowStunPercentagePerLvl", 1.0);

                databaseConfiguration.set("Greed.greedMaxLvl", 1);
                databaseConfiguration.set("Greed.greedPercentagePerLvl", 1.0);

                databaseConfiguration.save(file);

            } catch (IOException e) {

                throw new RuntimeException(e);

            }
    }
}
