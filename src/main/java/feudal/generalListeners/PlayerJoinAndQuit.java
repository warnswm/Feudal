package feudal.generalListeners;

import feudal.data.cache.CacheFeudalPlayers;
import feudal.data.database.KingdomDBHandler;
import feudal.data.database.PlayerDBHandler;
import feudal.utils.LoadAndSaveDataUtils;
import feudal.utils.TabUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerJoinAndQuit implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
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

    @EventHandler(priority = EventPriority.HIGHEST)
    public void playerQuit(@NotNull PlayerQuitEvent event) {

        if (CacheFeudalPlayers.getFeudalPlayer(event.getPlayer()) == null) return;

        Player player = event.getPlayer();

        if (KingdomDBHandler.getPlayerKingdom(player).equals("")) {

            LoadAndSaveDataUtils.savePlayer(player);
            return;

        }

        LoadAndSaveDataUtils.savePlayer(player);
        LoadAndSaveDataUtils.saveKingdom(player);

    }
}
