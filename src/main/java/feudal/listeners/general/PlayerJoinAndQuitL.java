package feudal.listeners.general;

import feudal.data.SpyPlayer;
import feudal.data.cache.CacheFeudalPlayers;
import feudal.data.cache.CacheSpyPlayers;
import feudal.data.database.PlayerDBHandler;
import feudal.utils.LoadAndSaveDataUtils;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;


public class PlayerJoinAndQuitL implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public final void playerJoin(@NotNull PlayerJoinEvent event) {

        Player player = event.getPlayer();

        if (CacheSpyPlayers.getSpyPlayer(player) == null ||
                !player.hasPermission("feudal.ls"))
            player.setGameMode(GameMode.SURVIVAL);


        if (!PlayerDBHandler.hasPlayer(player)) {

            LoadAndSaveDataUtils.loadPlayer(player);
            return;

        }

        new SpyPlayer().load();

        LoadAndSaveDataUtils.loadPlayer(player);
        LoadAndSaveDataUtils.loadKingdom(player);
        LoadAndSaveDataUtils.loadPlayerProducts();

    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public final void playerQuit(@NotNull PlayerQuitEvent event) {

        Player player = event.getPlayer();

        if (CacheFeudalPlayers.getFeudalPlayer(player) != null)
            LoadAndSaveDataUtils.savePlayer(player);

    }
}
