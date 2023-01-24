package feudal.listeners;

import feudal.info.CachePlayerInfo;
import feudal.info.PlayerInfoDB;
import feudal.utils.CachePlayers;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PlayerJoinAndQuit implements Listener{

    FileConfiguration config = Bukkit.getPluginManager().getPlugin("Feudal").getConfig();
    PlayerInfoDB playerInfoDB = new PlayerInfoDB(config.get("MongoClientName").toString(), config.get("MongoDataBaseName").toString(), config.get("MongoCollectionName").toString());
    @EventHandler
    public void playerJoin(@NotNull PlayerJoinEvent event) {

        Player player = event.getPlayer();

        if (!playerInfoDB.hasPlayer(player))
            playerInfoDB.createNewPlayer(player);


        CachePlayerInfo cachePlayerInfo = new CachePlayerInfo()
                .setPlayer(player)
                .setaClassID((Integer) playerInfoDB.getField(player, "classID"))
                .setExperience((Integer) playerInfoDB.getField(player, "experience"))
                .setGameClassExperience((Integer) playerInfoDB.getField(player, "gameClassExperience"))
                .setBalance((Integer) playerInfoDB.getField(player, "balance"))
                .setDeaths((Integer) playerInfoDB.getField(player, "deaths"))
                .setKills((Integer) playerInfoDB.getField(player, "kills"))
                .setLuckLvl((Integer) playerInfoDB.getField(player, "luckLvl"))
                .setSpeedLvl((Integer) playerInfoDB.getField(player, "speedLvl"))
                .setStaminaLvl((Integer) playerInfoDB.getField(player, "staminaLvl"))
                .setStrengthLvl((Integer) playerInfoDB.getField(player, "strengthLvl"))
                .setKingdomName((String) playerInfoDB.getField(player, "kingdomName"))
                .setSurvivabilityLvl((Integer) playerInfoDB.getField(player, "survivabilityLvl"));

        CachePlayers.getPlayerInfo().put(player, cachePlayerInfo);

        float tmp = cachePlayerInfo.getSpeedLvl();

        player.setMaxHealth(20 * (tmp / 100) + 20);
    }

    @EventHandler
    public void playerQuit(@NotNull PlayerQuitEvent event) {

        if (CachePlayers.getPlayerInfo().get(event.getPlayer()) == null) return;

        Player player = event.getPlayer();

        new Thread(() -> {

            playerInfoDB.setField(player, "classID", CachePlayers.getPlayerInfo().get(player).getAClassID());
            playerInfoDB.setField(player, "experience", CachePlayers.getPlayerInfo().get(player).getExperience());
            playerInfoDB.setField(player, "gameClassExperience", CachePlayers.getPlayerInfo().get(player).getGameClassExperience());
            playerInfoDB.setField(player, "balance", CachePlayers.getPlayerInfo().get(player).getBalance());
            playerInfoDB.setField(player, "deaths", CachePlayers.getPlayerInfo().get(player).getDeaths());
            playerInfoDB.setField(player, "kills", CachePlayers.getPlayerInfo().get(player).getKills());
            playerInfoDB.setField(player, "luckLvl", CachePlayers.getPlayerInfo().get(player).getLuckLvl());
            playerInfoDB.setField(player, "speedLvl", CachePlayers.getPlayerInfo().get(player).getSpeedLvl());
            playerInfoDB.setField(player, "staminaLvl", CachePlayers.getPlayerInfo().get(player).getStaminaLvl());
            playerInfoDB.setField(player, "strengthLvl", CachePlayers.getPlayerInfo().get(player).getStrengthLvl());
            playerInfoDB.setField(player, "survivabilityLvl", CachePlayers.getPlayerInfo().get(player).getSurvivabilityLvl());
            playerInfoDB.setField(player, "kingdomName", CachePlayers.getPlayerInfo().get(player).getKingdomName());

            CachePlayers.getPlayerInfo().remove(player);

        }).start();

    }
}
