package feudal.utils;

import com.mongodb.client.MongoClients;
import feudal.Feudal;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ConfigUtils {

    static File path = Feudal.getPlugin().getDataFolder();
    static FileConfiguration databaseConfiguration;

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
}
