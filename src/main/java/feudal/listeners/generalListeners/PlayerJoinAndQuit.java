package feudal.listeners.generalListeners;

import feudal.data.cache.CachePlayersMap;
import feudal.data.database.KingdomInfo;
import feudal.data.database.PlayerInfo;
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
    PlayerInfo playerInfo = new PlayerInfo(config.get("MongoClientName").toString(), config.get("MongoDataBaseName").toString(), config.get("MongoCollectionName").toString());
    KingdomInfo kingdomInfo = new KingdomInfo(config.get("MongoClientName").toString(), config.get("MongoDataBaseName").toString(), config.get("MongoCollectionName").toString());

    @EventHandler
    public void playerJoin(@NotNull PlayerJoinEvent event) {

        Player player = event.getPlayer();

        if (!playerInfo.hasPlayer(player)) {

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

        if (kingdomInfo.getPlayerKingdom(player).equalsIgnoreCase("notInTheKingdom")) {

            LoadAndSaveDataUtils.savePlayer(player);
            return;

        }

        LoadAndSaveDataUtils.savePlayer(player);
        LoadAndSaveDataUtils.saveKingdom(player);

    }
}
