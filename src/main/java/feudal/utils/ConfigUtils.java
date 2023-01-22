package feudal.utils;

import feudal.info.PlayerInfoDB;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigUtils {
    private final FileConfiguration config = Bukkit.getPluginManager().getPlugin("Feudal").getConfig();
    public final PlayerInfoDB playerInfoDB = new PlayerInfoDB(config.get("MongoClientName").toString(), config.get("MongoDataBaseName").toString(), config.get("MongoCollectionName").toString());
}
