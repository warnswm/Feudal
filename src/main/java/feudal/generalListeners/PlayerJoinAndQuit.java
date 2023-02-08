package feudal.generalListeners;

import feudal.data.cache.CachePlayersMap;
import feudal.data.database.KingdomDBHandler;
import feudal.data.database.PlayerDBHandler;
import feudal.utils.LoadAndSaveDataUtils;
import feudal.utils.TabUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerJoinAndQuit implements Listener {

    @EventHandler
    public void playerJoin(@NotNull PlayerJoinEvent event) {

        Player player = event.getPlayer();

        if (!PlayerDBHandler.hasPlayer(player)) {

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

        if (CachePlayersMap.getFeudalPlayer(event.getPlayer()) == null) return;

        Player player = event.getPlayer();

        if (KingdomDBHandler.getPlayerKingdom(player).equals("")) {

            LoadAndSaveDataUtils.savePlayer(player);
            return;

        }

        LoadAndSaveDataUtils.savePlayer(player);
        LoadAndSaveDataUtils.saveKingdom(player);

    }
}
