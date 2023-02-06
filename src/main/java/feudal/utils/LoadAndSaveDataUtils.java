package feudal.utils;

import feudal.data.builder.FeudalKingdom;
import feudal.data.builder.FeudalPlayer;
import feudal.data.cache.CacheKingdomsMap;
import feudal.data.cache.CachePlayersMap;
import feudal.data.database.KingdomInfo;
import feudal.data.database.PlayerInfo;
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

    public static void saveAllPlayers() {

        Bukkit.getOnlinePlayers().forEach(player -> new Thread(() -> {

            FeudalPlayer feudalPlayer = CachePlayersMap.getPlayerInfo().get(player);

            playerInfo.setField(player, "classID", feudalPlayer.getAClassID());
            playerInfo.setField(player, "experience", feudalPlayer.getExperience());
            playerInfo.setField(player, "gameClassLvl", feudalPlayer.getGameClassLvl());
            playerInfo.setField(player, "gameClassExperience", feudalPlayer.getGameClassExperience());
            playerInfo.setField(player, "balance", feudalPlayer.getBalance());
            playerInfo.setField(player, "deaths", feudalPlayer.getDeaths());
            playerInfo.setField(player, "kills", feudalPlayer.getKills());
            playerInfo.setField(player, "luckLvl", feudalPlayer.getLuckLvl());
            playerInfo.setField(player, "speedLvl", feudalPlayer.getSpeedLvl());
            playerInfo.setField(player, "staminaLvl", feudalPlayer.getStaminaLvl());
            playerInfo.setField(player, "strengthLvl", feudalPlayer.getStrengthLvl());
            playerInfo.setField(player, "survivabilityLvl", feudalPlayer.getSurvivabilityLvl());
            playerInfo.setField(player, "kingdomName", feudalPlayer.getKingdomName());

            CachePlayersMap.getPlayerInfo().remove(player);

        }).start());

        System.gc();

    }

    public static void loadPlayer(Player player) {

        FeudalPlayer feudalPlayer;


        if (!playerInfo.hasPlayer(player)) {

            playerInfo.createNewPlayer(player);

            feudalPlayer = new FeudalPlayer(player);
            feudalPlayer.setaClassID(0).setExperience(0).setGameClassExperience(0)
                    .setBalance(1000).setDeaths(0).setKills(0)
                    .setLuckLvl(0).setSpeedLvl(0).setStaminaLvl(0)
                    .setStrengthLvl(0).setKingdomName("notInTheKingdom").setSurvivabilityLvl(0)
                    .setGameClassLvl(0);

            return;
        }

        feudalPlayer = new FeudalPlayer(player);
        feudalPlayer.setaClassID((Integer) playerInfo.getField(player, "classID"))
                .setExperience((Integer) playerInfo.getField(player, "experience"))
                .setGameClassLvl((Integer) playerInfo.getField(player, "gameClassLvl"))
                .setGameClassExperience((Integer) playerInfo.getField(player, "gameClassExperience"))
                .setBalance((Integer) playerInfo.getField(player, "balance"))
                .setDeaths((Integer) playerInfo.getField(player, "deaths"))
                .setKills((Integer) playerInfo.getField(player, "kills"))
                .setLuckLvl((Integer) playerInfo.getField(player, "luckLvl"))
                .setSpeedLvl((Integer) playerInfo.getField(player, "speedLvl"))
                .setStaminaLvl((Integer) playerInfo.getField(player, "staminaLvl"))
                .setStrengthLvl((Integer) playerInfo.getField(player, "strengthLvl"))
                .setKingdomName((String) playerInfo.getField(player, "kingdomName"))
                .setSurvivabilityLvl((Integer) playerInfo.getField(player, "survivabilityLvl"));

        CachePlayersMap.getPlayerInfo().put(player, feudalPlayer);

    }

    public static void loadPlayerAttributes(@NotNull Player player) {

        FeudalPlayer feudalPlayer = new FeudalPlayer(player);

        float health = feudalPlayer.getSurvivabilityLvl(), speed = feudalPlayer.getSpeedLvl();

        player.setMaxHealth(20 * (health / 100) + 20);
        player.setWalkSpeed(0.2f * (speed / 100) + 0.2f);
        ScoreBoardInfo.createScoreBoardInfo(player);


        if (!kingdomInfo.getPlayerKingdom(player).equalsIgnoreCase("notInTheKingdom"))
            player.setDisplayName(player.getDisplayName() + " [" + kingdomInfo.getPlayerKingdom(player) + "]");

    }

    public static void savePlayer(Player player) {

        new Thread(() -> {

            FeudalPlayer feudalPlayer = CachePlayersMap.getPlayerInfo().get(player);

            playerInfo.setField(player, "classID", feudalPlayer.getAClassID());
            playerInfo.setField(player, "experience", feudalPlayer.getExperience());
            playerInfo.setField(player, "gameClassLvl", feudalPlayer.getGameClassLvl());
            playerInfo.setField(player, "gameClassExperience", feudalPlayer.getGameClassExperience());
            playerInfo.setField(player, "balance", feudalPlayer.getBalance());
            playerInfo.setField(player, "deaths", feudalPlayer.getDeaths());
            playerInfo.setField(player, "kills", feudalPlayer.getKills());
            playerInfo.setField(player, "luckLvl", feudalPlayer.getLuckLvl());
            playerInfo.setField(player, "speedLvl", feudalPlayer.getSpeedLvl());
            playerInfo.setField(player, "staminaLvl", feudalPlayer.getStaminaLvl());
            playerInfo.setField(player, "strengthLvl", feudalPlayer.getStrengthLvl());
            playerInfo.setField(player, "survivabilityLvl", feudalPlayer.getSurvivabilityLvl());
            playerInfo.setField(player, "kingdomName", feudalPlayer.getKingdomName());

            CachePlayersMap.getPlayerInfo().remove(player);

        }).start();

        System.gc();

    }
}
