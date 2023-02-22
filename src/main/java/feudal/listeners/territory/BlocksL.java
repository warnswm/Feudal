package feudal.listeners.territory;

import feudal.data.cache.CacheFeudalKingdoms;
import org.bukkit.Chunk;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.jetbrains.annotations.NotNull;


public class BlocksL implements Listener {

    @EventHandler
    public void playerBreakBlock(@NotNull BlockBreakEvent event) {

        Chunk chunk = event.getBlock().getChunk();

        if (CacheFeudalKingdoms.checkPrivate(chunk, event.getPlayer()))
            event.setCancelled(true);

    }

    @EventHandler
    public void playerPlaceBlock(@NotNull BlockPlaceEvent event) {

        Chunk chunk = event.getBlock().getChunk();

        if (CacheFeudalKingdoms.checkPrivate(chunk, event.getPlayer()))
            event.setCancelled(true);

    }

    @EventHandler
    public void playerBlockDamage(@NotNull BlockDamageEvent event) {

        Chunk chunk = event.getBlock().getChunk();

        if (CacheFeudalKingdoms.checkPrivate(chunk, event.getPlayer()))
            event.setCancelled(true);

    }

}
