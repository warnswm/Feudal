package feudal.listeners.privatesTerritoryListeners;

import feudal.data.cache.CacheKingdomsMap;
import feudal.utils.ChunkWrapper;
import feudal.utils.GsonUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.jetbrains.annotations.NotNull;

public class BlocksListener implements Listener {

    @EventHandler
    public void playerBreakBlock(@NotNull BlockBreakEvent event) {

        if (CacheKingdomsMap.chunkInKingdomCache(GsonUtils.chunkToJson(ChunkWrapper.chunkToChunkWrapper(event.getBlock().getChunk()))))
            event.setCancelled(true);

    }
}
