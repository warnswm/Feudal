package feudal.possessions.territoryListeners;

import feudal.Feudal;
import feudal.data.cache.CacheKingdomsMap;
import feudal.utils.GsonUtils;
import feudal.utils.wrappers.ChunkWrapper;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class BlocksListener implements Listener {

    @EventHandler
    public void playerBreakBlock(@NotNull BlockBreakEvent event) {

        if (CacheKingdomsMap.checkPrivate(GsonUtils.chunkToJson(ChunkWrapper.chunkToChunkWrapper(event.getBlock().getChunk())), event.getPlayer()) ||
                event.getBlock().hasMetadata("Kingdom"))
            event.setCancelled(true);

    }

    @EventHandler
    public void playerPlaceBlock(@NotNull BlockPlaceEvent event) {

        if (Objects.requireNonNull(CraftItemStack.asNMSCopy(event.getItemInHand()).getTag()).getBoolean("kingdomBlock"))
            event.getBlock().setMetadata("Kingdom", new FixedMetadataValue(Feudal.getPlugin(), "true"));

        if (CacheKingdomsMap.checkPrivate(GsonUtils.chunkToJson(ChunkWrapper.chunkToChunkWrapper(event.getBlock().getChunk())), event.getPlayer()))
            event.setCancelled(true);

    }

    @EventHandler
    public void playerBlockDamage(@NotNull BlockDamageEvent event) {

        if (CacheKingdomsMap.checkPrivate(GsonUtils.chunkToJson(ChunkWrapper.chunkToChunkWrapper(event.getBlock().getChunk())), event.getPlayer()))
            event.setCancelled(true);

    }

}
