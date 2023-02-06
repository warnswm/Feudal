package feudal.utils;

import feudal.data.builder.FeudalKingdom;
import feudal.data.builder.FeudalPlayer;
import feudal.data.cache.CacheKingdomsMap;
import feudal.data.cache.CachePlayersMap;
import feudal.data.database.KingdomDBInfo;
import feudal.data.database.PlayerDBInfo;
import feudal.utils.wrappers.ChunkWrapper;
import feudal.view.ScoreBoardInfo;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LoadAndSaveDataUtils {

    static FileConfiguration config = Bukkit.getPluginManager().getPlugin("Feudal").getConfig();
    static PlayerDBInfo playerDBInfo = new PlayerDBInfo(config.get("MongoClientName").toString(), config.get("MongoDataBaseName").toString(), config.get("MongoCollectionName").toString());
    static KingdomDBInfo kingdomDBInfo = new KingdomDBInfo(config.get("MongoClientName").toString(), config.get("MongoDataBaseName").toString(), config.get("MongoCollectionName").toString());



    public static void loadKingdom(Player player) {

        if (!kingdomDBInfo.playerInKingdom(player)) return;

        String kingdomName = kingdomDBInfo.getPlayerKingdom(player);
        FeudalKingdom feudalKingdom = new FeudalKingdom(kingdomName);

        feudalKingdom.setKingdomName(kingdomName)
                .setKing((Player) kingdomDBInfo.getField(kingdomName, "king"))
                .setMembers((List<Player>) kingdomDBInfo.getField(kingdomName, "members"))
                .setBarons((List<Player>) kingdomDBInfo.getField(kingdomName, "barons"))
                .setReputation((Integer) kingdomDBInfo.getField(kingdomName, "reputation"))
                .setBalance((Long) kingdomDBInfo.getField(kingdomName, "balance"))
                .setTerritory((List<Chunk>) kingdomDBInfo.getField(kingdomName, "territory"))
                .setPrivateTerritory((List<Chunk>) kingdomDBInfo.getField(kingdomName, "privateTerritory"));

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


                kingdomDBInfo.setField(kingdomName, "king", cacheFeudalKingdom.getKing().getUniqueId().toString());
                kingdomDBInfo.setField(kingdomName, "members", members);
                kingdomDBInfo.setField(kingdomName, "barons", barons);
                kingdomDBInfo.setField(kingdomName, "territory", territory);
                kingdomDBInfo.setField(kingdomName, "privateTerritory", privateTerritory);
                kingdomDBInfo.setField(kingdomName, "balance", cacheFeudalKingdom.getBalance());
                kingdomDBInfo.setField(kingdomName, "reputation", cacheFeudalKingdom.getReputation());

                CacheKingdomsMap.getKingdomInfo().remove(kingdomName);

            }
        }).start();

        System.gc();

    }

    public static void saveKingdom(Player player) {

        new Thread(() -> {

            String kingdomName = kingdomDBInfo.getPlayerKingdom(player);
            FeudalKingdom feudalKingdom = new FeudalKingdom(kingdomName);

            List<String> members = new ArrayList<>();
            feudalKingdom.getMembers().forEach(member -> members.add(member.getUniqueId().toString()));

            List<String> barons = new ArrayList<>();
            feudalKingdom.getBarons().forEach(baron -> barons.add(baron.getUniqueId().toString()));

            List<String> territory = new ArrayList<>();
            feudalKingdom.getTerritory().forEach(chunk -> territory.add(GsonUtils.chunkToJson(ChunkWrapper.chunkToChunkWrapper(chunk))));

            List<String> privateTerritory = new ArrayList<>();
            feudalKingdom.getPrivateTerritory().forEach(chunk -> privateTerritory.add(GsonUtils.chunkToJson(ChunkWrapper.chunkToChunkWrapper(chunk))));

            kingdomDBInfo.setField(kingdomName, "king", feudalKingdom.getKing());
            kingdomDBInfo.setField(kingdomName, "members", members);
            kingdomDBInfo.setField(kingdomName, "barons", barons);
            kingdomDBInfo.setField(kingdomName, "territory", territory);
            kingdomDBInfo.setField(kingdomName, "privateTerritory", privateTerritory);
            kingdomDBInfo.setField(kingdomName, "reputation", feudalKingdom.getReputation());
            kingdomDBInfo.setField(kingdomName, "balance", feudalKingdom.getBalance());

        }).start();

        System.gc();

    }

    public static void saveAllPlayers() {

        Bukkit.getOnlinePlayers().forEach(player -> new Thread(() -> {

            FeudalPlayer feudalPlayer = CachePlayersMap.getPlayerInfo().get(player);

            playerDBInfo.setField(player, "classID", feudalPlayer.getAClassID());
            playerDBInfo.setField(player, "experience", feudalPlayer.getExperience());
            playerDBInfo.setField(player, "gameClassLvl", feudalPlayer.getGameClassLvl());
            playerDBInfo.setField(player, "gameClassExperience", feudalPlayer.getGameClassExperience());
            playerDBInfo.setField(player, "balance", feudalPlayer.getBalance());
            playerDBInfo.setField(player, "deaths", feudalPlayer.getDeaths());
            playerDBInfo.setField(player, "kills", feudalPlayer.getKills());
            playerDBInfo.setField(player, "luckLvl", feudalPlayer.getLuckLvl());
            playerDBInfo.setField(player, "speedLvl", feudalPlayer.getSpeedLvl());
            playerDBInfo.setField(player, "staminaLvl", feudalPlayer.getStaminaLvl());
            playerDBInfo.setField(player, "strengthLvl", feudalPlayer.getStrengthLvl());
            playerDBInfo.setField(player, "survivabilityLvl", feudalPlayer.getSurvivabilityLvl());
            playerDBInfo.setField(player, "kingdomName", feudalPlayer.getKingdomName());

            CachePlayersMap.getPlayerInfo().remove(player);

        }).start());

        System.gc();

    }

    public static void loadPlayer(Player player) {

        FeudalPlayer feudalPlayer;


        if (!playerDBInfo.hasPlayer(player)) {

            playerDBInfo.createNewPlayer(player);

            feudalPlayer = new FeudalPlayer(player);
            feudalPlayer.setaClassID(0).setExperience(0).setGameClassExperience(0)
                    .setBalance(1000).setDeaths(0).setKills(0)
                    .setLuckLvl(0).setSpeedLvl(0).setStaminaLvl(0)
                    .setStrengthLvl(0).setKingdomName("notInTheKingdom").setSurvivabilityLvl(0)
                    .setGameClassLvl(0);

            return;
        }

        feudalPlayer = new FeudalPlayer(player);
        feudalPlayer.setaClassID((Integer) playerDBInfo.getField(player, "classID"))
                .setExperience((Integer) playerDBInfo.getField(player, "experience"))
                .setGameClassLvl((Integer) playerDBInfo.getField(player, "gameClassLvl"))
                .setGameClassExperience((Integer) playerDBInfo.getField(player, "gameClassExperience"))
                .setBalance((Integer) playerDBInfo.getField(player, "balance"))
                .setDeaths((Integer) playerDBInfo.getField(player, "deaths"))
                .setKills((Integer) playerDBInfo.getField(player, "kills"))
                .setLuckLvl((Integer) playerDBInfo.getField(player, "luckLvl"))
                .setSpeedLvl((Integer) playerDBInfo.getField(player, "speedLvl"))
                .setStaminaLvl((Integer) playerDBInfo.getField(player, "staminaLvl"))
                .setStrengthLvl((Integer) playerDBInfo.getField(player, "strengthLvl"))
                .setKingdomName((String) playerDBInfo.getField(player, "kingdomName"))
                .setSurvivabilityLvl((Integer) playerDBInfo.getField(player, "survivabilityLvl"));

        CachePlayersMap.getPlayerInfo().put(player, feudalPlayer);

    }

    public static void loadPlayerAttributes(@NotNull Player player) {

        FeudalPlayer feudalPlayer = new FeudalPlayer(player);

        float health = feudalPlayer.getSurvivabilityLvl(), speed = feudalPlayer.getSpeedLvl();

        player.setMaxHealth(20 * (health / 100) + 20);
        player.setWalkSpeed(0.2f * (speed / 100) + 0.2f);
        ScoreBoardInfo.createScoreBoardInfo(player);


        if (!kingdomDBInfo.getPlayerKingdom(player).equalsIgnoreCase("notInTheKingdom"))
            player.setDisplayName(player.getDisplayName() + " [" + kingdomDBInfo.getPlayerKingdom(player) + "]");

    }

    public static void savePlayer(Player player) {

        new Thread(() -> {

            FeudalPlayer feudalPlayer = CachePlayersMap.getPlayerInfo().get(player);

            playerDBInfo.setField(player, "classID", feudalPlayer.getAClassID());
            playerDBInfo.setField(player, "experience", feudalPlayer.getExperience());
            playerDBInfo.setField(player, "gameClassLvl", feudalPlayer.getGameClassLvl());
            playerDBInfo.setField(player, "gameClassExperience", feudalPlayer.getGameClassExperience());
            playerDBInfo.setField(player, "balance", feudalPlayer.getBalance());
            playerDBInfo.setField(player, "deaths", feudalPlayer.getDeaths());
            playerDBInfo.setField(player, "kills", feudalPlayer.getKills());
            playerDBInfo.setField(player, "luckLvl", feudalPlayer.getLuckLvl());
            playerDBInfo.setField(player, "speedLvl", feudalPlayer.getSpeedLvl());
            playerDBInfo.setField(player, "staminaLvl", feudalPlayer.getStaminaLvl());
            playerDBInfo.setField(player, "strengthLvl", feudalPlayer.getStrengthLvl());
            playerDBInfo.setField(player, "survivabilityLvl", feudalPlayer.getSurvivabilityLvl());
            playerDBInfo.setField(player, "kingdomName", feudalPlayer.getKingdomName());

            CachePlayersMap.getPlayerInfo().remove(player);

        }).start();

        System.gc();

    }
}
