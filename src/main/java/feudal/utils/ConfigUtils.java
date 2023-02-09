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
        FeudalValuesUtils.vampirismPersent = (double) databaseConfiguration.get("Vampirism.vampirismPersent");
        FeudalValuesUtils.doubleDamageMaxLvl = (int) databaseConfiguration.get("DoubleDamage.doubleDamageMaxLvl");
        FeudalValuesUtils.doubleDamagePersent = (double) databaseConfiguration.get("DoubleDamage.doubleDamagePersent");
        FeudalValuesUtils.blindnessMaxLvl = (int) databaseConfiguration.get("Blindness.blindnessMaxLvl");
        FeudalValuesUtils.blindnessPersent = (double) databaseConfiguration.get("Blindness.blindnessPersent");
        FeudalValuesUtils.slowdownMaxLvl = (int) databaseConfiguration.get("Slowdown.slowdownMaxLvl");
        FeudalValuesUtils.slowdownPersent = (double) databaseConfiguration.get("Slowdown.slowdownPersent");
        FeudalValuesUtils.desiccationMaxLvl = (int) databaseConfiguration.get("Desiccation.desiccationMaxLvl");
        FeudalValuesUtils.desiccationPersent = (double) databaseConfiguration.get("Desiccation.desiccationPersent");
        FeudalValuesUtils.swordStunMaxLvl = (int) databaseConfiguration.get("SwordStun.swordStunMaxLvl");
        FeudalValuesUtils.swordStunPersent = (double) databaseConfiguration.get("SwordStun.swordStunPersent");
        FeudalValuesUtils.levitationMaxLvl = (int) databaseConfiguration.get("Levitation.levitationMaxLvl");
        FeudalValuesUtils.levitationPersent = (double) databaseConfiguration.get("Levitation.levitationPersent");
        FeudalValuesUtils.poisoningMaxLvl = (int) databaseConfiguration.get("Poisoning.poisoningMaxLvl");
        FeudalValuesUtils.poisoningPersent = (double) databaseConfiguration.get("Poisoning.poisoningPersent");
        FeudalValuesUtils.nauseaMaxLvl = (int) databaseConfiguration.get("Nausea.nauseaMaxLvl");
        FeudalValuesUtils.nauseaPersent = (double) databaseConfiguration.get("Nausea.nauseaPersent");
        FeudalValuesUtils.hookMaxLvl = (int) databaseConfiguration.get("Hook.hookMaxLvl");
        FeudalValuesUtils.hookPersent = ((Double) databaseConfiguration.get("Hook.hookPersent")).doubleValue();
        FeudalValuesUtils.multi_shootingMaxLvl = (int) databaseConfiguration.get("Multi-shooting.multi_shootingMaxLvl");
        FeudalValuesUtils.multi_shootingPersent = (double) databaseConfiguration.get("Multi-shooting.multi_shootingPersent");
        FeudalValuesUtils.bowStunMaxLvl = (int) databaseConfiguration.get("BowStun.bowStunMaxLvl");
        FeudalValuesUtils.bowStunPersent = (double) databaseConfiguration.get("BowStun.bowStunPersent");
        FeudalValuesUtils.greedMaxLvl = (int) databaseConfiguration.get("Greed.greedMaxLvl");
        FeudalValuesUtils.greedPersent = (double) databaseConfiguration.get("Greed.greedPersent");

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
                databaseConfiguration.set("Vampirism.vampirismPersent", 1.0);
                databaseConfiguration.set("DoubleDamage.doubleDamageMaxLvl", 1);
                databaseConfiguration.set("DoubleDamage.doubleDamagePersent", 1.0);
                databaseConfiguration.set("Blindness.blindnessMaxLvl", 1);
                databaseConfiguration.set("Blindness.blindnessPersent", 1.0);
                databaseConfiguration.set("Slowdown.slowdownMaxLvl", 1);
                databaseConfiguration.set("Slowdown.slowdownPersent", 1.0);
                databaseConfiguration.set("Desiccation.desiccationMaxLvl", 1);
                databaseConfiguration.set("Desiccation.desiccationPersent", 1.0);
                databaseConfiguration.set("SwordStun.swordStunMaxLvl", 1);
                databaseConfiguration.set("SwordStun.swordStunPersent", 1.0);
                databaseConfiguration.set("Levitation.levitationMaxLvl", 1);
                databaseConfiguration.set("Levitation.levitationPersent", 1.0);
                databaseConfiguration.set("Poisoning.poisoningMaxLvl", 1);
                databaseConfiguration.set("Poisoning.poisoningPersent", 1.0);
                databaseConfiguration.set("Nausea.nauseaMaxLvl", 1);
                databaseConfiguration.set("Nausea.nauseaPersent", 1.0);
                databaseConfiguration.set("Hook.hookMaxLvl", 1);
                databaseConfiguration.set("Hook.hookPersent", 1.0);
                databaseConfiguration.set("Multi-shooting.multi_shootingMaxLvl", 1);
                databaseConfiguration.set("Multi-shooting.multi_shootingPersent", 1.0);
                databaseConfiguration.set("BowStun.bowStunMaxLvl", 1);
                databaseConfiguration.set("BowStun.bowStunPersent", 1.0);
                databaseConfiguration.set("Greed.greedMaxLvl", 1);
                databaseConfiguration.set("Greed.greedPersent", 1.0);

                databaseConfiguration.save(file);

            } catch (IOException e) {

                throw new RuntimeException(e);

            }
    }
}
