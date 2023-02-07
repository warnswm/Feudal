package feudal.listeners.generalListeners;

import feudal.data.cache.CachePlayersMap;
import feudal.data.database.KingdomDBHandler;
import feudal.data.database.PlayerDBHandler;
import feudal.utils.LoadAndSaveDataUtils;
import feudal.utils.TabUtils;
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
    PlayerDBHandler playerDBHandler = new PlayerDBHandler(config.get("MongoClientName").toString(), config.get("MongoDataBaseName").toString(), config.get("MongoCollectionName").toString());
    KingdomDBHandler kingdomDBHandler = new KingdomDBHandler(config.get("MongoClientName").toString(), config.get("MongoDataBaseName").toString(), config.get("MongoCollectionName").toString());

    @EventHandler
    public void playerJoin(@NotNull PlayerJoinEvent event) {

        Player player = event.getPlayer();

        if (!playerDBHandler.hasPlayer(player)) {

            LoadAndSaveDataUtils.loadPlayer(player);
            return;

        }

        LoadAndSaveDataUtils.loadPlayer(player);
        LoadAndSaveDataUtils.loadPlayerAttributes(player);
        LoadAndSaveDataUtils.loadKingdom(player);

        TabUtils.updateHidePlayers();

    }

    @EventHandler
    public void playerQuit(@NotNull PlayerQuitEvent event) {

        if (CachePlayersMap.getPlayerInfo().get(event.getPlayer()) == null) return;

        Player player = event.getPlayer();

        if (kingdomDBHandler.getPlayerKingdom(player).equalsIgnoreCase("notInTheKingdom")) {

            LoadAndSaveDataUtils.savePlayer(player);
            return;

        }

        LoadAndSaveDataUtils.savePlayer(player);
        LoadAndSaveDataUtils.saveKingdom(player);

    }
}
