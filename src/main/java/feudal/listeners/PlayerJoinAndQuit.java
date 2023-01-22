package feudal.listeners;

import feudal.info.CachePlayerInfo;
import feudal.info.PlayerInfoDB;
import feudal.utils.CachePlayersHashMap;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PlayerJoinAndQuit implements Listener {

    FileConfiguration config = Bukkit.getPluginManager().getPlugin("Feudal").getConfig();
    PlayerInfoDB playerInfoDB = new PlayerInfoDB(config.get("MongoClientName").toString(), config.get("MongoDataBaseName").toString(), config.get("MongoCollectionName").toString());

    @EventHandler
    public void playerJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        if (!playerInfoDB.hasPlayer(player))
            playerInfoDB.createNewPlayer(player);


        CachePlayerInfo cachePlayerInfo = new CachePlayerInfo()
                .setPlayer(player)
                .setaClassID((Integer) playerInfoDB.getField(player, "classID"))
                .setExperience((Integer) playerInfoDB.getField(player, "experience"))
                .setBalance((Integer) playerInfoDB.getField(player, "balance"))
                .setDeaths((Integer) playerInfoDB.getField(player, "deaths"))
                .setKills((Integer) playerInfoDB.getField(player, "kills"))
                .setLuckLvl((Integer) playerInfoDB.getField(player, "luckLvl"))
                .setSpeedLvl((Integer) playerInfoDB.getField(player, "speedLvl"))
                .setStaminaLvl((Integer) playerInfoDB.getField(player, "staminaLvl"))
                .setStrengthLvl((Integer) playerInfoDB.getField(player, "strengthLvl"))
                .setSurvivabilityLvl((Integer) playerInfoDB.getField(player, "survivabilityLvl"));

        CachePlayersHashMap.getPlayerInfo().put(player, cachePlayerInfo);

        float tmp = cachePlayerInfo.getSpeedLvl();

        player.setMaxHealth(20 * (tmp / 100) + 20);
    }

    @EventHandler
    public void playerQuit(PlayerQuitEvent event) {

        if (CachePlayersHashMap.getPlayerInfo().get(event.getPlayer()) == null) return;

        Player player = event.getPlayer();

        playerInfoDB.setField(player, "classID", CachePlayersHashMap.getPlayerInfo().get(player).getAClassID());
        playerInfoDB.setField(player, "experience", CachePlayersHashMap.getPlayerInfo().get(player).getExperience());
        playerInfoDB.setField(player, "balance", CachePlayersHashMap.getPlayerInfo().get(player).getBalance());
        playerInfoDB.setField(player, "deaths", CachePlayersHashMap.getPlayerInfo().get(player).getDeaths());
        playerInfoDB.setField(player, "kills", CachePlayersHashMap.getPlayerInfo().get(player).getKills());
        playerInfoDB.setField(player, "luckLvl", CachePlayersHashMap.getPlayerInfo().get(player).getLuckLvl());
        playerInfoDB.setField(player, "speedLvl", CachePlayersHashMap.getPlayerInfo().get(player).getSpeedLvl());
        playerInfoDB.setField(player, "staminaLvl", CachePlayersHashMap.getPlayerInfo().get(player).getStaminaLvl());
        playerInfoDB.setField(player, "strengthLvl", CachePlayersHashMap.getPlayerInfo().get(player).getStrengthLvl());
        playerInfoDB.setField(player, "survivabilityLvl", CachePlayersHashMap.getPlayerInfo().get(player).getSurvivabilityLvl());

        CachePlayersHashMap.getPlayerInfo().remove(player);

    }
}
