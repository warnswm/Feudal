package feudal.possessions;

import feudal.data.cache.CacheKingdoms;
import feudal.data.database.KingdomInfo;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class AddStartTerritory {

    public static boolean createStartTerritory(String kingdomName, List<Chunk> startTerritory) {

        FileConfiguration config = Bukkit.getPluginManager().getPlugin("Feudal").getConfig();
        KingdomInfo kingdomDataBase = new KingdomInfo(config.get("MongoClientName").toString(), config.get("MongoDataBaseName").toString(), config.get("MongoCollectionName").toString());

        for (Chunk chunk : startTerritory) {

            if (kingdomDataBase.chunkInKingdom(chunk)) return false;

            KingdomInfo kingdom = CacheKingdoms.getKingdomInfo().get(kingdomName);

            kingdom.addTerritory(chunk);

        }



        return true;
    }
}
