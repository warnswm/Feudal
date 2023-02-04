package feudal.possessions.privatesTerritoryListeners;

import feudal.data.cache.CacheKingdomsMap;
import feudal.utils.GsonUtils;
import feudal.utils.enums.ItemInteractEnum;
import feudal.utils.enums.PrivateBlocksEnum;
import feudal.utils.wrappers.ChunkWrapper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;

public class InteractListener implements Listener {

    @EventHandler
    public void playerInteractItem(@NotNull PlayerInteractEvent event) {

        if (event.getItem() == null) return;

        if (ItemInteractEnum.getByItemMaterial(event.getItem().getType()) &&
                CacheKingdomsMap.chunkInKingdomCache(GsonUtils.chunkToJson(ChunkWrapper.chunkToChunkWrapper(event.getClickedBlock().getChunk()))))
            event.setCancelled(true);

    }

    @EventHandler
    public void playerInteractBlock(@NotNull PlayerInteractEvent event) {

        if (event.getClickedBlock() == null) return;

        if (PrivateBlocksEnum.getByMaterial(event.getClickedBlock().getType()) &&
                CacheKingdomsMap.chunkInKingdomCache(GsonUtils.chunkToJson(ChunkWrapper.chunkToChunkWrapper(event.getClickedBlock().getChunk()))))
            event.setCancelled(true);

    }
}
