package feudal.possessions;

import feudal.data.builder.FeudalKingdom;
import feudal.data.cache.CacheKingdomsMap;
import feudal.data.database.KingdomDBInfo;
import feudal.utils.GsonUtils;
import feudal.utils.wrappers.ChunkWrapper;
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
    static KingdomDBInfo kingdomDataBase = new KingdomDBInfo(config.get("MongoClientName").toString(), config.get("MongoDataBaseName").toString(), config.get("MongoCollectionName").toString());

    public static boolean createStartTerritory(String kingdomName, @NotNull List<Chunk> startTerritory) {

        for (Chunk chunk : startTerritory) {

            if (kingdomDataBase.chunkInKingdom(GsonUtils.chunkToJson(ChunkWrapper.chunkToChunkWrapper(chunk))))
                return false;

            FeudalKingdom feudalKingdom = CacheKingdomsMap.getKingdomInfo().get(kingdomName);

            feudalKingdom.addTerritory(chunk);
            feudalKingdom.addPrivateTerritory(chunk);

        }

        return true;
    }
}
