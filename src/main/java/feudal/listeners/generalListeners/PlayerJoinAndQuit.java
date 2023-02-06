package feudal.listeners.generalListeners;

import feudal.data.cache.CachePlayersMap;
import feudal.data.database.KingdomDBInfo;
import feudal.data.database.PlayerDBInfo;
import feudal.utils.LoadAndSaveDataUtils;
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
public class PlayerJoinAndQuit implements Listener {

    FileConfiguration config = Bukkit.getPluginManager().getPlugin("Feudal").getConfig();
    PlayerDBInfo playerDBInfo = new PlayerDBInfo(config.get("MongoClientName").toString(), config.get("MongoDataBaseName").toString(), config.get("MongoCollectionName").toString());
    KingdomDBInfo kingdomDBInfo = new KingdomDBInfo(config.get("MongoClientName").toString(), config.get("MongoDataBaseName").toString(), config.get("MongoCollectionName").toString());

    @EventHandler
    public void playerJoin(@NotNull PlayerJoinEvent event) {

        Player player = event.getPlayer();

        if (!playerDBInfo.hasPlayer(player)) {

            LoadAndSaveDataUtils.loadPlayer(player);
            return;

        }

        LoadAndSaveDataUtils.loadPlayer(player);
        LoadAndSaveDataUtils.loadPlayerAttributes(player);
        LoadAndSaveDataUtils.loadKingdom(player);

    }

    @EventHandler
    public void playerQuit(@NotNull PlayerQuitEvent event) {

        if (CachePlayersMap.getPlayerInfo().get(event.getPlayer()) == null) return;

        Player player = event.getPlayer();

        if (kingdomDBInfo.getPlayerKingdom(player).equalsIgnoreCase("notInTheKingdom")) {

            LoadAndSaveDataUtils.savePlayer(player);
            return;

        }

        LoadAndSaveDataUtils.savePlayer(player);
        LoadAndSaveDataUtils.saveKingdom(player);

    }
}
