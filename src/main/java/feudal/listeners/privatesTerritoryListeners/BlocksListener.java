package feudal.listeners.privatesTerritoryListeners;

import feudal.data.cache.CacheKingdomsMap;
import feudal.utils.GsonUtils;
import feudal.utils.wrappers.ChunkWrapper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.jetbrains.annotations.NotNull;

public class BlocksListener implements Listener {

    @EventHandler
    public void playerBreakBlock(@NotNull BlockBreakEvent event) {

        if (CacheKingdomsMap.chunkInKingdomCache(GsonUtils.chunkToJson(ChunkWrapper.chunkToChunkWrapper(event.getBlock().getChunk()))))
            event.setCancelled(true);

    }

    @EventHandler
    public void playerPlaceBlock(@NotNull BlockPlaceEvent event) {

        if (CacheKingdomsMap.chunkInKingdomCache(GsonUtils.chunkToJson(ChunkWrapper.chunkToChunkWrapper(event.getBlock().getChunk()))))
            event.setCancelled(true);

    }

    @EventHandler
    public void playerSignChange(@NotNull SignChangeEvent event) {

        if (CacheKingdomsMap.chunkInKingdomCache(GsonUtils.chunkToJson(ChunkWrapper.chunkToChunkWrapper(event.getBlock().getChunk()))))
            event.setCancelled(true);

    }

    @EventHandler
    public void playerNotePlayer(@NotNull NotePlayEvent event) {

        if (CacheKingdomsMap.chunkInKingdomCache(GsonUtils.chunkToJson(ChunkWrapper.chunkToChunkWrapper(event.getBlock().getChunk()))))
            event.setCancelled(true);

    }

    @EventHandler
    public void playerBlockDamage(@NotNull BlockDamageEvent event) {

        if (CacheKingdomsMap.chunkInKingdomCache(GsonUtils.chunkToJson(ChunkWrapper.chunkToChunkWrapper(event.getBlock().getChunk()))))
            event.setCancelled(true);

    }
}
