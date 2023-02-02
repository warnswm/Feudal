package feudal.listeners.privatesTerritoryListeners;

import feudal.data.cache.CacheKingdomsMap;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.jetbrains.annotations.NotNull;

public class BlocksListener implements Listener {

    @EventHandler
    public void playerBreakBlock(@NotNull BlockBreakEvent event) {

        if (CacheKingdomsMap.chunkInKingdomCache(event.getBlock().getChunk().toString()))
            event.setCancelled(true);

    }
}
