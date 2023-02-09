package feudal.possessions.territoryListeners;

import feudal.data.cache.CacheKingdomsMap;
import feudal.utils.GsonUtils;
import feudal.utils.enums.PrivateBlocksEnum;
import feudal.utils.wrappers.ChunkWrapper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;

public class InteractListener implements Listener {

    @EventHandler
    public void playerInteractItem(@NotNull PlayerInteractEvent event) {

        if (event.getClickedBlock() == null ||
                !PrivateBlocksEnum.getByMaterial(event.getClickedBlock().getType()) &&
                        !CacheKingdomsMap.checkPrivate(GsonUtils.chunkToJson(ChunkWrapper.chunkToChunkWrapper(event.getClickedBlock().getChunk())), event.getPlayer())) return;

        event.setCancelled(true);

    }

    @EventHandler
    public void playerInteractBlock(@NotNull PlayerInteractEvent event) {

        if (event.getClickedBlock() == null ||
                !PrivateBlocksEnum.getByMaterial(event.getClickedBlock().getType()) &&
                        !CacheKingdomsMap.checkPrivate(GsonUtils.chunkToJson(ChunkWrapper.chunkToChunkWrapper(event.getClickedBlock().getChunk())), event.getPlayer())) return;

        event.setCancelled(true);

    }
}
