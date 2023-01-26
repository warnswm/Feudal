package feudal.listeners;

import feudal.info.CacheKingdoms;
import feudal.info.CachePlayers;
import feudal.info.KingdomInfo;
import feudal.info.PlayerInfo;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
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

        if (!playerInfo.hasPlayer(player))
            playerInfo.createNewPlayer(player);


        playerInfo.setPlayer(player)
                .setaClassID((Integer) playerInfo.getField(player, "classID"))
                .setExperience((Integer) playerInfo.getField(player, "experience"))
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

        float tmp = playerInfo.getSpeedLvl();
        player.setMaxHealth(20 * (tmp / 100) + 20);

        player.setDisplayName(player.getDisplayName() + " [" + kingdomInfo.getPlayerKingdom(player) + "]");


        if (!kingdomInfo.playerInKingdom(player)) return;

        kingdomInfo.setKingdomName(kingdomInfo.getPlayerKingdom(player))
                .setKing((String) kingdomInfo.getField(kingdomInfo.getPlayerKingdom(player), "king"))
                .setBanner((String) kingdomInfo.getField(kingdomInfo.getPlayerKingdom(player), "banner"))
                .setMembers((List<String>) kingdomInfo.getField(kingdomInfo.getPlayerKingdom(player), "members"))
                .setBarons((List<String>) kingdomInfo.getField(kingdomInfo.getPlayerKingdom(player), "barons"))
                .setTerritory((List<Chunk>) kingdomInfo.getField(kingdomInfo.getPlayerKingdom(player), "territory"));


        CacheKingdoms.getKingdomInfo().put(kingdomInfo.getPlayerKingdom(player), kingdomInfo);
    }

    @EventHandler
    public void playerQuit(@NotNull PlayerQuitEvent event) {

        if (CachePlayers.getPlayerInfo().get(event.getPlayer()) == null) return;

        Player player = event.getPlayer();

        new Thread(() -> {

            PlayerInfo cachePlayerInfo = CachePlayers.getPlayerInfo().get(player);

            playerInfo.setField(player, "classID", cachePlayerInfo.getAClassID());
            playerInfo.setField(player, "experience", cachePlayerInfo.getExperience());
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


            if (kingdomInfo.getPlayerKingdom(player).equalsIgnoreCase("notInTheKingdom")) return;

            String kingdomName = kingdomInfo.getPlayerKingdom(player);

            KingdomInfo cacheKingdomInfo = CacheKingdoms.getKingdomInfo().get(kingdomName);

            kingdomInfo.setField(kingdomName, "king", cacheKingdomInfo.getKing());
            kingdomInfo.setField(kingdomName, "banner", cacheKingdomInfo.getBanner());
            kingdomInfo.setField(kingdomName, "members", cacheKingdomInfo.getMembers());
            kingdomInfo.setField(kingdomName, "barons", cacheKingdomInfo.getBarons());
            kingdomInfo.setField(kingdomName, "territory", cacheKingdomInfo.getTerritory());

            CacheKingdoms.getKingdomInfo().remove(kingdomName);

        }).start();

    }
}
