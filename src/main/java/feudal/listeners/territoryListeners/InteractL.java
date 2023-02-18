package feudal.listeners.territoryListeners;

import feudal.data.cache.CacheFeudalKingdoms;
import feudal.utils.enums.PrivateBlocksE;
import feudal.utils.wrappers.ChunkWrapper;
import org.bukkit.Chunk;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;


public class InteractL implements Listener {

    @EventHandler
    public void playerInteract(@NotNull PlayerInteractEvent event) {

        Chunk chunk = event.getClickedBlock().getChunk();

        if (event.getClickedBlock() == null ||
                !CacheFeudalKingdoms.checkPrivate(new ChunkWrapper(chunk.getWorld().getName(), chunk.getX(), chunk.getZ()).hashCode(), event.getPlayer()) ||
                !PrivateBlocksE.checkMaterial(event.getClickedBlock().getType()))
            return;

        event.setCancelled(true);

    }
}
