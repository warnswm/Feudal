package feudal.utils;

import feudal.data.builder.FeudalKingdom;
import feudal.data.cache.CacheKingdomsMap;
import feudal.data.database.KingdomInfo;
import feudal.data.database.PlayerInfo;
import feudal.utils.wrappers.ChunkWrapper;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LoadAndSaveDataUtils {

    static FileConfiguration config = Bukkit.getPluginManager().getPlugin("Feudal").getConfig();
    static PlayerInfo playerInfo = new PlayerInfo(config.get("MongoClientName").toString(), config.get("MongoDataBaseName").toString(), config.get("MongoCollectionName").toString());
    static KingdomInfo kingdomInfo = new KingdomInfo(config.get("MongoClientName").toString(), config.get("MongoDataBaseName").toString(), config.get("MongoCollectionName").toString());



    public static void loadKingdom(Player player) {

        if (!kingdomInfo.playerInKingdom(player)) return;

        String kingdomName = kingdomInfo.getPlayerKingdom(player);
        FeudalKingdom feudalKingdom = new FeudalKingdom(kingdomName);

        feudalKingdom.setKingdomName(kingdomName)
                .setKing((Player) kingdomInfo.getField(kingdomName, "king"))
                .setMembers((List<Player>) kingdomInfo.getField(kingdomName, "members"))
                .setBarons((List<Player>) kingdomInfo.getField(kingdomName, "barons"))
                .setReputation((Integer) kingdomInfo.getField(kingdomName, "reputation"))
                .setBalance((Integer) kingdomInfo.getField(kingdomName, "balance"))
                .setTerritory((List<Chunk>) kingdomInfo.getField(kingdomName, "territory"))
                .setPrivateTerritory((List<Chunk>) kingdomInfo.getField(kingdomName, "privateTerritory"));

        CacheKingdomsMap.getKingdomInfo().put(kingdomName, feudalKingdom);

    }

    public static void saveAllKingdoms() {

        new Thread(() -> {

            for (Map.Entry<String, FeudalKingdom> kingdom : CacheKingdomsMap.getKingdomInfo().entrySet()) {

                FeudalKingdom cacheFeudalKingdom = kingdom.getValue();
                String kingdomName = cacheFeudalKingdom.getKingdomName();

                List<String> members = new ArrayList<>();
                cacheFeudalKingdom.getMembers().forEach(member -> members.add(member.getUniqueId().toString()));

                List<String> barons = new ArrayList<>();
                cacheFeudalKingdom.getBarons().forEach(baron -> barons.add(baron.getUniqueId().toString()));

                List<String> territory = new ArrayList<>();
                cacheFeudalKingdom.getTerritory().forEach(chunk -> territory.add(GsonUtils.chunkToJson(ChunkWrapper.chunkToChunkWrapper(chunk))));

                List<String> privateTerritory = new ArrayList<>();
                cacheFeudalKingdom.getPrivateTerritory().forEach(chunk -> privateTerritory.add(GsonUtils.chunkToJson(ChunkWrapper.chunkToChunkWrapper(chunk))));


                kingdomInfo.setField(kingdomName, "king", cacheFeudalKingdom.getKing().getUniqueId().toString());
                kingdomInfo.setField(kingdomName, "members", members);
                kingdomInfo.setField(kingdomName, "barons", barons);
                kingdomInfo.setField(kingdomName, "territory", territory);
                kingdomInfo.setField(kingdomName, "privateTerritory", privateTerritory);
                kingdomInfo.setField(kingdomName, "balance", cacheFeudalKingdom.getBalance());
                kingdomInfo.setField(kingdomName, "reputation", cacheFeudalKingdom.getReputation());

                CacheKingdomsMap.getKingdomInfo().remove(kingdomName);

            }
        }).start();

        System.gc();

    }

    public static void saveKingdom(Player player) {

        new Thread(() -> {

            String kingdomName = kingdomInfo.getPlayerKingdom(player);
            FeudalKingdom feudalKingdom = new FeudalKingdom(kingdomName);

            List<String> members = new ArrayList<>();
            feudalKingdom.getMembers().forEach(member -> members.add(member.getUniqueId().toString()));

            List<String> barons = new ArrayList<>();
            feudalKingdom.getBarons().forEach(baron -> barons.add(baron.getUniqueId().toString()));

            List<String> territory = new ArrayList<>();
            feudalKingdom.getTerritory().forEach(chunk -> territory.add(GsonUtils.chunkToJson(ChunkWrapper.chunkToChunkWrapper(chunk))));

            List<String> privateTerritory = new ArrayList<>();
            feudalKingdom.getPrivateTerritory().forEach(chunk -> privateTerritory.add(GsonUtils.chunkToJson(ChunkWrapper.chunkToChunkWrapper(chunk))));

            kingdomInfo.setField(kingdomName, "king", feudalKingdom.getKing());
            kingdomInfo.setField(kingdomName, "members", members);
            kingdomInfo.setField(kingdomName, "barons", barons);
            kingdomInfo.setField(kingdomName, "territory", territory);
            kingdomInfo.setField(kingdomName, "privateTerritory", privateTerritory);
            kingdomInfo.setField(kingdomName, "reputation", feudalKingdom.getReputation());
            kingdomInfo.setField(kingdomName, "balance", feudalKingdom.getBalance());

        }).start();

        System.gc();

    }
}
