package feudal.generalListeners;

import feudal.Feudal;
import feudal.data.cache.CacheFeudalPlayers;
import feudal.data.database.PlayerDBHandler;
import feudal.utils.LoadAndSaveDataUtils;
import feudal.utils.TabUtils;
import feudal.utils.TasksQueueUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

public class PlayerJoinAndQuitL implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public final void playerJoin(@NotNull PlayerJoinEvent event) {

        Player player = event.getPlayer();

        TasksQueueUtils queue = new TasksQueueUtils()

                .sleep(1, TimeUnit.SECONDS)
                .action(() -> {

                    if (player.isOnline()) {


                        if (!PlayerDBHandler.hasPlayer(player)) {

                            LoadAndSaveDataUtils.loadPlayer(player);
                            return;

                        }

                        LoadAndSaveDataUtils.loadPlayer(player);
                        LoadAndSaveDataUtils.loadKingdom(player);

                        TabUtils.updateHidePlayers();

                    }

                });

        queue.start(Feudal.getPlugin());

    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public final void playerQuit(@NotNull PlayerQuitEvent event) {

        if (CacheFeudalPlayers.getFeudalPlayer(event.getPlayer()) == null) return;

        Player player = event.getPlayer();
        LoadAndSaveDataUtils.savePlayer(player);
        LoadAndSaveDataUtils.saveKingdom(player);

    }
}
