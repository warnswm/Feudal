package feudal.possessions;

import feudal.data.cache.CacheKingdomsMap;
import feudal.data.database.KingdomInfo;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AddStartTerritory {

    static FileConfiguration config = Bukkit.getPluginManager().getPlugin("Feudal").getConfig();
    static KingdomInfo kingdomDataBase = new KingdomInfo(config.get("MongoClientName").toString(), config.get("MongoDataBaseName").toString(), config.get("MongoCollectionName").toString());

    public static boolean createStartTerritory(String kingdomName, @NotNull List<Chunk> startTerritory) {

        for (Chunk chunk : startTerritory) {

            if (kingdomDataBase.chunkInKingdom(chunk)) return false;

            KingdomInfo kingdom = CacheKingdomsMap.getKingdomInfo().get(kingdomName);

            kingdom.addTerritory(chunk);
            kingdom.addPrivateTerritory(chunk);

        }

        return true;
    }
}
