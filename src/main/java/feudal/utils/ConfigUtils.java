package feudal.utils;

import com.mongodb.client.MongoClients;
import feudal.Feudal;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ConfigUtils {

    static String path = Feudal.getPlugin().getDataFolder().getPath();

    public static void readDatabaseConfig() {

        File databaseConfig = new File(path + "/database.yml");

        if (!databaseConfig.exists()) {

            try {

                databaseConfig.createNewFile();

            } catch (IOException e) {

                throw new RuntimeException(e);

            }

            return;

        }

        FileConfiguration fileConfiguration = new YamlConfiguration();

        try {

            fileConfiguration.load(databaseConfig);

        } catch (IOException | InvalidConfigurationException e) {

            throw new RuntimeException(e);

        }

        FeudalValuesUtils.mongoClient = MongoClients.create((String) fileConfiguration.get("Mongo.address"));
        FeudalValuesUtils.database = FeudalValuesUtils.mongoClient.getDatabase((String) fileConfiguration.get("Mongo.name"));
        FeudalValuesUtils.playersCollection = FeudalValuesUtils.database.getCollection((String) fileConfiguration.get("Mongo.playersCollection"));
        FeudalValuesUtils.kingdomsCollection = FeudalValuesUtils.database.getCollection((String) fileConfiguration.get("Mongo.kingdomsCollection"));

    }

    public static void saveDatabaseConfig() {


        File databaseConfig = new File(path + "/database.yml");

        if (!databaseConfig.exists()) {

            try {

                databaseConfig.createNewFile();

            } catch (IOException e) {

                throw new RuntimeException(e);

            }

            return;

        }

        FileConfiguration fileConfiguration = new YamlConfiguration();

        try {

            fileConfiguration.save(databaseConfig);

        } catch (IOException e) {

            throw new RuntimeException(e);

        }

        fileConfiguration.set("Mongo.address", FeudalValuesUtils.mongoClient);
        fileConfiguration.set("Mongo.name", FeudalValuesUtils.database);
        fileConfiguration.set("Mongo.playersCollection", FeudalValuesUtils.playersCollection);
        fileConfiguration.set("Mongo.kingdomsCollection", FeudalValuesUtils.kingdomsCollection);

    }
}
