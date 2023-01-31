package feudal.listeners.generalListeners;

import feudal.databaseAndCache.CacheKingdoms;
import feudal.databaseAndCache.CachePlayers;
import feudal.databaseAndCache.KingdomInfo;
import feudal.databaseAndCache.PlayerInfo;
import feudal.view.ScoreBoardInfo;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PlayerJoinAndQuit implements Listener {

    FileConfiguration config = Bukkit.getPluginManager().getPlugin("Feudal").getConfig();
    PlayerInfo playerInfo = new PlayerInfo(config.get("MongoClientName").toString(), config.get("MongoDataBaseName").toString(), config.get("MongoCollectionName").toString());
    KingdomInfo kingdomInfo = new KingdomInfo(config.get("MongoClientName").toString(), config.get("MongoDataBaseName").toString(), config.get("MongoCollectionName").toString());

    @EventHandler
    public void playerJoin(@NotNull PlayerJoinEvent event) {

        Player player = event.getPlayer();

        if (!playerInfo.hasPlayer(player)) {

            loadPlayer(player);

            return;
        }

        loadPlayer(player);
        setPlayerAttribute(player);
        loadKingdom(player);

    }

    @EventHandler
    public void playerQuit(@NotNull PlayerQuitEvent event) {

        Player player = event.getPlayer();

        if (CachePlayers.getPlayerInfo().get(event.getPlayer()) == null) return;


        if (kingdomInfo.getPlayerKingdom(player).equalsIgnoreCase("notInTheKingdom")) {

            savePlayer(player);
            return;
        }

        savePlayer(player);
        saveKingdom(player);

    }

    private void loadPlayer(Player player) {

        if (!playerInfo.hasPlayer(player)) {

            playerInfo.createNewPlayer(player);
            playerInfo.setPlayer(player).setaClassID(0).setExperience(0).setGameClassExperience(0)
                    .setBalance(0).setDeaths(0).setKills(0)
                    .setLuckLvl(0).setSpeedLvl(0).setStaminaLvl(0)
                    .setStrengthLvl(0).setKingdomName("notInTheKingdom").setSurvivabilityLvl(0)
                    .setGameClassLvl(0);

            return;
        }

        playerInfo.setPlayer(player)
                .setaClassID((Integer) playerInfo.getField(player, "classID"))
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

        CachePlayers.getPlayerInfo().put(player, playerInfo);

    }

    private void loadKingdom(Player player) {

        if (!kingdomInfo.playerInKingdom(player)) return;

        String kingdomName = kingdomInfo.getPlayerKingdom(player);

        kingdomInfo.setKingdomName(kingdomName)
                .setKing((String) kingdomInfo.getField(kingdomName, "king"))
                .setMembers((List<String>) kingdomInfo.getField(kingdomName, "members"))
                .setBarons((List<String>) kingdomInfo.getField(kingdomName, "barons"))
                .setReputation((Integer) kingdomInfo.getField(kingdomName, "reputation"))
                .setBalance((Integer) kingdomInfo.getField(kingdomName, "balance"))
                .setTerritory((List<Chunk>) kingdomInfo.getField(kingdomName, "territory"));

        CacheKingdoms.getKingdomInfo().put(kingdomName, kingdomInfo);

    }

    private void setPlayerAttribute(@NotNull Player player) {

        float tmpHealth = playerInfo.getSurvivabilityLvl(), tmpSpeed = playerInfo.getSpeedLvl();

        player.setMaxHealth(20 * (tmpHealth / 100) + 20);
        player.setWalkSpeed(0.2f * (tmpSpeed / 100) + 0.2f);
        ScoreBoardInfo.createScoreBoardInfo(player);


        if (!kingdomInfo.getPlayerKingdom(player).equalsIgnoreCase("notInTheKingdom"))
            player.setDisplayName(player.getDisplayName() + " [" + kingdomInfo.getPlayerKingdom(player) + "]");

    }

    private void savePlayer(Player player) {

        new Thread(() -> {

            PlayerInfo cachePlayerInfo = CachePlayers.getPlayerInfo().get(player);

            playerInfo.setField(player, "classID", cachePlayerInfo.getAClassID());
            playerInfo.setField(player, "experience", cachePlayerInfo.getExperience());
            playerInfo.setField(player, "gameClassLvl", cachePlayerInfo.getGameClassLvl());
            playerInfo.setField(player, "gameClassExperience", cachePlayerInfo.getGameClassExperience());
            playerInfo.setField(player, "balance", cachePlayerInfo.getBalance());
            playerInfo.setField(player, "deaths", cachePlayerInfo.getDeaths());
            playerInfo.setField(player, "kills", cachePlayerInfo.getKills());
            playerInfo.setField(player, "luckLvl", cachePlayerInfo.getLuckLvl());
            playerInfo.setField(player, "speedLvl", cachePlayerInfo.getSpeedLvl());
            playerInfo.setField(player, "staminaLvl", cachePlayerInfo.getStaminaLvl());
            playerInfo.setField(player, "strengthLvl", cachePlayerInfo.getStrengthLvl());
            playerInfo.setField(player, "survivabilityLvl", cachePlayerInfo.getSurvivabilityLvl());
            playerInfo.setField(player, "kingdomName", cachePlayerInfo.getKingdomName());

            CachePlayers.getPlayerInfo().remove(player);

        }).start();

        System.gc();

    }

    private void saveKingdom(Player player) {

        new Thread(() -> {

            val kingdomName = kingdomInfo.getPlayerKingdom(player);
            val cacheKingdomInfo = CacheKingdoms.getKingdomInfo().get(kingdomName);

            kingdomInfo.setField(kingdomName, "king", cacheKingdomInfo.getKing());
            kingdomInfo.setField(kingdomName, "members", cacheKingdomInfo.getMembers());
            kingdomInfo.setField(kingdomName, "barons", cacheKingdomInfo.getBarons());
            kingdomInfo.setField(kingdomName, "territory", cacheKingdomInfo.getTerritory());
            kingdomInfo.setField(kingdomName, "reputation", cacheKingdomInfo.getReputation());
            kingdomInfo.setField(kingdomName, "balance", cacheKingdomInfo.getBalance());

        }).start();

        System.gc();

    }
}
