package feudal.utils;

import feudal.Feudal;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.bukkit.configuration.file.FileConfiguration;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ConfigUtil {

    static FileConfiguration databaseConfig = Feudal.getInstance().getDataBaseConfig().getDatabaseConfiguration();
    static String databaseAddress = databaseConfig.get("Mongo.address").toString();
    static String databaseName = databaseConfig.get("Mongo.name").toString();
    static String playerCollection = databaseConfig.get("Mongo.playerCollection").toString();
    static String kingdomCollection = databaseConfig.get("Mongo.kingdomCollection").toString();

    public static String getDatabaseAddress() {
        return databaseAddress;
    }

    public static String getDatabaseName() {
        return databaseName;
    }

    public static String getPlayerCollection() {
        return playerCollection;
    }

    public static String getKingdomCollection() {
        return kingdomCollection;
    }
}
