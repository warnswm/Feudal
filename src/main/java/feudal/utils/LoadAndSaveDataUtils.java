package feudal.utils;

import feudal.data.builder.FeudalKingdom;
import feudal.data.builder.FeudalPlayer;
import feudal.data.cache.CacheKingdomsMap;
import feudal.data.cache.CachePlayersMap;
import feudal.data.database.KingdomDBHandler;
import feudal.data.database.PlayerDBHandler;
import feudal.utils.wrappers.ChunkWrapper;
import feudal.visual.scoreboards.ScoreBoardInfo;
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
    static PlayerDBHandler playerDBHandler = new PlayerDBHandler(config.get("MongoClientName").toString(), config.get("MongoDataBaseName").toString(), config.get("MongoCollectionName").toString());
    static KingdomDBHandler kingdomDBHandler = new KingdomDBHandler(config.get("MongoClientName").toString(), config.get("MongoDataBaseName").toString(), config.get("MongoCollectionName").toString());


    public static void loadKingdom(Player player) {

        if (!kingdomDBHandler.playerInKingdom(player)) return;

        String kingdomName = kingdomDBHandler.getPlayerKingdom(player);
        FeudalKingdom feudalKingdom = new FeudalKingdom(kingdomName);

        feudalKingdom.setKingdomName(kingdomName)
                .setKing((Player) kingdomDBHandler.getField(kingdomName, "king"))
                .setMembers((List<Player>) kingdomDBHandler.getField(kingdomName, "members"))
                .setBarons((List<Player>) kingdomDBHandler.getField(kingdomName, "barons"))
                .setReputation((Integer) kingdomDBHandler.getField(kingdomName, "reputation"))
                .setBalance((Long) kingdomDBHandler.getField(kingdomName, "balance"))
                .setTerritory((List<Chunk>) kingdomDBHandler.getField(kingdomName, "territory"))
                .setPrivateTerritory((List<Chunk>) kingdomDBHandler.getField(kingdomName, "privateTerritory"));

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


                kingdomDBHandler.setField(kingdomName, "king", cacheFeudalKingdom.getKing().getUniqueId().toString());
                kingdomDBHandler.setField(kingdomName, "members", members);
                kingdomDBHandler.setField(kingdomName, "barons", barons);
                kingdomDBHandler.setField(kingdomName, "territory", territory);
                kingdomDBHandler.setField(kingdomName, "privateTerritory", privateTerritory);
                kingdomDBHandler.setField(kingdomName, "balance", cacheFeudalKingdom.getBalance());
                kingdomDBHandler.setField(kingdomName, "reputation", cacheFeudalKingdom.getReputation());

                CacheKingdomsMap.getKingdomInfo().remove(kingdomName);

            }
        }).start();

        System.gc();

    }

    public static void saveKingdom(Player player) {

        new Thread(() -> {

            String kingdomName = kingdomDBHandler.getPlayerKingdom(player);
            FeudalKingdom feudalKingdom = new FeudalKingdom(kingdomName);

            List<String> members = new ArrayList<>();
            feudalKingdom.getMembers().forEach(member -> members.add(member.getUniqueId().toString()));

            List<String> barons = new ArrayList<>();
            feudalKingdom.getBarons().forEach(baron -> barons.add(baron.getUniqueId().toString()));

            List<String> territory = new ArrayList<>();
            feudalKingdom.getTerritory().forEach(chunk -> territory.add(GsonUtils.chunkToJson(ChunkWrapper.chunkToChunkWrapper(chunk))));

            List<String> privateTerritory = new ArrayList<>();
            feudalKingdom.getPrivateTerritory().forEach(chunk -> privateTerritory.add(GsonUtils.chunkToJson(ChunkWrapper.chunkToChunkWrapper(chunk))));

            kingdomDBHandler.setField(kingdomName, "king", feudalKingdom.getKing());
            kingdomDBHandler.setField(kingdomName, "members", members);
            kingdomDBHandler.setField(kingdomName, "barons", barons);
            kingdomDBHandler.setField(kingdomName, "territory", territory);
            kingdomDBHandler.setField(kingdomName, "privateTerritory", privateTerritory);
            kingdomDBHandler.setField(kingdomName, "reputation", feudalKingdom.getReputation());
            kingdomDBHandler.setField(kingdomName, "balance", feudalKingdom.getBalance());

        }).start();

        System.gc();

    }

    public static void saveAllPlayers() {

        Bukkit.getOnlinePlayers().forEach(player -> new Thread(() -> {

            FeudalPlayer feudalPlayer = CachePlayersMap.getFeudalPlayer(player);

            playerDBHandler.setField(player, "classID", feudalPlayer.getAClassID());
            playerDBHandler.setField(player, "experience", feudalPlayer.getExperience());
            playerDBHandler.setField(player, "gameClassLvl", feudalPlayer.getGameClassLvl());
            playerDBHandler.setField(player, "gameClassExperience", feudalPlayer.getGameClassExperience());
            playerDBHandler.setField(player, "balance", feudalPlayer.getBalance());
            playerDBHandler.setField(player, "deaths", feudalPlayer.getDeaths());
            playerDBHandler.setField(player, "kills", feudalPlayer.getKills());
            playerDBHandler.setField(player, "luckLvl", feudalPlayer.getLuckLvl());
            playerDBHandler.setField(player, "speedLvl", feudalPlayer.getSpeedLvl());
            playerDBHandler.setField(player, "staminaLvl", feudalPlayer.getStaminaLvl());
            playerDBHandler.setField(player, "strengthLvl", feudalPlayer.getStrengthLvl());
            playerDBHandler.setField(player, "survivabilityLvl", feudalPlayer.getSurvivabilityLvl());
            playerDBHandler.setField(player, "kingdomName", feudalPlayer.getKingdomName());

            CachePlayersMap.getFeudalPlayerInfo().remove(player);

        }).start());

        System.gc();

    }

    public static void loadPlayer(@NotNull Player player) {

        FeudalPlayer feudalPlayer;

        player.sendMessage("Подождите, ваши данные загружаются!");

        if (!playerDBHandler.createNewPlayer(player)) {

            player.kickPlayer("Произошла проблема! Ваши данные не загружены или повреждены, попробуйте зайти позже.");
            return;

        }

        player.sendMessage("Ваши данные успешно загружены! Удачной игры");

        if (!playerDBHandler.hasPlayer(player)) {

            feudalPlayer = new FeudalPlayer(player);
            feudalPlayer.setaClassID(0).setExperience(0).setGameClassExperience(0)
                    .setBalance(1000).setDeaths(0).setKills(0)
                    .setLuckLvl(0).setSpeedLvl(0).setStaminaLvl(0)
                    .setStrengthLvl(0).setKingdomName("").setSurvivabilityLvl(0)
                    .setGameClassLvl(0);

            return;
        }

        feudalPlayer = new FeudalPlayer(player);
        feudalPlayer.setaClassID((Integer) playerDBHandler.getField(player, "classID"))
                .setExperience((Integer) playerDBHandler.getField(player, "experience"))
                .setGameClassLvl((Integer) playerDBHandler.getField(player, "gameClassLvl"))
                .setGameClassExperience((Integer) playerDBHandler.getField(player, "gameClassExperience"))
                .setBalance((Integer) playerDBHandler.getField(player, "balance"))
                .setDeaths((Integer) playerDBHandler.getField(player, "deaths"))
                .setKills((Integer) playerDBHandler.getField(player, "kills"))
                .setLuckLvl((Integer) playerDBHandler.getField(player, "luckLvl"))
                .setSpeedLvl((Integer) playerDBHandler.getField(player, "speedLvl"))
                .setStaminaLvl((Integer) playerDBHandler.getField(player, "staminaLvl"))
                .setStrengthLvl((Integer) playerDBHandler.getField(player, "strengthLvl"))
                .setKingdomName((String) playerDBHandler.getField(player, "kingdomName"))
                .setSurvivabilityLvl((Integer) playerDBHandler.getField(player, "survivabilityLvl"));

        CachePlayersMap.getFeudalPlayerInfo().put(player, feudalPlayer);

    }

    public static void loadPlayerAttributes(@NotNull Player player) {

        FeudalPlayer feudalPlayer = new FeudalPlayer(player);

        float health = feudalPlayer.getSurvivabilityLvl(), speed = feudalPlayer.getSpeedLvl();

        player.setMaxHealth(20 * (health / 100) + 20);
        player.setWalkSpeed(0.2f * (speed / 100) + 0.2f);
        ScoreBoardInfo.createScoreBoardInfo(player);


        if (!kingdomDBHandler.getPlayerKingdom(player).equalsIgnoreCase(""))
            player.setDisplayName(player.getDisplayName() + " [" + kingdomDBHandler.getPlayerKingdom(player) + "]");

    }

    public static void savePlayer(Player player) {

        new Thread(() -> {

            FeudalPlayer feudalPlayer = CachePlayersMap.getFeudalPlayer(player);

            playerDBHandler.setField(player, "classID", feudalPlayer.getAClassID());
            playerDBHandler.setField(player, "experience", feudalPlayer.getExperience());
            playerDBHandler.setField(player, "gameClassLvl", feudalPlayer.getGameClassLvl());
            playerDBHandler.setField(player, "gameClassExperience", feudalPlayer.getGameClassExperience());
            playerDBHandler.setField(player, "balance", feudalPlayer.getBalance());
            playerDBHandler.setField(player, "deaths", feudalPlayer.getDeaths());
            playerDBHandler.setField(player, "kills", feudalPlayer.getKills());
            playerDBHandler.setField(player, "luckLvl", feudalPlayer.getLuckLvl());
            playerDBHandler.setField(player, "speedLvl", feudalPlayer.getSpeedLvl());
            playerDBHandler.setField(player, "staminaLvl", feudalPlayer.getStaminaLvl());
            playerDBHandler.setField(player, "strengthLvl", feudalPlayer.getStrengthLvl());
            playerDBHandler.setField(player, "survivabilityLvl", feudalPlayer.getSurvivabilityLvl());
            playerDBHandler.setField(player, "kingdomName", feudalPlayer.getKingdomName());

            CachePlayersMap.getFeudalPlayerInfo().remove(player);

        }).start();

        System.gc();

    }
}
